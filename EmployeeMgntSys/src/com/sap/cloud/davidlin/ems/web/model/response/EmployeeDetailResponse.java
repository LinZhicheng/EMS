package com.sap.cloud.davidlin.ems.web.model.response;

import java.io.Serializable;

import com.sap.cloud.davidlin.ems.persistence.model.Department;
import com.sap.cloud.davidlin.ems.persistence.model.Employee;
import com.sap.cloud.davidlin.ems.persistence.model.Position;

public class EmployeeDetailResponse implements Serializable {

	private static final long serialVersionUID = -5071677355391219807L;

	private long id;

	private String firstName;

	private String lastName;

	private Position position;

	private String phoneNumber;

	private String photoId;

	private Department department;

	public EmployeeDetailResponse() {

	}

	public EmployeeDetailResponse(Employee employee) {
		this.setId(employee.getId());
		this.setFirstName(employee.getFirstName());
		this.setLastName(employee.getLastName());
		this.setPhoneNumber(employee.getPhoneNumber());
		this.setPosition(employee.getPosition());
		this.setPhotoId(employee.getPhotoId());
		this.setDepartment(employee.getDepartment());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
