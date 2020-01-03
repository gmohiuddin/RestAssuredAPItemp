package com.userapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.userapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GET__Single_Employee_Record extends TestBase{
	@BeforeClass
	void getAllEmployee() throws InterruptedException{

		
		logger.info("******************* Started TC002_GET_Single_Employee ****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/"+emplD);
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody() {
		
		logger.info("******************* Checking response body ****************");
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(emplD), true);
	}
	
	@Test
	void checkStatusCode() {
		
		logger.info("******************* Checking status code ****************");
		
		int statusCode = response.getStatusCode();
		logger.info("Status Code is==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
		System.out.println("statusCode "+statusCode);
	}
	
	@Test
	void checkRespondTime(){
		
		logger.info("******************* Checking respond time ****************");
		
		long responseTime = response.getTime();
		logger.info("Response Tisme is ==>" + responseTime);
		Assert.assertTrue(responseTime<6000);
	}
	
	@Test
	void checkStatusLine() {
		
		logger.info("******************* Checking status line ****************");
		
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>"  + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
		System.out.println("statusLine "+statusLine);
	}
	
	@Test
	void checkContentType() {
		
		logger.info("******************* Checking content type ****************");
		
		String contentType = response.contentType();
		logger.info("Content Type is ==>"  + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		System.out.println("contentType "+contentType);
	}
	
	@Test
	void checkServerType() {
		
		logger.info("******************* Checking server type ****************");
		
		String serverType = response.header("Server");
		logger.info("Server Type is ==>"  + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	
	@Test
	void checkSContentLength() {
		
		logger.info("******************* Checking content length ****************");
		
		String contentLength = response.header("Content - Length");
		logger.info("Content Length is ==>"  + contentLength);
	//	Assert.assertTrue(Integer.parseInt(contentLength)<1500);
		System.out.println("contentLength "+contentLength);

	}
	

	@AfterClass
	void tearDown() {
		
		logger.info("******************* Finished TC002_GET_Single_Employee ****************");
	
	}
}
