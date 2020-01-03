package com.userapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.userapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

//--------- https://www.youtube.com/watch?v=yDdBOspPp_c&list=PLUDwpEzHYYLuMRzT6LFq4r8DwKZdcqHmY
public class TC001_GET_All_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException{

		logger.info("******************* Started TC001_GET_AllEmployees ****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody() {
		
		logger.info("******************* Checking response body ****************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody !=null);
		
	}
	
	@Test
	void checkStatusCode() {
		
		logger.info("******************* Checking status code ****************");
		
		int statusCode = response.getStatusCode();
		logger.info("Status Code is==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkRespondTime(){
		
		logger.info("******************* Checking respond time ****************");
		
		long responseTime = response.getTime();
		logger.info("Response Tisme is ==>" + responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime>2000);
	}
	
	@Test
	void checkStatusLine() {
		
		logger.info("******************* Checking status line ****************");
		
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>"  + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");		
	}
	
	@Test
	void checkContentType() {
		
		logger.info("******************* Checking content type ****************");
		
		String contentType = response.contentType();
		logger.info("Content Type is ==>"  + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");		
	}
	
	@Test
	void checkServerType() {
		
		logger.info("******************* Checking server type ****************");
		
		String serverType = response.header("Server");
		logger.info("Server Type is ==>"  + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@Test
	void checkSContentEncoding() {
		
		logger.info("******************* Checking content encoding ****************");
		
		String contentEncoding = response.header("Content - Endoding");
		logger.info("Content Encoding is ==>"  + contentEncoding);
		Assert.assertEquals(contentEncoding, null);

	}
	
	@Test
	void checkSContentLength() {
		
		logger.info("******************* Checking content length ****************");
		
		String contentLength = response.header("Content - Length");
		logger.info("Content Length is ==>"  + contentLength);
	//	Assert.assertTrue(Integer.parseInt(contentLength)<1500);
		System.out.println(contentLength);

	}
	
	@Test
	void checkCookies() {
		
		logger.info("******************* Checking cookies ****************");
		
		String cookies = response.cookie("PHPSESSID");
		logger.info("Content Length is ==>"  + cookies);
	//	Assert.assertEquals(cookies,"kjkjkjkkjjjlllliuiuytytyt");		
	}
	
	@AfterClass
	void tearDown() {
		
		logger.info("******************* Finished TC001_GET_AllEmployees ****************");
	
	}
}























