package com.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.project.dao.UserDao;
import com.project.dto.UserRequest;
import com.project.entity.User;
import com.project.exception.UserNotFoundException;
import com.project.utils.Constants;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserServiceImpl userService;

	private UserRequest userRequest;

	private UserNotFoundException userNotFoundException;

	@BeforeEach
	void setup() {
		userRequest = new UserRequest();
		userRequest.setName("Shreyas");
		userRequest.setAge(23);
		userRequest.setEmail("shreyas@gmail.com");
		userRequest.setPhone(9876543125L);
	}

	@DisplayName("Service success test for addUser")
	@Test
	void unitTest_addUser_success() {

		User user = new User(1, "Shreyas", 23, "shreyas@gmail.com", 9876543125L);
		ResponseEntity<User> expected = ResponseEntity.ok(user);
		Mockito.when(userDao.save(Mockito.any())).thenReturn(user);
		ResponseEntity<User> actual = userService.addUser(userRequest);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("Service success test for getAllUser")
	@Test
	void unitTest_getAllUsers_success() {

		List<User> users = new ArrayList<>();
		ResponseEntity<List<User>> expected = ResponseEntity.ok(users);
		users.add(new User(1, "shreyas", 23, "shreyas@gmail.com", 987654321L));
		users.add(new User(2, "tariq", 22, "tariq@gmail.com", 6362355944L));
		Mockito.when(userDao.findAll()).thenReturn(users);

		ResponseEntity<List<User>> actual = userService.getAllUsers();

		assertThat(actual.getBody()).isEqualTo(expected.getBody());
	}

	@DisplayName("Service success test for getUserById")
	@Test
	void unitTest_getUserById_success() {

		int id = 1;
		User user = new User(1, "Shreyas", 23, "shreyas@gmail.com", 9876543215L);
		ResponseEntity<User> expected = ResponseEntity.ok(user);
		Mockito.when(userDao.findById(id)).thenReturn(Optional.of(user));

		ResponseEntity<User> actual = userService.getUserById(id);

		assertThat(actual.getBody()).isEqualTo(expected.getBody());

	}

	@DisplayName("Service failure test for getUserById")
	@Test
	void uniTest_getUserById_failure() {
		int id = 1;
		Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(id), Constants.USERNOTFOUND);
	}

	@DisplayName("Service success test for updateUser")
	@Test
	void unitTest_updateUser_success() {

		int id = 1;
		User user = new User(1, "Shreyas", 23, "shreyas180@gmail.com", 9876543125L);
		Mockito.when(userDao.findById(id)).thenReturn(Optional.of(user));
		Mockito.when(userDao.save(Mockito.any())).thenReturn(user);

		ResponseEntity<User> actual = userService.updateUser(id, userRequest);

		assertThat(actual.getBody().getEmail()).isEqualTo("shreyas@gmail.com");
	}

	@DisplayName("Service failure test for updateUser")
	@Test
	void unitTest_updateUser_failure() {

		User user = new User();
		Assertions.assertThrows(UserNotFoundException.class, () -> userService.updateUser(1, userRequest));
		verify(userDao, never()).save(user);
	}

	@DisplayName("Service success test for deleteUser")
	@Test
	void unitTest_deleteUser_success() {
		int id = 1;
		Mockito.when(userDao.findById(id)).thenReturn(Optional.of(new User()));
		willDoNothing().given(userDao).deleteById(id);
		userService.deleteUser(id);
		verify(userDao, times(1)).deleteById(id);
	}

	@DisplayName("Service failure test for deleteUser")
	@Test
	void unitTest_deleteUser_failure() {

		int id=1;
		Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(id), Constants.USERNOTFOUND);

		verify(userDao, never()).deleteById(id);

	}
}
