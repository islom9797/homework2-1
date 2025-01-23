package org.islom.homework21.controller;

import org.islom.homework21.enity.Address;
import org.islom.homework21.service.AddressService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    AddressService addressService;

    @GetMapping
    public List<Address> getAllAddress() {
        return addressService.getAddress().getBody();
    }
    @PostMapping
    public HttpEntity<?> saveAddress(Address address) {
        HttpEntity<?> httpEntity = addressService.addAddress(address);
        return httpEntity;
    }
    @PutMapping("{id}")
    public HttpEntity<?> updateAddress(Address address,int id) {
      return   addressService.updateAddress(address,id);
    }
     @DeleteMapping
    public HttpEntity<?> deleteAddress(int id) {
        return addressService.deleteAddress(id);
     }


}
