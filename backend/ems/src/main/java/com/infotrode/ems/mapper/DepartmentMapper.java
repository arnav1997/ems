package com.infotrode.ems.mapper;

import com.infotrode.ems.dto.DepartmentDTO;
import com.infotrode.ems.entity.Department;

public class DepartmentMapper {
    public static DepartmentDTO mapToDepartmentDTO (Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getDeptName(),
                department.getDeptDesc()
        );
    }

    public static Department mapToDepartment (DepartmentDTO departmentDTO) {
        return new Department(
                departmentDTO.getId(),
                departmentDTO.getDeptName(),
                departmentDTO.getDeptDesc()
        );
    }
}
