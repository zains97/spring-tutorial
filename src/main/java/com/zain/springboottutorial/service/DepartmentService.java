package com.zain.springboottutorial.service;

import com.zain.springboottutorial.entity.Department;
import com.zain.springboottutorial.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            departmentRepository.deleteById(id);
            return true;
        } catch (DataAccessException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> department = departmentRepository.findById(id);

        if (updatedDepartment.isValid() && department.isPresent()) {
            department.get().setDepartmentName(updatedDepartment.getDepartmentName());
            department.get().setDepartmentAddress(updatedDepartment.getDepartmentAddress());
            department.get().setDepartmentCode(updatedDepartment.getDepartmentCode());
            return departmentRepository.save(department.get());
        }

        return null;
    }

    @Override
    public Optional<Department> findByDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }

}
