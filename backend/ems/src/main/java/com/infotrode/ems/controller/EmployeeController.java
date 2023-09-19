package com.infotrode.ems.controller;

import com.infotrode.ems.dto.EmployeeDTO;
import com.infotrode.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor 
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees () {
        List<EmployeeDTO> retrievedEmployeeDTOs = employeeService.getAllEmployees();
        return new ResponseEntity<>(retrievedEmployeeDTOs, HttpStatus.OK);
    }

    @PostMapping    
    public ResponseEntity<EmployeeDTO> createEmployee (@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeByIdPV (@PathVariable(name = "id") Long employeeId) {
        EmployeeDTO retrievedEmployee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(retrievedEmployee, HttpStatus.OK);
    }

//    @GetMapping("")
//    public ResponseEntity<EmployeeDTO> getEmployeeByIdRP (@RequestParam(name = "id") Long employeeId) {
//        EmployeeDTO retrievedEmployee = employeeService.getEmployeeById(employeeId);
//        return new ResponseEntity<>(retrievedEmployee, HttpStatus.OK);
//    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeByIdPV (@PathVariable("id") Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeById(employeeId, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

//    @PutMapping
//    public ResponseEntity<EmployeeDTO> updateEmployeeByIdRP (@RequestParam("id") Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
//        EmployeeDTO updatedEmployee = employeeService.updateEmployeeById(employeeId, employeeDTO);
//        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeByIdPV (@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }

//    @DeleteMapping
//    public ResponseEntity<String> deleteEmployeeByIdRP (@RequestParam("id") Long employeeId) {
//        employeeService.deleteEmployeeById(employeeId);
//        return ResponseEntity.ok("Employee deleted successfully!");
//    }
}
