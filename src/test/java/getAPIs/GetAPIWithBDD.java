package getAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GetAPIWithBDD {
	
	//BDD 
	/*
	 * Given()--what is given to you? pre-condition
	 * When() - whatever HTTP method you want to call ---get() e.g.
	 * Then() --assertion
	 * and()--assertion
	 * 
	 * These are already available in RestAssured and used with the help of static keyword
	 */
	
	//Chain pattern--builder pattern(each and every method is giving you current class obj)
	
	@Test
	public void getProductsTest() {
		
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)  //All are Hard Assertions, no concept of Soft-assertions in RestAssured
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("Connection", "keep-alive")
												.and()
													.body("$.size()", equalTo(20)) //check response body--supply some matchers then Hamcrest library comes into the picture
														.and()			 				// In RestAssured, "$" means entire response body
														     .body("id", is(notNullValue()))          	//for equalTo() --> Add --> import static org.hamcrest.Matchers.*;
														          .and()
														          	.body("title", hasItem("Mens Cotton Jacket"));
	}
	
	
	@Test
	public void getUserAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//After give, when, then --log().all()
		
		given().log().all()
			.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76") //Add header ---token
				.when().log().all()
					.get("/public/v2/users/")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.body("$.size", equalTo(10));
											
				
	}
	
	//With query parameter
	@Test
	public void getProductDataAPIWithQueryParamTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.queryParam("limit", 5) //no header for this API
				.when().log().all()
					.get("/products")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.body("$.size", equalTo(5));
		
	}
	
	//JSON Path Method in Rest Assured
	@Test
	public void getProductDataAPIWithExtractBody() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
	Response response =	given().log().all()
								.queryParam("limit", 5) //no header for this API
									.when().log().all()
										.get("/products");
						
		//similar to postman - pm.response,json --always write jsonPath
	JsonPath js = response.jsonPath(); //complete JSON response is available here
	
	//get the ID of the first product
	int firstProductID = js.getInt("[0].id");
	System.out.println("First product ID is " + firstProductID); //1
	
	//to fetch the title
	String firstProductTitle = js.getString("[0].title");
	System.out.println("First product title " + firstProductTitle); //Foldsack No. 1 Backpack, Fits 15 Laptops
	
	//to fetch price
	float firstProductPrice = js.getFloat("[0].price");
	System.out.println("First product price = " + firstProductPrice); //109.95
	
	//fetch rating-count
	int ratingCount = js.getInt("[0].rating.count");
	System.out.println("Product rating = " + ratingCount);
	
	}
	
	
	@Test
	public void getProductDataAPIWithExtractBody_With_JSONArray() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
	Response response =	given().log().all()
								.queryParam("limit", 10)
									.when().log().all()
										.get("/products");
						
		
	JsonPath js = response.jsonPath(); //JSON Array --use getList here because it was available inside a array
	
	//Collect all the IDs 
	
	List<Integer> idList = js.getList("id");
	List<String> titleList = js.getList("title");
	List<Object> rateList = js.getList("rating.rate");
//	List<Float> rateList = js.getList("rating.rate", Float.class);
	List<Integer> rateCount = js.getList("rating.count");
	
	//Iterate lists
	for(int i=0; i<idList.size(); i++) {
		int id = idList.get(i);
		String title = titleList.get(i);
		Object rate = rateList.get(i); //because at 7th position there was an int value 3 so it was failing with float. 
	//	float rate = rateList.get(i); //could be a mismatch later so use Object always
		int count = rateCount.get(i);
		
		System.out.println("ID:" + id + " " + "Title: " + title + " " + "Rate: " + rate + " " + "Count: "+count);
	}
	
	
	}
	
	//Single response without JSON Array
	@Test
	public void getUserAPIWithExtractBody_WithJson() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response  response = given().log().all()
								.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
									.when().log().all()
										.get("/public/v2/users/4657189");
		
		JsonPath js = response.jsonPath();

		/*
		 *  
		 *  {
    		"id": 4657189,
    		"name": "Adityanandana Devar Ret.",
    		"email": "ret_adityanandana_devar@windler.test",
    		"gender": "male",
    		"status": "active"
			}
		 */
		
		//fetch ID
		System.out.println(js.getInt("id")); //4657189
		
		//fetch email
		System.out.println(js.getString("email")); //ret_adityanandana_devar@windler.test
	}
	
	
	//Same thing with the help of Extract 
	@Test
	public void getUserAPIWithExtractBody_WithJson_Extract() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
//		int userId = given().log().all()
//							.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
//								.when().log().all()
//									.get("/public/v2/users/4657189")
//										.then()
//											.extract().path("id"); //useful in case of fetching single attribute
//		System.out.println(userId);	
		
		//if you don't want to use chain concept as above 
		Response response = given().log().all()
								.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
									.when().log().all()
										.get("/public/v2/users/4657187")
											.then()
												.extract()
													.response();
							
		int userId = response.path("id"); //4657187
		String email = response.path("email"); //i_adhrit_nehru@boehm-wunsch.test
		
		System.out.println(userId);
		System.out.println(email);
		
		
	}
	
	
	
	
	
	
}
