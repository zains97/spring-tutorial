package com.zain.springboottutorial.controller;

import com.zain.springboottutorial.entity.Department;
import com.zain.springboottutorial.service.DepartmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        List<Department> departments = departmentService.getAll();
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Department> department = departmentService.getById(id);
        if (department.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(department.get());
        }
        return new ResponseEntity<>(department.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Department department) {
        System.out.println("\n\n" + department.toString() + "\n\n");

        return ResponseEntity.status(200).body(departmentService.save(department));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return departmentService.deleteById(id)
                ?  ResponseEntity.status(200).body("Deleted department successfully!")
                : ResponseEntity.status(404).body("Department not found!");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity update(
            @PathVariable("id") Long id,
            @RequestBody Department updatedDepartment
    ){
        Department department = departmentService.updateDepartment(id,updatedDepartment);
        return  department != null
                ? ResponseEntity.status(200).body(department)
                : ResponseEntity.status(404).body("Failed to update department");
    }

    @GetMapping( path = "/name/{departmentName}")
    public ResponseEntity findbyDepartmentName(@PathVariable("departmentName") String departmentName){
        Optional<Department> department = departmentService.findByDepartmentName(departmentName);
        return department.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(department.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No department with such name.");
    }
}