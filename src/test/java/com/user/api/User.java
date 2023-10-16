package com.user.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO Class--using Lambok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //chain-concept like Actions class in Selenium
public class User {
	
	//if you want to give your own names
	
//	@JsonProperty("name")
//	private String username;
	//Better stick with request body name only 

	private String name;
	private String email;
	private String gender;
	private String status;
	

	//This will be combined POJO so have to add response's "ID" also
	private Integer id;


	//create custom const. which will ignore this ID
	public User(String name, String email, String gender, String status) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
		
	
}
