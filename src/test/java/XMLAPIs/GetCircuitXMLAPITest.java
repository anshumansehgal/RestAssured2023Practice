package XMLAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GetCircuitXMLAPITest {
	
	@Test
	public void xmlTest() {
		RestAssured.baseURI = "http://ergast.com";
		
	Response response = given()
							.contentType(ContentType.XML)
							.when()
							.get("/api/f1/2017/circuits.xml");
		
	//Similar to JSON PATH from JAYWAY
	
	String responseBody = response.body().asString();
	System.out.println(responseBody);	
		
	//Create Obj. of XML Path
	XmlPath xmlPath = new XmlPath(responseBody);
		
	//Circuit Names --Child Tag - .TagName
	List<String> circuitNames = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
	for (String e : circuitNames) {
		System.out.println(e);
	}
	
	System.out.println("-------------");
	
	//Circuit IDs --Child Attribute - @
	List<String> circuitIDs = xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");
	for (String e : circuitIDs) {
		System.out.println(e);
	}
	System.out.println("-------------");
	
	
	//Fetch the locality where circuitId = americas
	//**.findAll --deep scan  ---Groovy scripting
	String locality = xmlPath.get("**.findAll { it.@circuitId == 'americas'}.Location.Locality").toString();
		System.out.println(locality);  //Austin
		
	System.out.println("-------------");
	
	//Fetch the lat and long where circuitId = americas
	String latVal = xmlPath.get("**.findAll { it.@circuitId == 'americas'}.Location.@lat");
	String longVal = xmlPath.get("**.findAll { it.@circuitId == 'americas'}.Location.@long");
		System.out.println(latVal + " and " + longVal); //30.1328 and -97.6411
		
	System.out.println("-------------");
		
	//Fetch locality where circuitId = americas or circuitID= bahrain
	String data = xmlPath.get("**.findAll { it.@circuitId == 'americas' || it.@circuitId == 'bahrain'}.Location.Locality").toString();
	System.out.println(data);	//[Austin, Sakhir]
	
	System.out.println("-------------");
	
	//Fetch circuitName where circuitId = americas
	String circuitName = xmlPath.get("**.findAll { it.@circuitId == 'americas'}.CircuitName").toString();
	System.out.println(circuitName); //Circuit of the Americas
		
	}
	
	
	
	
	
	

}
