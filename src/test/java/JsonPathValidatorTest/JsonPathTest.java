package JsonPathValidatorTest;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class JsonPathTest {
	
	
	@Test
	public void getCircuitDataAPIWith_YearTest() {
		
		RestAssured.baseURI = "http://ergast.com";
		
		//1. Get Response
		Response response = given()
								.when()
									.get("/api/f1/2017/circuits.json");
		
		//2. convert response into string response
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		//3. Use JSON PATH from JAYWAY --read method
		
		//Find all circuits
		int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println(totalCircuits);
		
		
		//Find all the country
		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country"); 
		
		//primt size and country name
		System.out.println(countryList.size()); //20
		System.out.println(countryList);
	}
	
	
	//Next e.g.
	@Test
	public void getProductTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
	Response response = 	given()
								.when()
									.get("/products");
	
	String jsonResponse = response.asString();
	System.out.println(jsonResponse);				
					
	//1st e.g. Rate which is less than 3---Getting float values
	List<Float> rateLessThan3 = JsonPath.read(jsonResponse, "$[?(@.rating.rate<3)].rating.rate");
	System.out.println(rateLessThan3); //[2.1,1.9,2.9,2.9,2.2,2.6,2.9]
	
	/* 2nd example
	 * Fetch title and price of the product where category is jewelery ---Getting title and value ---use List<Map> --(k,v)
	 *  "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
  		695,
  		"Solid Gold Petite Micropave ",
  		168,
	 */
	
	//With 2 attributes
	 List<Map<String, Object>> jeweleryList = JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\"]");
	 System.out.println(jeweleryList);    //Object--data could be int or float
	 
	 //Iterate the list --use for loop
	 System.out.println("----Printing using for loop-------");
	 for(Map<String, Object> e : jeweleryList) {
		 
		 //Map 1st and 2nd key
		 String title = (String)e.get("title");
		 Object price = (Object) e.get("price");
		 
		 System.out.println("title : " + title);
		 System.out.println("price : " + price);
		 
		 System.out.println("-----");
	 }
	 /*
	  * title : John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet
		price : 695
		title : Solid Gold Petite Micropave 
		price : 168
		title : White Gold Plated Princess
		price : 9.99
	  */
	 
	//With 3 attributes--Key is String, Value can be anything 
	 System.out.println("-----3 attributes------");
		 List<Map<String, Object>> jeweleryList2 = JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\", \"id\"]");
		 System.out.println(jeweleryList2);  
		
		 for(Map<String, Object> e : jeweleryList2) {
			 
			 //Map 1st and 2nd key
			 String title = (String)e.get("title");
			 Object price = (Object) e.get("price");
			 int id = (int) e.get("id");
			 
			 System.out.println("title : " + title);
			 System.out.println("price : " + price);
			 System.out.println("id : " + id);
			 
			 System.out.println("-----");
		 }
	 
	 
	
	}
	
	
	

}
