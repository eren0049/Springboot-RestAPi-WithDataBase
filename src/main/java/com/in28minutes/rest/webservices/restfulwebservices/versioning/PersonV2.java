package com.in28minutes.rest.webservices.restfulwebservices.versioning;
/**
 * it is created for versioning Rest API
 * it is the second version gives the name and Lastname of Person in different lines
 * No Logic only getters and Setters etc
 * COnnected w/ Name Class
 */
public class PersonV2 {
	
	// ----- Variables ---- //
	private Name name;
	
	// ----- Constructor ---- //
	public PersonV2(Name name) {
		super();
		this.name = name;
	}
	
	
	// ----- Methods ---- //
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}
}
