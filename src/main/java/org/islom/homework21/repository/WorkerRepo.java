package org.islom.homework21.repository;

import org.islom.homework21.enity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepo extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean exitsByPhoneNumberAndIdNot(String phoneNumber,Integer id);
}
