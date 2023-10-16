package getAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPIRequestTestWithoutBDD {
	
	RequestSpecification request;
	//BDD - BDD means you're writing the code in the form of Given, When, Then
	
	//This is Rest Assured Non-BDD Approach
	
	@BeforeTest
	public void setUp() {
		//Request
		
		//Creating Base URI
		RestAssured.baseURI = "https://gorest.co.in";
		
		//Helps to create request specification
		request = RestAssured.given();
		
		//Header - Authorization etc
		request.header("Authorization", "Bearer 55b2c799844a831c6401e7b4178327f0a3c5c12f66359af247c473a18cd5fe76");	
	}
	
	@Test
	public void getAllUsersAPITest() {
		
		//GET call ---gets "response" in return
		Response response = request.get("/public/v2/users/");
		
			//========================Response
		
			//Status code fetch
			int statusCode = response.statusCode();
			System.out.println("Status Code:" + statusCode);
		
			//Verification point:
			Assert.assertEquals(statusCode, 200);
		
			//Status message fetch
			String statusMsg = response.statusLine();
			System.out.println(statusMsg); //200 OK
		
			//Fetch the body from response
			response.prettyPrint(); //Pretty-print the response body. Mainly useful for debug purposes when writing tests. Pretty printing is possible for content-types JSON, XML and HTML.
		
			//Fetch response headers
		
			//1. specific header 
			String contentType = response.header("Content-Type");  //two options --all header or specific header like key-value pair
			System.out.println(contentType); //application/json; charset=utf-8
		
			//2. All headers
			System.out.println("-------------------------------");
			List<Header> headersList = response.headers().asList();
			System.out.println(headersList.size());	//check how many header are there
		
			//print all headers
			for (Header h : headersList) {
				System.out.println(h.getName() + ":" + h.getValue()); //prints all headers. No need to validate all the header, only imp. ones
			}
		}
	
	//?name=naveen&gender=male
	@Test
	public void getAllUsersWithQueryParamterAPITest() {
		
		//Request
		request.queryParam("name", "Naveen");
		request.queryParam("gender", "male");
		
		Response response = request.get("/public/v2/users");
		
			//========================Response
		
			//Status code fetch
			int statusCode = response.statusCode();
			System.out.println("Status Code:" + statusCode);
		
			//Verification point:
			Assert.assertEquals(statusCode, 200);
		
			//Status message fetch
			String statusMsg = response.statusLine();
			System.out.println(statusMsg); //200 OK
		
			//Fetch the body from response
			response.prettyPrint(); //Pretty-print the response body. Mainly useful for debug purposes when writing tests. Pretty printing is possible for content-types JSON, XML and HTML.
		
			
		}
	
		//Better approach to use HashMap
		@Test
		public void getAllUsersWithQueryParamter_With_HashMap_APITest() {
		
			//query params HashMap for adding more than 1 query --HashMap--stores the data in key-value pair
			Map<String, String> queryParamsMap = new HashMap<String, String>();
			queryParamsMap.put("name", "Naveen");
			queryParamsMap.put("gender", "male");
			
			request.queryParams(queryParamsMap);
			
			Response response = request.get("/public/v2/users");
			
				//========================Response
			
				//Status code fetch
				int statusCode = response.statusCode();
				System.out.println("Status Code:" + statusCode);
			
				//Verification point:
				Assert.assertEquals(statusCode, 200);
			
				//Status message fetch
				String statusMsg = response.statusLine();
				System.out.println(statusMsg); //200 OK
			
				//Fetch the body from response
				response.prettyPrint(); //Pretty-print the response body. Mainly useful for debug purposes when writing tests. Pretty printing is possible for content-types JSON, XML and HTML.
			
				
			}

}
