package com.org.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;

import java.util.concurrent.atomic.AtomicLong;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.project.config.WebConfig;
import com.org.project.db.UserRepository;
import com.org.project.model.User;
import com.org.project.model.UserModel;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);


    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Mr. %s";
    
    @Autowired
    private UserRepository userRepository; 

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users"  
    )
    public List<User> getAllUsers() {
        System.out.println("Get users ");
        logger.info("get users from database");
        return userRepository.findAll();
    }

    @RequestMapping(
    method = RequestMethod.GET, 
    value = "/user", 
    produces = "application/json; charset=UTF-8",
    // consumes = "text/plain; charset=UTF-8",
    path = "/user"
    )
    @Operation(
		summary = "Get user data",
		description = "Get information from user model",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Get user data", 
                useReturnTypeSchema = true,
                links=@Link(
                    name="user", description = "User Id", 
                    parameters=@LinkParameter(name="kkk"), 
                    operationId = "123",
                    server=@Server(
                        url = "http://localhost",
                        description = "Uri address"
                        )
                    )),
            @ApiResponse(responseCode = "404", description = "Data not found", useReturnTypeSchema=false),
            @ApiResponse(responseCode = "415", description = "Data types unsopported", useReturnTypeSchema=false)
        }
	)
    @ApiResponse(responseCode = "415", description = "Data types unsopported", useReturnTypeSchema=false)
    @ApiResponse(responseCode = "403", description = "Data types unsopported", useReturnTypeSchema=true)
    @ResponseStatus(HttpStatus.OK)
    public UserModel user(
        @RequestParam(value = "name", defaultValue = "John") String firstname,
        @RequestParam(value = "lastname", defaultValue = "Smith") String lastname,
        @RequestParam(value = "email", defaultValue = "examle@mail.com") String email
    ){
        
        // return new UserModel(counter.incrementAndGet(), String.format(template, firstname), LastName, null);
        return new UserModel(counter.incrementAndGet(), firstname, lastname, email);
    }

        
    @RequestMapping(
    method = RequestMethod.POST, 
    value = "/user", 
    produces = "application/json; charset=UTF-8",
    consumes = "application/json; charset=UTF-8" ,
    path = "/user"
    )
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel usercreate(){
        return new UserModel(0, null, null, null);
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
