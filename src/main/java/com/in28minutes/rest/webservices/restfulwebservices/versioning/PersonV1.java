package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import jakarta.validation.constraints.Size;

/**
 * it is created for versioning Rest API
 * it is the first version gives directly the name and Lastname of Person
 * No Logic only getters and Setters etc
 */
public class PersonV1 {
	
	@Size(min=2,message="Name should consist of at least 2 Characters.")
	private String name;

	public PersonV1(@Size(min = 2, message = "Name should consist of at least 2 Characters.") String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV1 [name=" + name + "]";
	}

}
