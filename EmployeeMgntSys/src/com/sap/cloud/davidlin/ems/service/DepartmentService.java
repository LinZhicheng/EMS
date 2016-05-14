package com.sap.cloud.davidlin.ems.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.cloud.davidlin.ems.persistence.model.Department;
import com.sap.cloud.davidlin.ems.persistence.model.Employee;
import com.sap.cloud.davidlin.ems.persistence.repository.DepartmentRepository;
import com.sap.cloud.davidlin.ems.persistence.repository.EmployeeRepository;
import com.sap.cloud.davidlin.ems.web.model.request.DepartmentMergeRequest;

@Service
@Transactional
public class DepartmentService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Valid
	public Department findOneById(@NotNull @Min(1) Long id) {
		return departmentRepository.findOne(id);
	}

	@Valid
	public Department createOne(@NotNull DepartmentMergeRequest departmentMergeRequest) {
		Department department = new Department();
		mergerScalarProperties(departmentMergeRequest, department);
		mergeManager(departmentMergeRequest, department);
		return departmentRepository.saveAndFlush(department);
	}

	@Valid
	public Department updateOne(@NotNull @Min(1) Long id, @NotNull DepartmentMergeRequest departmentMergeRequest) {
		Department savedDepartment = departmentRepository.findOne(id);
		if (savedDepartment == null) {
			throw new IllegalArgumentException("id");
		}
		mergerScalarProperties(departmentMergeRequest, savedDepartment);
		mergeManager(departmentMergeRequest, savedDepartment);
		return departmentRepository.saveAndFlush(savedDepartment);
	}

	@Valid
	public void deleteOneById(@NotNull @Min(1) Long id) {
		departmentRepository.delete(id);
	}

	private void mergeManager(DepartmentMergeRequest departmentMergeRequest, Department department) {
		if (departmentMergeRequest.getManagerId() != null) {
			Employee savedManager = employeeRepository.findOne(departmentMergeRequest.getManagerId());
			if (savedManager == null) {
				throw new IllegalArgumentException("managerId");
			}
			department.setManager(savedManager);
		}
	}

	private void mergerScalarProperties(DepartmentMergeRequest departmentMergeRequest, Department department) {
		department.setName(departmentMergeRequest.getName());
	}
}
