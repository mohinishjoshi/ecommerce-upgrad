package com.upgrad.ecommerce.controllers;

import com.upgrad.ecommerce.dto.LoginRequest;
import com.upgrad.ecommerce.dto.MessageResponse;
import com.upgrad.ecommerce.dto.SignupRequest;
import com.upgrad.ecommerce.dto.UserInfoResponse;
import com.upgrad.ecommerce.models.ERole;
import com.upgrad.ecommerce.models.Role;
import com.upgrad.ecommerce.models.User;
import com.upgrad.ecommerce.repositories.RoleRepository;
import com.upgrad.ecommerce.repositories.UserRepository;
import com.upgrad.ecommerce.security.jwt.AuthTokenFilter;
import com.upgrad.ecommerce.security.jwt.JwtUtils;
import com.upgrad.ecommerce.security.services.UserDetailsImpl;
import com.upgrad.ecommerce.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  private final List<ERole> roles = List.of(ERole.ADMIN, ERole.USER);
  private final RoleService roleService;
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  public AuthController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String token = jwtUtils.generateTokenFromUsername(userDetails.getEmail());

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("x-auth-token", token);
    // return ResponseEntity.ok()
    // .headers(responseHeaders).build();

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().headers(responseHeaders)
        .body(new UserInfoResponse(userDetails.getId(),
            userDetails.getEmail(),
            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    checkRolesExist();

    // Create new user's account
    User user = new User();
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    logger.info("Recieved Roles: {}", strRoles);

    if (strRoles == null) {
      logger.info("Got No ROles, making user");
      Role userRole = roleRepository.findByName(ERole.USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        if (role.equals("admin")) {
          logger.info("Got Admin Role: {}", role);
          Role adminRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);
        } else {
          logger.info("Got User Role: {}", role);
          Role userRole = roleRepository.findByName(ERole.USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  private void checkRolesExist() {
    for (ERole role : roles) {
      try {
        roleService.create(role);
        System.out.println("Role Created " + role.name());
      } catch (Exception e) {
        System.out.println("Skipping Role Creation");
      }
    }
  }
}
