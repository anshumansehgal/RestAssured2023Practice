package PutAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.user.api.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest {
	
	//Need a userID to update. We'll get the ID from Create/POST call
	
	/*
	 * 1. Create a user with POST call  --userId
	 * 2. Update user --PUT/PATCH --/userId
	 */
	
	@Test
	public void updateUserTest() {
		
		//1st - POST CALL
		RestAssured.baseURI = "https://gorest.co.in";
			
		User user = new User("Ashu", getRandomEmailId(), "male", "active");
				
		Response response = 	given().log().all()
									.contentType(ContentType.JSON)
									.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
									.body(user)    //serialization
								.when()
									.post("/public/v2/users");
			
			//From response, we need 'id' for get call
			Integer userId = response.jsonPath().get("id");
				System.out.println("User ID: " + userId);
		
		
			System.out.println("------------------");	
		//2. Update the existing user (PUT) --Setters will come into the picture
			user.setName("Ashu Test");
			user.setStatus("inactive");
			
			given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
				.body(user)    //serialization
			.when()
				.put("/public/v2/users/" + userId) //PATCH will also work
			.then().log().all()
				.assertThat()
					.statusCode(200) //verify status code
					.and()
						.assertThat()
							.body("id", equalTo(userId))  //verify user ID
								.and()
									.body("name", equalTo(user.getName()))   //verify updated name
										.and()
											.body("status", equalTo(user.getStatus()));
	}
	
	
	public static String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@gmail.com";
	}
	

}
