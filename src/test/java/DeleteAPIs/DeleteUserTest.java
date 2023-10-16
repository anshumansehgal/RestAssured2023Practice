package DeleteAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.user.api.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class DeleteUserTest {
	
	//1. Post --Create user ---userId --201
	//2. Delete - delete user -- /userID --204
	//3. GET(Confirm user gets deleted) ---get user --- /userID -- 404
	
	//No need of POJO for Delete and Get call
	
	@Test
	public void deleteUserTest() {
		
		//1st - POST CALL
		RestAssured.baseURI = "https://gorest.co.in";
			
		User user = new User("Ashu", getRandomEmailId(), "male", "active");
				
		Response response = 	given().log().all()
									.contentType(ContentType.JSON)
									.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
									.body(user)    //serialization
								.when()
									.post("/public/v2/users");
		
			response.prettyPrint(); //Pretty-print the response body if possible and return it as string. Mainly useful for debug purposes when writing tests.
			
			//From response, we need 'id' for get call
			Integer userId = response.jsonPath().get("id");
				System.out.println("User ID: " + userId);
		
		
			System.out.println("------------------");
			
		//2nd -- Delete -- Delete user	
			
			given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
		.when()
			.delete("/public/v2/users/" + userId)
				.then().log().all()
					.assertThat()
						.statusCode(204);
			
		//3rd --Get the user -- GET --Not available --404
			given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization" , "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
		.when()
			.get("/public/v2/users/" + userId)
				.then().log().all()
					.assertThat()
						.statusCode(404)
							.and()
								.assertThat()
									.body("message", equalTo("Resource not found"));
			
	}
	
	public static String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@gmail.com";
	}
	
	

}
