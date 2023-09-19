package com.infotrode.ems.service;

import com.infotrode.ems.dto.DepartmentDTO;
import com.infotrode.ems.entity.Department;
import com.infotrode.ems.exception.ResourceNotFoundException;
import com.infotrode.ems.mapper.DepartmentMapper;
import com.infotrode.ems.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.mapToDepartment(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Department retrievedDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist!"));
        DepartmentDTO departmentDTO = DepartmentMapper.mapToDepartmentDTO(retrievedDepartment);
        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> retrievedDepartments = departmentRepository.findAll();
        return retrievedDepartments.stream().map((department) -> DepartmentMapper.mapToDepartmentDTO(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateDepartmentById(Long departmentId, DepartmentDTO departmentDTO) {
        Department retrievedDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist!"));
        retrievedDepartment.setDeptName(departmentDTO.getDeptName());
        retrievedDepartment.setDeptDesc(departmentDTO.getDeptDesc());
        Department updatedDepartment = departmentRepository.save(retrievedDepartment);
        return DepartmentMapper.mapToDepartmentDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        Department retrievedDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist!"));
        departmentRepository.deleteById(departmentId);
    }
}
