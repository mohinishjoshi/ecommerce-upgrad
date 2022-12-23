package com.upgrad.ecommerce.services;

import com.upgrad.ecommerce.dto.AddressDTO;
import com.upgrad.ecommerce.models.Address;
import com.upgrad.ecommerce.models.User;
import com.upgrad.ecommerce.repositories.AddressRepository;
import com.upgrad.ecommerce.repositories.UserRepository;
import com.upgrad.ecommerce.utils.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(final AddressRepository addressRepository,
                          final UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<AddressDTO> findAll() {
        final List<Address> addresss = addressRepository.findAll(Sort.by("id"));
        return addresss.stream()
                .map((address) -> mapToDTO(address, new AddressDTO()))
                .collect(Collectors.toList());
    }

    public AddressDTO get(final String id) {
        return addressRepository.findById(id)
                .map(address -> mapToDTO(address, new AddressDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public String create(final AddressDTO addressDTO) {
        final Address address = new Address();
        mapToEntity(addressDTO, address);
        return addressRepository.save(address).getId();
    }

    public void update(final String id, final AddressDTO addressDTO) {
        final Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(addressDTO, address);
        addressRepository.save(address);
    }

    public void delete(final String id) {
        addressRepository.deleteById(id);
    }

    private AddressDTO mapToDTO(final Address address, final AddressDTO addressDTO) {
        addressDTO.setId(address.getId());
        addressDTO.setName(address.getName());
        addressDTO.setContactNumber(address.getContactNumber());
        addressDTO.setCity(address.getCity());
        addressDTO.setLandmark(address.getLandmark());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setState(address.getState());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setUser(address.getUser() == null ? null : address.getUser().getId());
        return addressDTO;
    }

    private Address mapToEntity(final AddressDTO addressDTO, final Address address) {
        address.setName(addressDTO.getName());
        address.setContactNumber(addressDTO.getContactNumber());
        address.setCity(addressDTO.getCity());
        address.setLandmark(addressDTO.getLandmark());
        address.setStreet(addressDTO.getStreet());
        address.setState(addressDTO.getState());
        address.setZipcode(addressDTO.getZipcode());
        final User user = addressDTO.getUser() == null ? null : userRepository.findById(addressDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        address.setUser(user);
        return address;
    }

    public boolean contactNumberExists(final String contactNumber) {
        return addressRepository.existsByContactNumberIgnoreCase(contactNumber);
    }

}
