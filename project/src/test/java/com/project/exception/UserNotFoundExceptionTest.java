package com.project.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserNotFoundExceptionTest {
	
	@InjectMocks
	private UserNotFoundException userNotFound;
	
	@Test
	void UserNotFoundException()
	{
		userNotFound.getMessage();
	}
}
