package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * in this example, it was tried to static filtering w/ @JsonIgnore in field. 
 * There is an option for @JsonIgnoreProperties for writing above Class
 */
@RestController    // for Rest Api
public class FilteringController {
	
	/*		Static filtering 		*/
	@RequestMapping(path="/static-filtering")  // for getMapping
	public SomeBean somebean() {
		return new SomeBean("1","2","3");
	}
	
	@GetMapping("/static-filtering-list") 
	public List<SomeBean> filteringlist(){
		return Arrays.asList(
				new SomeBean("value1","value2","value3"),
				new SomeBean("value4","value5","value6"));
	}
	
	/*		Dynamic filtering 		*/
	
	@RequestMapping(path="/dynamic-filtering")  // for getMapping
	public MappingJacksonValue filtering() {
		SomeBean someBean =new SomeBean("value1","value2","value3");
		
		MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3"); //instead of fields, u can use list as well
		
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBEanFilter",filter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	

}
