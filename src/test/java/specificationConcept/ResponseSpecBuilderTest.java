package specificationConcept;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {
	
	public static ResponseSpecification get_res_spec_200_OK() {
		
		ResponseSpecification res_spec_200_ok	= new ResponseSpecBuilder()
														.expectContentType(ContentType.JSON)
														.expectStatusCode(200)
														.expectHeader("Server", "cloudflare")
														.build();
		
		return res_spec_200_ok;
	}
	
	public static ResponseSpecification get_res_spec_200_OK_WithBody() {
		
		ResponseSpecification res_spec_200_ok	= new ResponseSpecBuilder()
														.expectContentType(ContentType.JSON)
														.expectStatusCode(200)
														.expectHeader("Server", "cloudflare")
														.expectBody("$.size", equalTo(10))
														.expectBody("id", hasSize(10))
														.build();
		
		return res_spec_200_ok;
	}
	
	public static ResponseSpecification get_res_spec_401_Auth_Fail() {
		
		ResponseSpecification res_spec_401_AUTH_FAIL	= new ResponseSpecBuilder()
														.expectStatusCode(401)
														.expectHeader("Server", "cloudflare")
														.build();
		
		return res_spec_401_AUTH_FAIL;
	}
	
	//For 200 --with body
	@Test
	public void get_user_res_200_spec_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
				.when()
					.get("/public/v2/users/")
						.then()
							.assertThat()
								.spec(get_res_spec_200_OK_WithBody());
	}
	
	//401
	@Test
	public void get_user_res_401_Auth_Fail_spec_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe761234") //make incorrect token
				.when()
					.get("/public/v2/users/")
						.then()
							.assertThat()
								.spec(get_res_spec_401_Auth_Fail());
	}
	
	
	

}
