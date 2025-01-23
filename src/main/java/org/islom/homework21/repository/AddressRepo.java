package org.islom.homework21.repository;

import org.islom.homework21.enity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}
