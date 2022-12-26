package com.upgrad.ecommerce.controllers;

import com.upgrad.ecommerce.dto.AddressDTO;
import com.upgrad.ecommerce.services.AddressService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressResource {

    private final AddressService addressService;

    public AddressResource(final AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<AddressDTO>> getAllAddresss() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable final String id) {
        return ResponseEntity.ok(addressService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> createAddress(@RequestBody @Valid final AddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.create(addressDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateAddress(@PathVariable final String id,
                                              @RequestBody @Valid final AddressDTO addressDTO) {
        addressService.update(id, addressDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteAddress(@PathVariable final String id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
