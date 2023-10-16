package AuthAPIs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class AuthTest {
	
	@BeforeTest
	public void allureSetup() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	/* Types of Auth
	 * 1. JWT Bearer
	 */

	@Test
	public void jwtAuthWithJsonBody() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
	String jwtToken = 	given()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"username\": \"mor_2314\",\n"
					+ "    \"password\": \"83r5^_\"\n"
					+ "}").
		when()
			.post("/auth/login")
				.then()
					.assertThat()
						.statusCode(200)
							.and()
								.extract().path("token");
			
		System.out.println(jwtToken);
		//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXIiOiJtb3JfMjMxNCIsImlhdCI6MTY5Mzg3MjQ1OX0.c7fEKsuI6R2pC08SELbFIqm5tfNXWYNoiKqxyBE5PGs
		
		//Break token into 3 parts
		String tokenArr[] = jwtToken.split("\\.");
		System.out.println(tokenArr[0]);
		System.out.println(tokenArr[1]);
		System.out.println(tokenArr[2]);
	}
	
	//2. Basic Auth
	@Test
	public void basictAuthTest() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
	String responseBody = 	given()
							.auth().basic("admin", "admin").
							when()
								.get("/basic_auth")
									.then()
										.assertThat()
											.statusCode(200)
												.and()
													.extract().body().asString();
		System.out.println(responseBody);										
	}
	
	//Preemptive Auth --Advance version of Basic Auth
	@Test
	public void preemptiveAuthTest() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
	String responseBody = 	given()
							.auth().preemptive().basic("admin", "admin").
							when()
								.get("/basic_auth")
									.then()
										.assertThat()
											.statusCode(200)
												.and()
													.extract().body().asString();
		System.out.println(responseBody);										
	}
	
	//3. Digest Auth --more secure and better performance than Preemptive auth
	@Test
	public void digestAuthTest() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
	String responseBody = 	given()
							.auth().digest("admin", "admin").
							when()
								.get("/basic_auth")
									.then()
										.assertThat()
											.statusCode(200)
												.and()
													.extract().body().asString();
		System.out.println(responseBody);										
	}
	
	//4. API Key
	@Test
	public void apiKeyAuthTest() {
		
		RestAssured.baseURI = "http://api.weatherapi.com";
		
	String responseBody = 	given()
								.queryParam("q", "London")
								.queryParam("aqi", "no")
								.queryParam("key", "13c1653204fc4b45907232725230908")
							.when()
								.get("/v1/current.json")
									.then()
										.assertThat()
											.statusCode(200)
												.and()
													.extract().body().asString();
		System.out.println(responseBody);										
	}
	
	
	
}
