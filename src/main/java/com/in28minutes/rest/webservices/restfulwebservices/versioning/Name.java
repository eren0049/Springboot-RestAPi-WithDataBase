package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class Name {
//	----- Variables -----	//
	private String firstName;
	private String secondName;
	
	//	----- Constructors -----	//
	public Name(String firstName, String secondName) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
	}
	
	//	----- Methods -----	//

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	public String toString() {
		return "Name [firstName=" + firstName + ", secondName=" + secondName + "]";
	}

}
