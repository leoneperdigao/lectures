package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Foo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@Api(value="Hello World",
	authorizations = {
		  @Authorization(value="sampleoauth", scopes = {})
	})
@RequestMapping("api/v1")
public class HelloController {

	@ApiOperation(value = "Greets the world or BH",  notes = "Sample notes",
		    response = Foo.class,
		    responseContainer = "")
	@GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Foo hello(@RequestParam(required = false) boolean belzonte) {
		Foo greeting = new Foo("Hello world");
		if (belzonte) {
			greeting.setMessage(greeting.getMessage().replace("world", "BH"));
		}
		return greeting;
	}

	@ApiOperation(value = "Greets a person given her name")
	@GetMapping(value = "/hello/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Foo get(@PathVariable String name) {
		return new Foo("Hello, " + name);
	}
}


