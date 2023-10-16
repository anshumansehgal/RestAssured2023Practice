package specificationConcept;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResSpecTest {
	
	//Request Spec
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec =  new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
						.addHeader("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76")
							.build(); //build-Just like in Actions class in Selenium
		
		return requestSpec;
	}
	
//Response Spec	
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
	
	
	@Test
	public void getUser_with_Req_Res_Spec_Test() {
		
		given()
			.spec(user_req_spec())
				.get("/public/v2/users")
					.then()
						.assertThat()
							.spec(get_res_spec_200_OK_WithBody());
		
		
		
	}

}
