package com.sap.cloud.davidlin.ems.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.cloud.davidlin.ems.persistence.model.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
