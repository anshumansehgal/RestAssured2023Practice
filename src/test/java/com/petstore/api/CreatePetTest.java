package com.petstore.api;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.api.PetLombok.Category;
import com.petstore.api.PetLombok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CreatePetTest {
	
	@Test
	public void createPetTest() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		//For category class--create obj
		Category category = new Category(1, "Dog");
		
		//For photoUrl
		List<String> photoUrls = Arrays.asList("https://dog1.com", "https://dog2.com");
		
		//tag - create obj
		Tag tag1 = new Tag(10, "red");
		Tag tag2 = new Tag(15, "black");
		List<Tag> tags = Arrays.asList(tag1, tag2); 
		
		PetLombok pet = new PetLombok(300, category, "Ronny", photoUrls, tags , "available");
		
		Response respose = given()     //This API doesn't have any header so need to add that
								.contentType(ContentType.JSON)
								.body(pet) //serialization
						   .when()
						   	.post("/v2/pet");
		
		//can validate status code 
		System.out.println(respose.statusCode());
		
		//pretty print
		respose.prettyPrint(); //Mainly useful for debug purposes when writing tests. 
		
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			PetLombok petRes = mapper.readValue(respose.getBody().asString(), PetLombok.class);  //class obj.
			
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			//category
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getCategory().getName());
			
			//photoUrls
			System.out.println(petRes.getPhotoUrls());
			
			//tag
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());
			
			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//with builder pattern
	@Test
	public void createPet_With_BuilderPattern_Test() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
	//Category builder	
	Category category = new Category.CategoryBuilder()
			.id(400)
			.name("Animal")
			.build();
	
	//Tag builder
	Tag tag = new Tag.TagBuilder()
			.id(78)
			.name("Red")
			.build();
			
	Tag tag1 = new Tag.TagBuilder()
						.id(90)
						.name("Black")
						.build();
	
		//Builder pattern
	PetLombok pet = new PetLombok.PetLombokBuilder()
			.id(500)
				.category(category)
				.name("Roby")
				.photoUrls(Arrays.asList("https://cat.com", "https://cat1.com"))
				.tags(Arrays.asList(tag, tag1))
				.status("available")
				.build();
				
		
		
		Response respose = given()     //This API doesn't have any header so need to add that
								.contentType(ContentType.JSON)
								.body(pet) //serialization
						   .when()
						   	.post("/v2/pet");
		
		//can validate status code 
		System.out.println(respose.statusCode());
		
		//pretty print
		respose.prettyPrint(); //Mainly useful for debug purposes when writing tests. 
		
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			PetLombok petRes = mapper.readValue(respose.getBody().asString(), PetLombok.class);  //class obj.
			
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			//category
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getCategory().getName());
			
			//photoUrls
			System.out.println(petRes.getPhotoUrls());
			
			//tag
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());
			
			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

}
