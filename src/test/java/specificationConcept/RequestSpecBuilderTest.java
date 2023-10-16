package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class RequestSpecBuilderTest {
	
	//we'll save lot of code here and time. It's a feature provided by RestAssured internally. They say if you have a common request/response, then create a separate method 
	//with SpecBuilder class and you define how many specification you need. 
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec =  new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
						.addHeader("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
							.build(); //build-Just like in Actions class in Selenium
		
		return requestSpec;
	}
	
	
	
	
	@Test
	public void getUser_With_Request_Spec() {
		
		RestAssured.given().log().all()
					.spec(user_req_spec()) //Add request data from a pre-defined specification
						.get("/public/v2/users")
							.then()
								.statusCode(200);
		
		
	}
	
	@Test
	public void getUser_With_Param_RequestSpec() {
		
		RestAssured.given().log().all()
			.queryParam("name", "Naveen")
			.queryParam("status", "active")
					.spec(user_req_spec())
						.get("/public/v2/users")
							.then()
								.statusCode(200);
		
		
	}
	
	
	
	
	
	

}
