package com.sap.cloud.davidlin.ems.web.controller;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.davidlin.ems.persistence.model.Department;
import com.sap.cloud.davidlin.ems.service.DepartmentService;
import com.sap.cloud.davidlin.ems.web.model.request.DepartmentMergeRequest;
import com.sap.cloud.davidlin.ems.web.model.response.DepartmentDetailResponse;

@RestController
public class DepartmentController {

	private static final Transformer<Department, DepartmentDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Department, DepartmentDetailResponse>() {
		@Override
		public DepartmentDetailResponse transform(Department department) {
			return new DepartmentDetailResponse(department);
		}
	};

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public Collection<DepartmentDetailResponse> findAll() {
		return CollectionUtils.collect(departmentService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
	}

	@Valid
	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
	public DepartmentDetailResponse findOneById(@Min(1) @PathVariable("id") long id) {
		return new DepartmentDetailResponse(departmentService.findOneById(id));
	}

	@Valid
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public DepartmentDetailResponse createOne(@NotNull @RequestBody DepartmentMergeRequest departmentMergeRequest) {
		return new DepartmentDetailResponse(departmentService.createOne(departmentMergeRequest));
	}

	@Valid
	@RequestMapping(value = "/department/{id}", method = RequestMethod.PUT)
	public DepartmentDetailResponse updateOne(@Min(1) @PathVariable("id") long id,
			@NotNull @RequestBody DepartmentMergeRequest departmentMergeRequest) {
		return new DepartmentDetailResponse(departmentService.updateOne(id, departmentMergeRequest));
	}

	@Valid
	@RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
	public void deleteOneById(@Min(1) @PathVariable("id") long id) {
		departmentService.deleteOneById(id);
	}
}
