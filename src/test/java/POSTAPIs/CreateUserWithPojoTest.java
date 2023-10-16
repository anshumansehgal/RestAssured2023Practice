package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.UUID;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;


public class CreateUserWithPojoTest {
	
	/* Types of POST operation we can perform --
	 * 1. Direct supply the JSON String
	 * 2. Pass the JSON file
	 * POJO -- Java objects --- to JSON with the help of Jackson/Rest Assured
	 */

	//Create random email id
	public static String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@gmail.com";
		//OR
		//return "apiautomation" + UUID.randomUUID() + "@gmail.com"; //what if "-" is not supported in email id in the app
	}
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		User user = new User("Anshu", getRandomEmailId(), "male", "active");
		
		//1. Add user --POST
	int userId = given().log().all()
					.contentType(ContentType.JSON)
					
					//supply the POJO
					.body(user)
					
					.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76").
				 when()
				 	.post("/public/v2/users/").
				 then().log().all()
				 	.assertThat()
				 	.statusCode(201)
				 	.and()
				 	.body("name", equalTo(user.getName()))  //getName
				 	.extract()
				 		.path("id");
				
		System.out.println("User id: " + userId);		
		
		//2. Get the same user and verify it : GET 
		given()
		.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
			.when().log().all()
				.get("/public/v2/users/" + userId) //append user id here
					.then()
						.assertThat()
							.statusCode(200)
								.and()
									.body("id", equalTo(userId))
										.and()
											.body("name", equalTo(user.getName()))
												.and()
													.body("status", equalTo(user.getStatus()))
														.and()
															.body("email", equalTo(user.getEmail()));
											
										
	}
	
	
	

}
