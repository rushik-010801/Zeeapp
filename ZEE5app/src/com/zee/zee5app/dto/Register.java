package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Register {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public Register() {
		
		System.out.println("hello from register");
	}
}
