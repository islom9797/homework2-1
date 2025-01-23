package org.islom.homework21.service;

import org.islom.homework21.enity.Address;
import org.islom.homework21.enity.Department;
import org.islom.homework21.enity.Worker;
import org.islom.homework21.payload.ApiResponse;
import org.islom.homework21.payload.WorkerDto;
import org.islom.homework21.repository.AddressRepo;
import org.islom.homework21.repository.DepartmentRepo;
import org.islom.homework21.repository.WorkerRepo;

import java.util.List;
import java.util.Optional;

public class WorkerService {
    WorkerRepo workerRepo;

    AddressRepo addressRepo;
    DepartmentRepo departmentRepo;

    public Worker getWorker(Integer id) {
        Optional<Worker> byId = workerRepo.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;

    }

    public List<Worker> getWorkers() {
        return workerRepo.findAll();
    }

    public ApiResponse addWorker(WorkerDto workerDto) {
        Optional<Department> departmentRepoById = departmentRepo.findById(workerDto.getDepartmentId());
        if (!departmentRepoById.isPresent()) {
            return new ApiResponse("Departament topilmadi", false);
        }
        boolean existsByPhoneNumber = workerRepo.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("Bunday Telefin raqammli mivoj mavjud", false);
        }
        Address addres = new Address();
        addres.setHomeNumber(workerDto.getPhoneNumber());
        addres.setStreet(workerDto.getStreet());
        Address savedAddress = addressRepo.save(addres);
        Worker worker = new Worker();
        worker.setDepartment(departmentRepoById.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(savedAddress);
        workerRepo.save(worker);
        return new ApiResponse("Worker added", true);


    }

    public ApiResponse updateWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> byId = workerRepo.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Worker topilmadi", false);

        Optional<Department> departmentRepoById = departmentRepo.findById(workerDto.getDepartmentId());
        if (!departmentRepoById.isPresent()) {
            return new ApiResponse("Departament topilmadi", false);
        }

        boolean exitsByPhoneNumberAndIdNot = workerRepo.exitsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (exitsByPhoneNumberAndIdNot)
            return new ApiResponse("Bunday Telefin raqammli mivoj mavjud", false);
        Worker worker = byId.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(departmentRepoById.get());
        Address addres = worker.getAddress();
        addres.setHomeNumber(workerDto.getPhoneNumber());
        addres.setStreet(workerDto.getStreet());
        worker.setAddress(addres);
        workerRepo.save(worker);
        return new ApiResponse("Worker updated", true);


    }

    public ApiResponse deleteWorker(Integer id) {
        Optional<Worker> byId = workerRepo.findById(id);
        if(!byId.isPresent())
            return new ApiResponse("Worker topilmadi", false);
        return  new ApiResponse("Worker o'chirildi", true);
    }
}
