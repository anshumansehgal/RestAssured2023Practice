package multibody;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;


public class BodyAPITest {
	
	//Text
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI = "http://httpbin.org";
		String payload = "Hi, this is Ashu";
		
	Response response	= given()
							.contentType(ContentType.TEXT)
							.body(payload).
						when()
							.post("/post");
			
		response.prettyPrint();
		System.out.println(response.statusCode());	
	}
	
	//JavaScript
	@Test
	public void bodyWithJavaScriptTest() {
		
		RestAssured.baseURI = "http://httpbin.org";
		String payload = "function login(){\n"
				+ "    let x = 10;\n"
				+ "    let y = 20;\n"
				+ "    console.log(x + y);\n"
				+ "}";
		
	Response response	= given()
							.header("Content-Type", "application/javascript")
							.body(payload).
						when()
							.post("/post");
			
		response.prettyPrint();
		System.out.println(response.statusCode());	
	}
	
	//HTML
	@Test
		public void bodyWithHTMLTest() {
			
			RestAssured.baseURI = "http://httpbin.org";
			String payload = "<!DOCTYPE html>\n"
					+ "<html dir = \"ltr\" lang=\"en\">\n"
					+ "   <head>\n"
					+ "<meta charset=\"UTF=8\" />\n"
					+ "\n"
					+ "   </head>\n"
					+ "</html>";
			
		Response response	= given()
								.contentType(ContentType.HTML)
								.body(payload).
							when()
								.post("/post");
				
			response.prettyPrint();
			System.out.println(response.statusCode());	
		}
	
	//XML
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI = "http://httpbin.org";
		String payload = "<note>\n"
				+ "    <to>Tove</to>\n"
				+ "    <from>Jani</from>\n"
				+ "    <heading>Reminder</heading>\n"
				+ "    <body>Don't forget me this weekend!</body>\n"
				+ "</note>";
		
	Response response	= given()
							.contentType(ContentType.XML)
							.body(payload).
						when()
							.post("/post");
			
		response.prettyPrint();
		System.out.println(response.statusCode());	
	}
	
	//Form-data multi-part
	@Test
	public void bodyWithFormDataMultiTest() {
		
		RestAssured.baseURI = "http://httpbin.org";
		
	Response response	= given()
							.contentType(ContentType.MULTIPART)
							.multiPart("name", "testing")  //normal key pair
							.multiPart("fileName", new File("/Users/anshumansehgal/Downloads/Damaged_Suitcase_Evaluation.pdf")) //file with path--base64 PDF not readable
						.when()
							.post("/post");
			
		response.prettyPrint();
		System.out.println(response.statusCode());	
	}
	
	
	//Binary--file could be anything
	@Test
	public void bodyWithBinaryFileTest() {
		
		RestAssured.baseURI = "http://httpbin.org";
		
	Response response	= given()
							.header("Conten-Type", "application/pdf")
							.body(new File("/Users/anshumansehgal/Downloads/Damaged_Suitcase_Evaluation.pdf"))
						.when()
							.post("/post");
			
		response.prettyPrint();
		System.out.println(response.statusCode());	
	}
	

}
