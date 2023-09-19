package com.infotrode.ems.controller;

import com.infotrode.ems.dto.DepartmentDTO;
import com.infotrode.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments () {
        List<DepartmentDTO> retrievedDepartmentDTOs = departmentService.getAllDepartments();
        return new ResponseEntity<>(retrievedDepartmentDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment (@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentByIdPV (@PathVariable(name = "id") Long departmentId) {
        DepartmentDTO retrievedDepartment = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(retrievedDepartment, HttpStatus.OK);
    }

//    @GetMapping("")
//    public ResponseEntity<DepartmentDTO> getDepartmentByIdRP (@RequestParam(name = "id") Long departmentId) {
//        DepartmentDTO retrievedDepartment = departmentService.getDepartmentById(departmentId);
//        return new ResponseEntity<>(retrievedDepartment, HttpStatus.OK);
//    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDTO> updateDepartmentByIdPV (@PathVariable("id") Long departmentId, @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartmentById(departmentId, departmentDTO);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

//    @PutMapping
//    public ResponseEntity<DepartmentDTO> updateDepartmentByIdRP (@RequestParam("id") Long departmentId, @RequestBody DepartmentDTO DepartmentDTO) {
//        DepartmentDTO updatedDepartment = departmentService.updateDepartmentById(departmentId, DepartmentDTO);
//        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartmentByIdPV (@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.ok("Department deleted successfully!");
    }

//    @DeleteMapping
//    public ResponseEntity<String> deleteDepartmentByIdRP (@RequestParam("id") Long departmentId) {
//        departmentService.deleteDepartmentById(departmentId);
//        return ResponseEntity.ok("Department deleted successfully!");
//    }
}
