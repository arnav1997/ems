package com.infotrode.ems.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
@EqualsAndHashCode(exclude = "employees")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_desc")
    private String deptDesc;

//    @OneToMany(mappedBy = "department")
//    private List<Employee> employees = new ArrayList<>();
}
