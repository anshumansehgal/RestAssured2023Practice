package POSTAPIs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OAuth2Test {
	
	/*
	 * https://test.api.amadeus.com/v1/security/oauth2/token ---token
	 * 
	 * Get call ---needed token in to hit this
	 */
	
	static String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		//1. POST - Get the access token
				RestAssured.baseURI = "https://test.api.amadeus.com";
				
			accessToken = 	given()
									//	.header("Content-Type", "application/x-www-form-urlencoded")//header --url encoded OR
										.contentType(ContentType.URLENC)
										.formParam("grant_type", "client_credentials")
										.formParam("client_id", "soDfzpPAJ4gKECAyHQ26QcjDEDDAeMLk")  //from app, these client id and client secret is coming
										.formParam("client_secret", "5650YVLcx8sf1qEz")
									.when()
										.post("/v1/security/oauth2/token")
									.then()
										.assertThat()
											.statusCode(200)
												.extract().path("access_token");
					
				System.out.println(accessToken); //E.g. - noV52JOFG64Zt03eEsReDXSf6gBw
	}
	
	
	@Test
	public void getFlightInfoTest() {
		
		//co-relation: relate 2 APIs together
	
		//2. Get flight info: GET
	String type = 			given().log().all()
								.header("Authorization", "Bearer " + accessToken)
								.queryParam("origin", "PAR")
								.queryParam("maxPrice", 200)
							.when().log().all()
								.get("/v1/shopping/flight-destinations")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.extract().response()
												.jsonPath()
													.get("data[0].type");
	
	System.out.println(type); //flight-destination
		
	}
	
	

}
