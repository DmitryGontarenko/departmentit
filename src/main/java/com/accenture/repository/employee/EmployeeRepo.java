package com.accenture.repository.employee;

import com.accenture.entity.employee.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
