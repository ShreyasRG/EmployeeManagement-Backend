package com.project.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionUnitTest {
	
	@BeforeEach
	void setup() 
	{
		error = new ApiError();
		error.setErrorMessage("User not found");
		error.setErrorCode("404");
		error.setTimeStamp(new Date());
		
	}
	
	@Mock
	private UserNotFoundException userNotFound;
	
	@InjectMocks
	private GlobalException globalException;
	
	private ApiError error;
	
	@Test
	void unitTestResourceNotFound() {
		ResponseEntity<ApiError> response =  globalException.resourceNotFoundException(userNotFound);
		assertNotNull(response);
	}
}
