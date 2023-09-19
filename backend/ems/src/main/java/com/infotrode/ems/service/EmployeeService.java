package com.infotrode.ems.service;

import com.infotrode.ems.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee (EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById (Long employeeId);
    List<EmployeeDTO> getAllEmployees ();
    EmployeeDTO updateEmployeeById (Long employeeId, EmployeeDTO employeeDTO);
    void deleteEmployeeById (Long employeeId);
}
