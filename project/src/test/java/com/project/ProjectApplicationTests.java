package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProjectApplicationTests {

	@InjectMocks
	ProjectApplication projectMain;
	
	@Test
	void contextLoads() {
		ProjectApplication.main(new String[]{ "a", "b", "c" });
	}

}
