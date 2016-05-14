package com.sap.cloud.davidlin.ems.web.model.response;

import java.io.Serializable;

import com.sap.cloud.davidlin.ems.persistence.model.Department;

public class DepartmentDetailResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6649721653343673958L;

	private long id;

	private String name;

	private EmployeeDetailResponse manager;

	public DepartmentDetailResponse() {
	}

	public DepartmentDetailResponse(Department department) {
		this.setId(department.getId());
		this.setName(department.getName());
		this.setManager(new EmployeeDetailResponse(department.getManager()));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmployeeDetailResponse getManager() {
		return manager;
	}

	public void setManager(EmployeeDetailResponse manager) {
		this.manager = manager;
	}
}
