package com.product.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.fakestore.api.Product;
import com.fakestore.api.ProductLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest {
	
	@Test
	public void getProductsTest_With_POJO() {
		
		RestAssured.baseURI = "https://fakestoreapi.com"; 
		
	Response response = 	given().log().all()
								.when().log().all()
									.get("/products");
	
	//JSON to POJO mapping => De-Serialization
	
	//Using Jackson lib
	ObjectMapper mapper = new ObjectMapper();
	try {
		Product product[] = mapper.readValue(response.getBody().asString(), Product[].class); //JSON to Java Object(POJO)
																						      //map it to POJO class--response is in array so store it in [] array
		
		//iterate this product array
		for(Product p : product) {
			System.out.println("ID " + p.getId());
			System.out.println("Title " + p.getTitle());
			System.out.println("Price " + p.getPrice());
			System.out.println("Description " + p.getDescription());
			System.out.println("Category " + p.getCategory());
			System.out.println("Image " + p.getImage());
			
			//Rate and count
			System.out.println("Rate " + p.getRating().getRate());
			System.out.println("Count " + p.getRating().getCount());
			
			System.out.println("-----");
		}
		
		
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		
		e.printStackTrace();
	}   
}
	
	
	//Create POJO with Lombok
	@Test
	public void getProductsTest_With_POJO_Lombok() {
		
		RestAssured.baseURI = "https://fakestoreapi.com"; 
		
	Response response = 	given().
								when()
									.get("/products");
	
	//JSON to POJO mapping => De-Serialization
	
	ObjectMapper mapper = new ObjectMapper();
	try {
		
		ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
		
		//iterate this product array
		for(ProductLombok p : product) {
			System.out.println("ID " + p.getId());
			System.out.println("Title " + p.getTitle());
			System.out.println("Price " + p.getPrice());
			System.out.println("Description " + p.getDescription());
			System.out.println("Category " + p.getCategory());
			System.out.println("Image " + p.getImage());
			
			//Rate and count
			System.out.println("Rate " + p.getRating().getRate());
			System.out.println("Count " + p.getRating().getCount());
			
			System.out.println("-----");
		}
		
		
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		
		e.printStackTrace();
	}   
}
	
	//3rd 
	@Test
	public void getProductsTest_With_POJO_LombokBuilderPattern() {
		
		RestAssured.baseURI = "https://fakestoreapi.com"; 
		
	
}
					
}
