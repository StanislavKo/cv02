package com.cv.eagle6.depuser.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
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
import com.cv.eagle6.depuser.db.dto.UserDto;
import com.cv.eagle6.depuser.db.entity.Department;
import com.cv.eagle6.depuser.db.entity.User;
import com.cv.eagle6.depuser.db.repository.DepartmentRepository;
import com.cv.eagle6.depuser.db.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RedisBean cacheBean;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private UserRepository userRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public AbstractResponse list() {
		String cacheKey = "users";
		String jsonArray = Optional.ofNullable(cacheBean.getValue(cacheKey)).orElseGet(() -> cacheBean.setValue(cacheKey, getUsersImpl()));
		Type listType = new TypeToken<ArrayList<User>>(){}.getType();
		List<User> userList = new Gson().fromJson(jsonArray, listType);
		return new DataResponse(ResponseCodes.OK, userList);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/users/{depId}/{letters}" }, method = RequestMethod.GET)
	public AbstractResponse listFilter(@PathVariable Long depId, @PathVariable String letters) throws UnsupportedEncodingException {
		String cacheKey = "usersFilter" + depId + "_" + URLEncoder.encode(letters, "UTF-8");
		String jsonArray = Optional.ofNullable(cacheBean.getValue(cacheKey)).orElseGet(() -> cacheBean.setValue(cacheKey, getUsersFilterImpl(depId, letters)));
		Type listType = new TypeToken<ArrayList<User>>(){}.getType();
		List<User> userList = new Gson().fromJson(jsonArray, listType);
		return new DataResponse(ResponseCodes.OK, userList);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/user" }, method = RequestMethod.POST)
	public AbstractResponse add(@RequestBody UserDto userDto) {
		try {
			Long depId = Optional.ofNullable(userDto.getDepartment()).map(d -> d.getId()).orElse(-1l);
			Optional<Department> depOp = departmentRepository.findById(depId);
			User user = new User();

			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setDepartment(depOp.orElse(null));
			userRepository.save(user);
			return new DataResponse(ResponseCodes.OK, DtoConverter.getUserDto(user, true));
		} catch (Throwable e) {
			logger.error("add failed", e);
			return new ErrorResponse(ResponseCodes.INTERNAL_ERROR, e.getClass().getSimpleName() + ", " + e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/user/{id}" }, method = RequestMethod.PUT)
	public AbstractResponse edit(@PathVariable Long id, @RequestBody UserDto userDto) {
		try {
			Optional<User> userOp = userRepository.findById(id);
			if (userOp.isPresent()) {
				Long depId = Optional.ofNullable(userDto.getDepartment()).map(d -> d.getId()).orElse(-1l);
				Optional<Department> depOp = departmentRepository.findById(depId);

				User user = userOp.get();
				user.setFirstName(userDto.getFirstName());
				user.setLastName(userDto.getLastName());
				user.setDepartment(depOp.orElse(null));
				userRepository.save(user);
				return new DataResponse(ResponseCodes.OK, DtoConverter.getUserDto(user, true));
			} else {
				return new ErrorResponse(ResponseCodes.USER_IS_NOT_FOUND_ERROR, "User is not found");
			}
		} catch (Throwable e) {
			logger.error("edit failed", e);
			return new ErrorResponse(ResponseCodes.INTERNAL_ERROR, e.getClass().getSimpleName() + ", " + e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/user/{id}" }, method = RequestMethod.GET)
	public AbstractResponse get(@PathVariable Long id) {
		try {
			String cacheKey = "user" + id;
			String json = Optional.ofNullable(cacheBean.getValue(cacheKey)).orElseGet(() -> cacheBean.setValue(cacheKey, getUserImpl(id)));
			UserDto userDto = new Gson().fromJson(json, UserDto.class);
			return new DataResponse(ResponseCodes.OK, userDto);
		} catch (Throwable e) {
			logger.error("get failed", e);
			return new ErrorResponse(ResponseCodes.INTERNAL_ERROR, e.getClass().getSimpleName() + ", " + e.getMessage());
		}
	}

	// ********* implementation ************
	
	private String getUsersImpl() {
		Iterable<User> usersIt = userRepository.findAll();
		List<User> users = new LinkedList<>();
		usersIt.forEach(users::add);
		Gson gson = new Gson();
		return gson.toJson(DtoConverter.getUserDtos(users, true));
	}

	private String getUsersFilterImpl(Long depId, String letters) {
		Iterable<User> usersIt = userRepository.findFilter(depId, "%" + letters + "%");
		List<User> users = new LinkedList<>();
		usersIt.forEach(users::add);
		Gson gson = new Gson();
		return gson.toJson(DtoConverter.getUserDtos(users, true));
	}

	private String getUserImpl(Long id) {
		Optional<User> userOp = userRepository.findById(id);
		Gson gson = new Gson();
		return userOp.isPresent() ? gson.toJson(DtoConverter.getUserDto(userOp.get(), true)) : null;
	}

}