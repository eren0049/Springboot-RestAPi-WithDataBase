package com.in28minutes.rest.webservices.restfulwebservices.customizing;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
/**
 * Only to use for User no Logic here.
 * Only Variables, Getter&Setters ,and ToString.
 * Annotation used only for sizing and Date limitation.
 */
public class CustomizeUser {
	
	//  --------- Variables ----------  //
	private Integer id;
	
	@JsonProperty("user_name")
	@Size(min=2, message="Name has to be at least 2 Characters.")   // for not null user name
	private String name;
	
	@JsonProperty("Birth_Date")
	@Past(message="Birth Date should be in the Past.")  // for accurate date for new User data
	private LocalDate birthDate;
		
	//  --------- Methods ----------  //
	public CustomizeUser(Integer id, String name, LocalDate birthDate) {
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
