package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class BoookingAuthWithPojoTest {
	
	/* POJO - Plain Old Java Object
	 * Cannot extend any other class
	 * OOP - Encapsulation --data hiding---with the help of 'private' keyword --Encapsulation means that I don't want to give the direct access on some of the class variables,
	 * if you really want to access, you have to access via public methods. Those public methods are called getter and setters.
	 * 
	 * POJO is classic example of Encapsulation.
	 * create private class vars according to JSON body
	 * public getter/setter 
	 * 
	 * .body(Object) --use concept of serialization
	 * Serialization - java object to other format (file, media, json/xml/text/pdf)
	 * POJO to JSON
	 * 
	 * De-Serialization --reverse of Serialization --
	 * JSON to POJO--we don't need this as of now
	 */
	
	@Test
	public void getBookingAuthTokenTest_WithJSON_String() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Credentials creds = new Credentials("admin", "password123");
			
	String tokenId = given()
							.contentType(ContentType.JSON)
							
							//Pass obj. 
							.body(creds)
							
								.when()
									.post("/auth")
										.then()
											.assertThat()
												.statusCode(200)
													.extract()
														.path("token");
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
		
		/*
		 * Need of getter and setter 
		 * Let's say we get username and password in the response JSON
		 * So, res JSON --username ---compare with getter(getUsername)) 
		 */
		
	}
	
	
	

}
