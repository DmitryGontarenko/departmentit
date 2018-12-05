package com.accenture.entity.employeepc;

import com.accenture.entity.employee.Employee;
import com.accenture.entity.pc.Pc;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee_pc")
public class EmployeePc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pc_id")
    private Pc pc;
}
