package com.user.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CreateUserTestWithLombok {
	
	@Test
	public void createUserTest() {
		
		//1st
		RestAssured.baseURI = "https://gorest.co.in";
	
		User user = new User("Ashu", getRandomEmailId(), "male", "active");
		
		//given() ---for that static imports
	Response response = 	given()
								.contentType(ContentType.JSON)
								.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
								.body(user)    //serialization
							.when()
								.post("/public/v2/users");
	
	//From response, we need 'id' for get call
	Integer userId = response.jsonPath().get("id");
		System.out.println("User ID: " + userId);
		
	//GET API to get the same user
	Response getResponse = 	given()
							.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
								.when()
									.get("/public/v2/users/" + userId);
	
	//De-serialization--convert JSON to POJO(Java Object)
	ObjectMapper mapper = new ObjectMapper();
	
	try {
		User userRes = mapper.readValue(getResponse.getBody().asString(), User.class); //check response if it's coming with normal {} then no need to put [] 
		
		System.out.println(userRes.getId() + ":" + userRes.getEmail() + ":" + userRes.getStatus() + ":" + userRes.getGender());
		
		//Help me to write proper assertions
		Assert.assertEquals(userId, userRes.getId());
		Assert.assertEquals(user.getName(), userRes.getName());
		Assert.assertEquals(user.getEmail(), userRes.getEmail());
		
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	} 
	
	//If you're using De-serialization you don't have to write Jayway JSON Path query, this is easy approach.
	
	}
	
	@Test
	public void createUser_With_BuilderPattern_Test() {
		
		//1st
		RestAssured.baseURI = "https://gorest.co.in";
	
	//Builder pattern--Lombok library	
	User user =	new User.UserBuilder()
			.name("Anshu")
			.email(getRandomEmailId())
			.status("active")
			.gender("male")
			.build();
		
		//given() ---for that static imports
	Response response = 	given()
								.contentType(ContentType.JSON)
								.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
								.body(user)    //serialization
							.when()
								.post("/public/v2/users");
	
	//From response, we need 'id' for get call
	Integer userId = response.jsonPath().get("id");
		System.out.println("User ID: " + userId);
		
	//GET API to get the same user
	Response getResponse = 	given()
							.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
								.when()
									.get("/public/v2/users/" + userId);
	
	//De-serialization--convert JSON to POJO(Java Object)
	ObjectMapper mapper = new ObjectMapper();
	
	try {
		User userRes = mapper.readValue(getResponse.getBody().asString(), User.class); //check response if it's coming with normal {} then no need to put [] 
		
		System.out.println(userRes.getId() + ":" + userRes.getEmail() + ":" + userRes.getStatus() + ":" + userRes.getGender());
		
		//Help me to write proper assertions
		Assert.assertEquals(userId, userRes.getId());
		Assert.assertEquals(user.getName(), userRes.getName());
		Assert.assertEquals(user.getEmail(), userRes.getEmail());
		
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	} 
	
	//If you're using De-serialization you don't have to write Jayway JSON Path query, this is easy approach.
	
	}
	
	public static String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@gmail.com";
	}

}
