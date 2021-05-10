package com.example.demo;

import com.example.demo.controller.TaskController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private TaskController controller;

	@Test
	public void contextLoads() throws Exception {
		Assert.notNull(controller, "Controller not autowired");
	}
}
