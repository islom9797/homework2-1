package org.islom.homework21.service;

import org.islom.homework21.enity.Address;
import org.islom.homework21.repository.AddressRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    AddressRepo addressRepo;

    public ResponseEntity<List<Address> > getAddress() {
        return ResponseEntity.ok(addressRepo.findAll());
    }


    public HttpEntity<?> addAddress(Address address) {
        Address saved = addressRepo.save(address);
        return ResponseEntity.ok(saved);

    }

    public HttpEntity<?> updateAddress(Address address,int id) {
        Optional<Address> byId = addressRepo.findById(id);
        if (byId.isPresent()) {
            Address saved = byId.get();
            saved.setHomeNumber(address.getHomeNumber());
            saved.setStreet(address.getStreet());
            addressRepo.save(saved);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();

    }

    public HttpEntity<?> deleteAddress(int id) {
        Optional<Address> byId = addressRepo.findById(id);
        if (byId.isPresent()) {
            addressRepo.delete(byId.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
