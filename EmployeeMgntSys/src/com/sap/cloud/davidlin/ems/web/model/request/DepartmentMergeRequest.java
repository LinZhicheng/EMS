package com.sap.cloud.davidlin.ems.web.model.request;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class DepartmentMergeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4484067582382572942L;

	@NotBlank
	@Length(min = 3, max = 20)
	private String name;

	@Min(1)
	private Long managerId;

	public DepartmentMergeRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

}
