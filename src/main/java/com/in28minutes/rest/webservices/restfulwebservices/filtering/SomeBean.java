package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is just getter setter class
 */
public class SomeBean {
	
	private final String field1;
	
	@JsonIgnore
	private final String field2;
	
	
	private final String field3;
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	public String getField3() {
		return field3;
	}
	@Override
	public String toString() {
		return "Somebean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
	
	
	
	

}
