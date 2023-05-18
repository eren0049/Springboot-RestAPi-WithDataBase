package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Why versioning?
 * to avoid: URI Pollution, Misuse of Http headers, Caching 
 *
 */
@RestController
public class VersioningPersonController {

	/**
	 * Version: URI 
	 * Style: Twitter 
	 * URI: http://localhost:8080/v1/person
	 */
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	/**
	 * Version: Request Parameter 
	 * Style: Amazon URI:
	 * http://localhost:8080/person?version=1
	 * @return
	 */
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequest() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	/**
	 * Version: Request Header 
	 * Style: Microsoft 
	 * URI: http://localhost:8080/person ->then in header
	 * @return
	 */
	@GetMapping(path = "/person", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	
	/**
	 * Version: Media type 
	 * Style: Github 
	 * URI: http://localhost:8080/person  -> accept
	 * @return
	 */
	@GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	
	@GetMapping(path = "/person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
}
