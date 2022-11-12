package com.zain.springboottutorial.service;

import com.zain.springboottutorial.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    Department save(Department department);

    List<Department> getAll();

    Optional<Department> getById(Long id);
}
