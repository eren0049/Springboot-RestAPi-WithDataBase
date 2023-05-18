package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.in28minutes.rest.webservices.restfulwebservices.h2console.H2User;
import com.in28minutes.rest.webservices.restfulwebservices.h2console.Post;

import jakarta.validation.Valid;

/**
 * This is the Class where Logic goes.
 * 
 */
@RestController // It is an Annotation for REST API
public class UserJpaResource {

	// ----- Variables ----- //
	
	private final UserJpaRepository userRepository;

	private final PostRepository postRepository;

	// ----- Methods ----- //
	public UserJpaResource(UserJpaRepository repository, PostRepository postRepository) {
		super();
		this.userRepository = repository;
		this.postRepository=postRepository;
	}

	/**
	 * gets all Users to "http://localhost:8080/"
	 * 
	 * @return
	 */
	@GetMapping("/jpa/users")
	public List<H2User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * gets only matched Users to "http://localhost:8080/"
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/jpa/users/{id}") // in Parenthesis the Path is written
	public EntityModel<H2User> retrieveUser(@PathVariable int id) {
		Optional<H2User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		
		EntityModel<H2User> entityModel = EntityModel.of(user.get());

		// to this method to point the Controller Method. In other word to Link
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		// to add Link to entityModel
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}

	/**
	 * It is kind of Setters
	 */
	// Post /users
	@PostMapping("/jpa/users")
	public ResponseEntity<H2User> createUser(@Valid @RequestBody H2User user) {
		H2User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * Deletes the User with given ID
	 * 
	 * @param id
	 */
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	//---		/*	Post Rest API 	*/

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id) {
		Optional<H2User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post ) {
		Optional<H2User> user = userRepository.findById(id);  // checks if user exists
		if (user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		post.setUser(user.get());

		Post savedPost= postRepository.save(post); //maps post to the User and saves

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	
}
