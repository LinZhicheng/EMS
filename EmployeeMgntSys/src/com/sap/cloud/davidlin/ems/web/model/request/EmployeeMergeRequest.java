package com.sap.cloud.davidlin.ems.web.model.request;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.sap.cloud.davidlin.ems.persistence.model.Position;

public class EmployeeMergeRequest implements Serializable {

	private static final long serialVersionUID = 4733824404995544942L;

	@NotBlank
	@Length(min = 3, max = 20)
	private String firstName;

	@NotBlank
	@Length(min = 3, max = 20)
	private String lastName;

	private Position position = Position.OFFICER;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String photoId;

	@Min(1)
	private Long departmentId;

	public EmployeeMergeRequest() {

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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
