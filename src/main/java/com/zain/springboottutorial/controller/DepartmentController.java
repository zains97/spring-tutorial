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
    public ResponseEntity<List<Department>>  getAll(){
        List<Department> departments = departmentService.getAll();
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        Optional<Department> department = departmentService.getById(id);
        if(department.isEmpty()){
            return new ResponseEntity("Could not find user for id",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department.get(),HttpStatus.OK) ;
    }

    Response res = new Response();

    @PostMapping
    public ResponseEntity save(@RequestBody Department department){
        return ResponseEntity.status(200).body(departmentService.save(department));
    }
}
