package com.project.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.project.dto.UserRequest;
import com.project.entity.User;
import com.project.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private UserServiceImpl userService;

	@InjectMocks
	private UserController userController;

	UserRequest userRequest = new UserRequest();

	@BeforeEach
	void setup() {
		userRequest.setName("Shreyas");
		userRequest.setAge(23);
		userRequest.setEmail("shreyas@gmail.com");
		userRequest.setPhone(9876543125L);
	}

	@DisplayName("Controller success test for addUser")
	@Test
	void unitTest_addUser_success() {

		User user = new User(1, "Shreyas", 23, "shreyas@gmail.com", 9876543125L);
		ResponseEntity<User> expected = ResponseEntity.ok(user);
		Mockito.when(userService.addUser(userRequest)).thenReturn(expected);

		ResponseEntity<User> actual = userController.addUser(userRequest);

		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("Controller success test for getAllUsers")
	@Test
	void unitTest_getAllUsers() {

		List<User> list = new ArrayList<>();
		ResponseEntity<List<User>> expected = ResponseEntity.ok(list);
		Mockito.when(userService.getAllUsers()).thenReturn(expected);

		ResponseEntity<List<User>> actual = userController.getUsers();

		assertThat(actual.getStatusCode()).isEqualTo(expected.getStatusCode());
	}

	@DisplayName("Controller success test for getUserById")
	@Test
	void unitTest_getUserById() {

		int id = 1;
		User user = new User(1, "Shreyas", 23, "shreyas@gmail.com", 9876543215L);
		ResponseEntity<User> expected = ResponseEntity.ok(user);
		Mockito.when(userService.getUserById(id)).thenReturn(expected);

		ResponseEntity<User> actual = userController.getUserById(id);

		assertThat(actual.getBody()).isEqualTo(expected.getBody());

	}

	@DisplayName("Controller success test for updateUser")
	@Test
	void unitTest_updateUser() {
		int id = 1;
		User user = new User(1, "Shreyas", 23, "shreyas@gmail.com", 9876543125L);
		user.setEmail("shreyas180@gmail.com");
		ResponseEntity<User> expected = ResponseEntity.ok(user);
		Mockito.when(userService.updateUser(id, userRequest)).thenReturn(expected);

		ResponseEntity<User> actual = userController.updateUser(id, userRequest);

		assertThat(actual.getBody().getEmail()).isEqualTo("shreyas180@gmail.com");
	}

	@DisplayName("Controller success test for deleteUser")
	@Test
	void unitTest_deleteUser() {

		int id = 1;
		ResponseEntity<String> expected = ResponseEntity.ok("Deleted successfully");
		Mockito.when(userService.deleteUser(id)).thenReturn(expected);

		ResponseEntity<String> actual = userController.deleteUser(id);

		assertThat(actual.getBody()).isEqualTo(expected.getBody());
	}

}
