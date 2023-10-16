package schemaValidation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.File;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidationTest {
	
	//1. POST call 
	@Test
	public void addUserSchemaTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";

				given()
					.contentType(ContentType.JSON)
					.body(new File("./src/test/resources/data/addUser.json"))
					.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
				.when()
					.post("/public/v2/users/").
				then().log().all()
					.assertThat()
						.statusCode(201)
						 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createuserschema.json"));
	
	}
	
	//2. GET Call
	@Test
	public void getUserSchemaTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";

				given()
					.contentType(ContentType.JSON)
					.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
				.when()
					.get("/public/v2/users/").
				then().log().all()
					.assertThat()
						.statusCode(200)
						 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getuserschema.json"));
	
	}
	
	
	
	
	

}
