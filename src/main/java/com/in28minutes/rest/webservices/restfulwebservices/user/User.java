package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
/**
 * Only to use for User no Logic here.
 * Only Variables, Getter&Setters ,and ToString.
 * Annotation used only for sizing and Date limitation.
 */
public class User {
	
	//  --------- Variables ----------  //
	private Integer id;
	
	@Size(min=2, message="Name has to be at least 2 Characters.")   // for not null user name
	private String name;
	
	@Past(message="Birth Date should be in the Past.")  // for accurate date for new User data
	private LocalDate birthDate;
		
	//  --------- Methods ----------  //
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return "User [birthDate=" + birthDate + ", id=" + id + ", name=" + name + "]";
	}
	
	
	
}
