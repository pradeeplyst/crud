package com.example.employee.service;

import com.example.employee.entity.EmployeeEntity;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeEntity> getAll() {
        return repository.findAll();
    }

    public EmployeeEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public EmployeeEntity save(EmployeeEntity employee) {
        return repository.save(employee);
    }

    public EmployeeEntity update(Long id, EmployeeEntity updated) {
        EmployeeEntity existing = getById(id);
        existing.setName(updated.getName());
        existing.setDepartment(updated.getDepartment());
        existing.setEmail(updated.getEmail());
        existing.setStatus(updated.getStatus());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
