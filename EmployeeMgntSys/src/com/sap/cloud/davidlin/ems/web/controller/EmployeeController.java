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
import com.sap.cloud.davidlin.ems.persistence.model.Employee;
import com.sap.cloud.davidlin.ems.service.EmployeeService;
import com.sap.cloud.davidlin.ems.web.model.request.EmployeeMergeRequest;
import com.sap.cloud.davidlin.ems.web.model.response.EmployeeDetailResponse;

@RestController
public class EmployeeController {

	private Transformer<Employee, EmployeeDetailResponse> DETAIL_RESPONSE_TRANSFORMER = new Transformer<Employee, EmployeeDetailResponse>() {

		@Override
		public EmployeeDetailResponse transform(Employee input) {
			// TODO Auto-generated method stub
			return new EmployeeDetailResponse(input);
		}
	};

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Collection<EmployeeDetailResponse> findAll() {
		return CollectionUtils.collect(employeeService.findAll(), DETAIL_RESPONSE_TRANSFORMER);
	}

	@Valid
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public EmployeeDetailResponse findOneById(@Min(1) @PathVariable("id") long id) {
		return new EmployeeDetailResponse(employeeService.findOneById(id));
	}

	@Valid
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public EmployeeDetailResponse createOne(@NotNull @RequestBody EmployeeMergeRequest employeeMergeRequest) {
		return new EmployeeDetailResponse(employeeService.createOne(employeeMergeRequest));
	}

	@Valid
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public EmployeeDetailResponse updateOne(@Min(1) @PathVariable("id") long id,
			@NotNull @RequestBody EmployeeMergeRequest employeeMergeRequest) {
		return new EmployeeDetailResponse(employeeService.updateOne(id, employeeMergeRequest));
	}

	@Valid
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public void deleteOneById(@Min(1) @PathVariable("id") long id) {
		employeeService.deleteOneById(id);
	}
}
