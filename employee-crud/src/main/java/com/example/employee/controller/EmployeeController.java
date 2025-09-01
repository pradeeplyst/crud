package com.example.employee.controller;

import com.example.employee.entity.EmployeeEntity;
import com.example.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all employees")
    public List<EmployeeEntity> getAll() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Create a new employee")
    public EmployeeEntity create(@RequestBody EmployeeEntity employee) {
        return service.save(employee);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public ResponseEntity<EmployeeEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee by ID")
    public ResponseEntity<EmployeeEntity> update(@PathVariable Long id, @RequestBody EmployeeEntity updated) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
