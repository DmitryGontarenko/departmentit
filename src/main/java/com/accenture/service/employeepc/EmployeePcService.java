package com.accenture.service.employeepc;

import com.accenture.entity.employeepc.EmployeePc;
import com.accenture.repository.employeepc.EmployeePcRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeePcService {

    @Autowired
    private EmployeePcRepo employeePcRepo;

    public List<EmployeePc> findAll() {
        return employeePcRepo.findAll();
    }
}
