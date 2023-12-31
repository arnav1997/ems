package com.infotrode.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String deptName;
    private String deptDesc;
}
