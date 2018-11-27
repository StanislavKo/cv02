package com.cv.eagle6.depuser;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.eagle6.depuser.component.RedisBean;
import com.cv.eagle6.depuser.controller.DepartmentController;
import com.cv.eagle6.depuser.controller.UserController;
import com.cv.eagle6.depuser.controller.pojo.AbstractResponse;
import com.cv.eagle6.depuser.controller.pojo.DataResponse;
import com.cv.eagle6.depuser.db.dto.DepartmentDto;
import com.cv.eagle6.depuser.db.dto.UserDto;
import com.cv.eagle6.depuser.db.entity.Department;
import com.cv.eagle6.depuser.db.entity.User;
import com.cv.eagle6.depuser.db.repository.DepartmentRepository;
import com.cv.eagle6.depuser.db.repository.UserRepository;
import com.google.gson.Gson;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.Silent.class)
public class EndpointsTest {

	private static final Logger logger = LoggerFactory.getLogger(EndpointsTest.class);

	@Mock
	private RedisBean cacheBean;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private DepartmentController departmentController;
	
	@InjectMocks
	private UserController userController;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Mockito.when(cacheBean.getValue("user1")).thenReturn("{\"id\":1,\"firstName\":\"firstName0Updated\",\"lastName\":\"姓名0Updated\",\"department\":{\"id\":1,\"depId\":383026,\"title\":\"department0Updated\",\"users\":null}}");
		Department dep = new Department();
		dep.setId(1l);
		dep.setDepId(100l);
		dep.setTitle("title");
		Optional<Department> depOp = Optional.of(dep);
		Mockito.when(departmentRepository.findById(1l)).thenReturn(depOp);
		Mockito.when(userRepository.save(Mockito.any(User.class))).then((InvocationOnMock invocation) -> {
			User user = invocation.getArgument(0);
			user.setId(200l);
			return user;
		});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_get() {
		AbstractResponse response = userController.get(1l);
		assertTrue(response instanceof DataResponse);
		String json = new Gson().toJson(((DataResponse) response).getData());
		System.out.println(json);
		assertTrue(json.equals("{\"id\":1,\"firstName\":\"firstName0Updated\",\"lastName\":\"姓名0Updated\",\"department\":{\"id\":1,\"depId\":383026,\"title\":\"department0Updated\"}}"));
	}

	@Test
	public void test02_add() {
		DepartmentDto depDto = new DepartmentDto();
		depDto.setId(1l);
		depDto.setDepId(100l);
		depDto.setTitle("title");
		UserDto userDto = new UserDto();
		userDto.setFirstName("firstName");
		userDto.setLastName("lastName");
		userDto.setDepartment(depDto);
		
		AbstractResponse response = userController.add(userDto);
		assertTrue(response instanceof DataResponse);
		String json = new Gson().toJson(((DataResponse) response).getData());
		assertTrue(json.equals("{\"id\":200,\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"department\":{\"id\":1,\"depId\":100,\"title\":\"title\"}}"));
	}

}
