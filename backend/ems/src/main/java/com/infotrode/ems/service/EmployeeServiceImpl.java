package com.infotrode.ems.service;

import com.infotrode.ems.dto.EmployeeDTO;
import com.infotrode.ems.entity.Department;
import com.infotrode.ems.entity.Employee;
import com.infotrode.ems.exception.ResourceNotFoundException;
import com.infotrode.ems.mapper.EmployeeMapper;
import com.infotrode.ems.repository.DepartmentRepository;
import com.infotrode.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Department department = departmentRepository.findById(employeeDTO.getDeptId())
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id: " + employeeDTO.getDeptId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee retrievedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + employeeId));
        EmployeeDTO employeeDTO = EmployeeMapper.mapToEmployeeDTO(retrievedEmployee);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> retrievedEmployees = employeeRepository.findAll();
        return retrievedEmployees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        Employee retrievedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + employeeId));
        retrievedEmployee.setFirstName(employeeDTO.getFirstName());
        retrievedEmployee.setLastName(employeeDTO.getLastName());
        retrievedEmployee.setEmail(employeeDTO.getEmail());
        Department department = departmentRepository.findById(employeeDTO.getDeptId())
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id: " + employeeDTO.getDeptId()));
        retrievedEmployee.setDepartment(department);
        Employee updatedEmployee = employeeRepository.save(retrievedEmployee);
        return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        Employee retrievedEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
