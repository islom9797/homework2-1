package org.islom.homework21.controller;

import org.islom.homework21.enity.Worker;
import org.islom.homework21.payload.ApiResponse;
import org.islom.homework21.payload.WorkerDto;
import org.islom.homework21.service.WorkerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    WorkerService workerService;

    @GetMapping
    public List<Worker> getWorkers() {
        return workerService.getWorkers();
    }

    @PostMapping
    public HttpEntity<?> addWorker(@RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);

    }

    @PostMapping("/{id}")
    public HttpEntity<?> updateWorker(@RequestBody WorkerDto workerDto, @PathVariable int id) {
        ApiResponse apiResponse = workerService.updateWorker(id, workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorker(@PathVariable int id) {
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
