package com.userapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.userapi.base.TestBase;
import com.userapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PUT_Employee_Record extends TestBase {

	RequestSpecification httpRequest; 
	Response response; 

	String empName=RestUtils.empName(); 
	String empSalary=RestUtils.empSal(); 
	String empAge=RestUtils.empAge();

	@BeforeClass 
	void createEmployee() throws InterruptedException { 
		logger.info("*********Started TC004_Put_Employee_Record **********"); 
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1"; 
		httpRequest = RestAssured.given(); 
		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method 
		//{"name":"Dohn123X","salary":"123"1"age":"23"} 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("name", empName); // Cast 
		requestParams.put("salary", empSalary); 
		requestParams.put("age", empAge);

		// Add a header stating the Request body is a JSON 
		httpRequest.header("Content-Type", "application/json"); 
		// Add the Json to the body of the request 
		httpRequest.body(requestParams.toJSONString()); 

		response = httpRequest.request(Method.PUT, "/update/"+emplD); 
		Thread.sleep(5000);
	}
		@Test 
		void checkResposeBody() {
			String responseBody = response.getBody().asString(); 
			Assert.assertEquals(responseBody.contains(empName), true);
			Assert.assertEquals(responseBody.contains(empSalary), true); 
			Assert.assertEquals(responseBody.contains(empAge), true); 
		}

		@Test 
		void checkStatusCode() { 
			int statusCode = response.getStatusCode(); // Gettng status code 
			Assert.assertEquals(statusCode, 200); 
		}	
		@Test void checkstatusLine() { 
			String statusLine = response.getStatusLine(); // Gettng status Line 
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK"); 
		}

		@Test void checkContentType() { 
			String contentType = response.header("Content-Type"); 
			Assert.assertEquals(contentType, "text/html; charset=UTF-8"); 
		} 
		@Test void checkserverType() { 
			String serverType = response.header("Server"); 
			Assert.assertEquals(serverType, "nginx/1.16.0"); 
		} 

		@Test void checkcontentEncoding() { 
			String contentEncoding = response.header("Content-Encoding"); 
		//	Assert.assertEquals(contentEncoding, "gzip");
			System.out.println(contentEncoding);
		}

		@AfterClass void tearDown() { 

			logger.info(". Finished TC004 Put Employee Record M "); 
		}
}	