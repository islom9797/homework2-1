package org.islom.homework21.controller;

import org.islom.homework21.payload.ApiResponse;
import org.islom.homework21.payload.DepartmentDto;
import org.islom.homework21.service.DepartmentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    DepartmentService departmentService;


    @GetMapping
    public HttpEntity<?> getDepartments() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @PostMapping
    public HttpEntity<?> addDepartments(@RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.createDeparatment(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateDepartments(@RequestBody DepartmentDto departmentDto,@PathVariable int id) {
        ApiResponse apiResponse = departmentService.update(departmentDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDepartments(@PathVariable int id) {
        ApiResponse apiResponse = departmentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
