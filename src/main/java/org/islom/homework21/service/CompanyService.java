package org.islom.homework21.service;

import org.islom.homework21.enity.Address;
import org.islom.homework21.enity.Company;
import org.islom.homework21.payload.ApiResponse;
import org.islom.homework21.payload.CompanyDto;
import org.islom.homework21.repository.CompanyRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class CompanyService {

    CompanyRepo companyRepo;

    public HttpEntity<?> getAllCompany() {
        List<Company> all = companyRepo.findAll();
        return ResponseEntity.status(all.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(all);

    }

    public HttpEntity<?> getCompanyById(int id) {
        Optional<Company> company = companyRepo.findById(id);
        return ResponseEntity.status(company.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(company.get());
    }

    public HttpEntity<?> addCompany(Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyRepo.save(company));
    }

    public ApiResponse updateCompany(int id, CompanyDto companyDto) {
        Optional<Company> companyOptional = companyRepo.findById(id);

        if (!companyOptional.isPresent())
            return new ApiResponse("Bunday company Mavjud emas", false);


        Company companyToUpdate = companyOptional.get();
        Address address = companyToUpdate.getAddress();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        companyToUpdate.setAddress(address);
        companyToUpdate.setCorpName(companyDto.getCorpName());
        companyToUpdate.setDirectorName(companyDto.getDirectorName());
        companyRepo.save(companyToUpdate);
        return new ApiResponse("muvaffiyatli o'zgartirildi", true);


    }

    public ApiResponse deleteCompany(int id) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        if (!companyOptional.isPresent())
            return new ApiResponse("O'chirishda xatolik", false);
        companyRepo.deleteById(id);
        return new ApiResponse("O'chirildi", true);

    }
}
