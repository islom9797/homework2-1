package org.islom.homework21.service;

import org.islom.homework21.enity.Company;
import org.islom.homework21.enity.Department;
import org.islom.homework21.payload.ApiResponse;
import org.islom.homework21.payload.DepartmentDto;
import org.islom.homework21.repository.CompanyRepo;
import org.islom.homework21.repository.DepartmentRepo;

import java.util.List;
import java.util.Optional;

public class DepartmentService {

    DepartmentRepo repo;

    CompanyRepo companyRepo;

    public List<Department> getAll() {
        return repo.findAll();
    }

    public Department getById(int id) {
        Optional<Department> byId = repo.findById(id);
        return byId.orElse(byId.get());
    }

    public ApiResponse createDeparatment(DepartmentDto departmentDto) {
        /// Kompaniya Idsi keladi va usha kompaniyani belgilab beramiz
        Optional<Company> byId1 = companyRepo.findById(departmentDto.getCompanyId());
        if (!byId1.isPresent()) {
            return new ApiResponse("Company topilmadi", false);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(byId1.get());
        repo.save(department);
        return new ApiResponse("hammasi yakunlandi", true);
    }

    public ApiResponse update(DepartmentDto departmentDto, int id) {
        Optional<Department> byId = repo.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Xatolik", false);
        Department department = byId.get();
        department.setName(departmentDto.getName());
        /// Kompaniya Idsi keladi va usha kompaniyani belgilab beramiz
        Optional<Company> byId1 = companyRepo.findById(departmentDto.getCompanyId());
        if (!byId1.isPresent()) {
            return new ApiResponse("Company topilmadi", false);
        }
        department.setCompany(byId1.get());
        repo.save(department);
        return new ApiResponse("hammasi yakunlandi", true);

    }

    public ApiResponse delete(int id) {
        Optional<Department> byId = repo.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Departament topilmadi", false);
        }
        repo.deleteById(id);
        return new ApiResponse("hammasi yakunlandi", true);
    }

}
