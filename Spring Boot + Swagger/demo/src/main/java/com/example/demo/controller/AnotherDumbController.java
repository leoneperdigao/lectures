package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Foo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@Api(value="I'm another dumb api",
	authorizations = {
		  @Authorization(value="sampleoauth", scopes = {})
	})
public class AnotherDumbController {

	@ApiOperation(value = "Create foo object",  notes = "Sample notes",
		    response = Foo.class,
		    responseContainer = "")
	@PostMapping(value = "/dumb", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Foo create(@RequestParam(required = false) String message) {
		Foo greeting = new Foo(message);
		return greeting;
	}

	@ApiOperation(value = "Update a foo object")
	@PutMapping(value = "/dumb/{message}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Foo update(@PathVariable String message) {
		return new Foo("Dumb, " + message);
	}
	
	@ApiOperation(value = "Delete a foo object")
	@DeleteMapping(value = "/dumb", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void remove() {
	}
	
	@ApiOperation(value = "Do some shit")
	@PatchMapping(value = "/dumb", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void shitHappens() {
		throw new RuntimeException();
	}
}
