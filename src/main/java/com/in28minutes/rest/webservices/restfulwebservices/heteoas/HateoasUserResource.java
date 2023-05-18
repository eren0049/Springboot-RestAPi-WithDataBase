package com.in28minutes.rest.webservices.restfulwebservices.heteoas;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
 * HATEOAS: Hypermedia as the Engine of Application States 
 * 	-Implementation options:
 * 		1- Custom Format & Implementation
 * 			- difficult to maintain
 * 		2- Use Standard Implementation
 * 			- Hal (JSOn Hypertext Application Language)
 * 				it is simple format that gives a consistent and easy way to 
 * 				hyperlink between resources in your API.
 * 			- Spring HATEOAS:
 * 				generates Hal responses w/ hyperlinks to resources. 
 * 
 */
@RestController //It is an Annotation for REST API
public class HateoasUserResource {
	
	//	----- Variables -----	//
	private final HateoasUserDaoService service;

	//	----- Methods -----	//
	public HateoasUserResource(HateoasUserDaoService service) {
		super();
		this.service = service;
	}

	/**
	 * gets all Users to "http://localhost:8080/"
	 * @return
	 */
	@GetMapping("/hateoasusers")
	public List<HateoasUser> retrieveAllUsers() {
		return service.findAll();
	}

	/**
	 * gets only matched Users to "http://localhost:8080/"
	 * with Entitymodel: basically you are wrapping the HateoasUser Class and using EntityModel 
	 */
	@GetMapping("/heteoasusers/{id}") // in Parathases the Path is written
	public EntityModel<HateoasUser> retrieveUser(@PathVariable int id) {
		HateoasUser user  = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id:" + id);
	
		EntityModel<HateoasUser> entityModel= EntityModel.of(user);
		
		
		//to this method to point the Controller Method. In other word to Link
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		
		// to add Link to entityModel
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	/**
	 * It is kind of Setters 
	 */
	//Post /users
	@PostMapping("/heteoasusers")
	public ResponseEntity<HateoasUser> createUser(@Valid @RequestBody HateoasUser user) {
		HateoasUser savedUser = service.save(user);
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
	@DeleteMapping("/heteoasusers/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteByID(id);
	}
}
