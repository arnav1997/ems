package com.infotrode.ems.service;

import com.infotrode.ems.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment (DepartmentDTO departmentDTO);
    DepartmentDTO getDepartmentById (Long departmentId);
    List<DepartmentDTO> getAllDepartments ();
    DepartmentDTO updateDepartmentById (Long departmentId, DepartmentDTO departmentDTO);
    void deleteDepartmentById (Long departmentId);
}
