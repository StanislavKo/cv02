package com.cv.eagle6.depuser.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cv.eagle6.depuser.component.RedisBean;
import com.cv.eagle6.depuser.controller.pojo.AbstractResponse;
import com.cv.eagle6.depuser.controller.pojo.DataResponse;
import com.cv.eagle6.depuser.controller.pojo.ErrorResponse;
import com.cv.eagle6.depuser.controller.pojo.ResponseCodes;
import com.cv.eagle6.depuser.db.dto.DepartmentDto;
import com.cv.eagle6.depuser.db.dto.DtoConverter;
import com.cv.eagle6.depuser.db.entity.Department;
import com.cv.eagle6.depuser.db.repository.DepartmentRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private RedisBean cacheBean;

	@Autowired
	private DepartmentRepository departmentRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/departments" }, method = RequestMethod.GET)
	public AbstractResponse list() {
		String cacheKey = "departments";
		String jsonArray = Optional.ofNullable(cacheBean.getValue(cacheKey)).orElseGet(() -> cacheBean.setValue(cacheKey, getDepartmentsImpl()));
		Type listType = new TypeToken<ArrayList<Department>>(){}.getType();
		List<Department> userList = new Gson().fromJson(jsonArray, listType);
		return new DataResponse(ResponseCodes.OK, userList);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/department" }, method = RequestMethod.POST)
	public AbstractResponse add(@RequestBody DepartmentDto depDto) {
		try {
			Department dep = new Department();
			dep.setDepId(depDto.getDepId());
			dep.setTitle(depDto.getTitle());
			departmentRepository.save(dep);
			return new DataResponse(ResponseCodes.OK, DtoConverter.getDepartmentDto(dep, true));
		} catch (Throwable e) {
			logger.error("add failed", e);
			return new ErrorResponse(ResponseCodes.INTERNAL_ERROR, e.getClass().getSimpleName() + ", " + e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/department/{id}" }, method = RequestMethod.PUT)
	public AbstractResponse edit(@PathVariable Long id, @RequestBody DepartmentDto depDto) {
		try {
			Optional<Department> depOp = departmentRepository.findById(id);
			if (depOp.isPresent()) {
				Department dep = depOp.get();
				dep.setTitle(depDto.getTitle());
				departmentRepository.save(dep);
				return new DataResponse(ResponseCodes.OK, DtoConverter.getDepartmentDto(dep, true));
			} else {
				return new ErrorResponse(ResponseCodes.DEPARTMENT_IS_NOT_FOUND_ERROR, "Department is not found");
			}
		} catch (Throwable e) {
			logger.error("edit failed", e);
			return new ErrorResponse(ResponseCodes.INTERNAL_ERROR, e.getClass().getSimpleName() + ", " + e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/department/{id}" }, method = RequestMethod.GET)
	public AbstractResponse get(@PathVariable Long id) {
		try {
			String cacheKey = "department" + id;
			String json = Optional.ofNullable(cacheBean.getValue(cacheKey)).orElseGet(() -> cacheBean.setValue(cacheKey, getDepartmentImpl(id)));
			DepartmentDto depDto = new Gson().fromJson(json, DepartmentDto.class);
			return new DataResponse(ResponseCodes.OK, depDto);
		} catch (Throwable e) {
			logger.error("get failed", e);
			return new ErrorResponse(ResponseCodes.INTERNAL_ERROR, e.getClass().getSimpleName() + ", " + e.getMessage());
		}
	}

	// ********* implementation ************
	
	private String getDepartmentsImpl() {
		Iterable<Department> depsIt = departmentRepository.findAll();
		List<Department> deps = new LinkedList<>();
		depsIt.forEach(deps::add);
		Gson gson = new Gson();
		return gson.toJson(DtoConverter.getDepartmentDtos(deps, false));
	}

	private String getDepartmentImpl(Long id) {
		Optional<Department> depOp = departmentRepository.findById(id);
		Gson gson = new Gson();
		return depOp.isPresent() ? gson.toJson(DtoConverter.getDepartmentDto(depOp.get(), true)) : null;
	}

}