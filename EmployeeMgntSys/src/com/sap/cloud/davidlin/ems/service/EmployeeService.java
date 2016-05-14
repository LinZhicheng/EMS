package com.sap.cloud.davidlin.ems.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.cloud.davidlin.ems.persistence.model.Employee;
import com.sap.cloud.davidlin.ems.persistence.repository.EmployeeRepository;
import com.sap.cloud.davidlin.ems.web.model.request.EmployeeMergeRequest;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Valid
	public Employee findOneById(@NotNull @Min(1) Long id) {
		return employeeRepository.findOne(id);
	}

	public Employee createOne(@NotNull EmployeeMergeRequest employeeMergeRequest) {
		Employee employee = new Employee();
		mergeScalarProperties(employeeMergeRequest, employee);
		return employeeRepository.saveAndFlush(employee);
	}

	@Valid
	public Employee updateOne(@NotNull @Min(1) Long id, @NotNull EmployeeMergeRequest employeeMergeRequest) {
		Employee savedEmployee = employeeRepository.findOne(id);
		if (savedEmployee == null) {
			throw new IllegalArgumentException("id");
		}
		mergeScalarProperties(employeeMergeRequest, savedEmployee);
		return employeeRepository.saveAndFlush(savedEmployee);
	}

	@Valid
	public void deleteOneById(@NotNull @Min(1) Long id) {
		employeeRepository.delete(id);
	}

	private void mergeScalarProperties(EmployeeMergeRequest employeeMergeRequest, Employee savedEmployee) {
		savedEmployee.setFirstName(employeeMergeRequest.getFirstName());
		savedEmployee.setLastName(employeeMergeRequest.getLastName());
		savedEmployee.setPhoneNumber(employeeMergeRequest.getPhoneNumber());
		savedEmployee.setPhotoId(employeeMergeRequest.getPhotoId());
		savedEmployee.setPosition(employeeMergeRequest.getPosition());
	}
}
