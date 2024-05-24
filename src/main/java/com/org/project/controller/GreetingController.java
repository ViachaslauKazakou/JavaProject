package com.org.project.controller;

// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.model.Greeting;

@RestController
// @Api(value = "User Management System", description = "Operations pertaining to user in User Management System")
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	// @ApiOperation(value = "Get user details by ID")
	@Operation(
		summary = "Execute content",
		description = "Run get serach"
	)
	@ApiResponse
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@Operation(
		summary = "Greeting endpoint",
		description = "Hello word"
	)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/hello", 
		produces = "application/json; charset=UTF-8",
		consumes = "application/json; charset=UTF-8" ,
		path = "/hello"
	)
	public Greeting hello(@RequestParam(value = "name", defaultValue = "Java") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
