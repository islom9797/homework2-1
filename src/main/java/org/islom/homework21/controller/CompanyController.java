package org.islom.homework21.controller;

import org.islom.homework21.enity.Company;
import org.islom.homework21.payload.ApiResponse;
import org.islom.homework21.payload.CompanyDto;
import org.islom.homework21.service.CompanyService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    CompanyService companyService;

    @GetMapping
    public HttpEntity<?> getCompany() {
        return companyService.getAllCompany();
    }

    @PostMapping
    public HttpEntity<?> addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateCompany(@RequestBody CompanyDto companyDto,@PathVariable int id) {
        ApiResponse apiResponse = companyService.updateCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCompany(@PathVariable int id) {
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
