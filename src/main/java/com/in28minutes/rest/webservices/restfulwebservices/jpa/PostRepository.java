package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import com.in28minutes.rest.webservices.restfulwebservices.h2console.H2User;
import com.in28minutes.rest.webservices.restfulwebservices.h2console.Post;
import org.springframework.data.jpa.repository.JpaRepository;


/*
	-H2User Class is the one I used for H2Console.
	- in order to talk to Database, it will be used in the H2UserResource Class take a look there as well.
*/
public interface PostRepository extends JpaRepository<Post,Integer>{

}


