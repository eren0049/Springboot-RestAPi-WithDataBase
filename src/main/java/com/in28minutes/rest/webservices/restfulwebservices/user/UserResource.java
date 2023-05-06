package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * This is the Class where Logic goes. 
 * 
 */
@RestController //It is an Annotation for REST API
public class UserResource {
	
	//	----- Variables -----	//
	private UserDaoService service;

	//	----- Methods -----	//
	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}

	/**
	 * gets all Users to "http://localhost:8080/"
	 * @return
	 */
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	/**
	 * gets only matched Users to "http://localhost:8080/"
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}") // in Parathases the Path is written
	public User retrieveUser(@PathVariable int id) {
		User user  = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id:" + id);
		return user;
	}
	
	/**
	 * It is kind of Setters 
	 * @param user
	 * @return
	 */
	//Post /users
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	/**
	 * Deletes the User with given ID
	 * @param id
	 */
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteByID(id);
	}
}
