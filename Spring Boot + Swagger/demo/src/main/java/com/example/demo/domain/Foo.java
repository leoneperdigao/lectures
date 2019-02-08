package com.example.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author leone.perdigao
 *
 */
@ApiModel(value = "Foo Entity", description = "This is a sample entity from the domain package.")
public class Foo {

	@ApiModelProperty(value = "message", example="Property example")
	private String message;

	public Foo() {
		super();
	}

	public Foo(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
