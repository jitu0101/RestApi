package com.qa.Test;

import java.io.File;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class Post_MethodTest extends TestBase {
	
	TestBase testbase;
	String serviceUrl;
	String apiURL;
	String URL;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;


	@BeforeMethod
	public void setup() throws Throwable, Throwable
	{
		testbase = new TestBase();
		apiURL = prop.getProperty("ApiURL");
		serviceUrl = prop.getProperty("serviceURL");
		
		URL=apiURL+serviceUrl;
		
	}

	@Test
	public void PostAPI() throws Throwable
	{
		restClient= new RestClient();
		HashMap<String,String> headermap=new HashMap<String,String>();
		headermap.put("Content-Type", "application/json");
		
		//jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users user= new Users("jitu","guptaa");
		
		//object to json file
		
		mapper.writeValue(new File("D:\\selenium\\APITest\\src\\main\\java\\com\\qa\\data\\users.jason"), user);
		
		//object to json in String
		String usersjsonString=mapper.writeValueAsString(user);
		System.out.println(usersjsonString);
		
		closeableHttpResponse=restClient.post(URL, usersjsonString, headermap);
		
		//get status code
		int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode,testbase.RESPONCE_STATUS_201);
		
		//json string
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson= new JSONObject(responseString);
		System.out.println("Response from API:"+responseJson);
		
		//json to java
		Users usersresObject=mapper.readValue(responseString, Users.class);//actaul user objecrt
		System.out.println(usersresObject);
		
		Assert.assertTrue(user.getName().equals(usersresObject.getName()));
		Assert.assertTrue(user.getJob().equals(usersresObject.getJob()));

		
	}

}
