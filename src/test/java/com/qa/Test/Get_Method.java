package com.qa.Test;

import java.util.HashMap;


import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utils.TestUtils;

public class Get_Method extends TestBase 
{
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
	public void getAPI() throws Throwable, Throwable
	{
		restClient= new RestClient();
		closeableHttpResponse=restClient.get(URL);
		
		//status
		int status_code=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(status_code);
		Assert.assertEquals(status_code, RESPONCE_STATUS_200,"Status code is not 200");
		
		String ResponceString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"); //To get the string of response we have EntityUtils class, -UTF-8-character format
		
		JSONObject responceJason= new JSONObject(ResponceString);//convert ResponseString in JSONObject
		
		System.out.println("Json Responce from API: "+responceJason);	
		
		//page
		String page = TestUtils.getValueByJPath(responceJason, "/page");
		int page_entry = Integer.parseInt(page);
		System.out.println("Displayed Page number is:"+page_entry);
		Assert.assertEquals(page_entry, 1);
		
		//per_page
		String perPageValue = TestUtils.getValueByJPath(responceJason, "/per_page");
		int perpage_entry=Integer.parseInt(perPageValue);
		System.out.println("Per Page data entry are:"+perpage_entry);	
		Assert.assertEquals(perpage_entry, 6,"per page data entry is correct");		
		
		//total
		String totalValue = TestUtils.getValueByJPath(responceJason, "/total");
		System.out.println("Total Data entry are:"+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		
		String lastname= TestUtils.getValueByJPath(responceJason, "/data[1]/last_name");
		String ID= TestUtils.getValueByJPath(responceJason, "/data[1]/id");
		String Avatar= TestUtils.getValueByJPath(responceJason, "/data[1]/avatar");
		String FirstName= TestUtils.getValueByJPath(responceJason, "/data[1]/first_name");
		String Email= TestUtils.getValueByJPath(responceJason, "/data[1]/email");

		//System.out.println(":"+lastname);
		System.out.println("Last Name:"+lastname);
		System.out.println("First Name:"+FirstName);
		System.out.println("id:"+ID);
		System.out.println("avatar:"+Avatar);
		System.out.println("email:"+Email);
		
		
		String lastname1= TestUtils.getValueByJPath(responceJason, "/data[2]/last_name");
		String ID1= TestUtils.getValueByJPath(responceJason, "/data[2]/id");
		String Avatar1= TestUtils.getValueByJPath(responceJason, "/data[2]/avatar");
		String FirstName1= TestUtils.getValueByJPath(responceJason, "/data[2]/first_name");
		String Email1= TestUtils.getValueByJPath(responceJason, "/data[2]/email");

		//System.out.println(":"+lastname1);
		System.out.println("Last Name:"+lastname1);
		System.out.println("First Name:"+FirstName1);
		System.out.println("id:"+ID1);
		System.out.println("avatar:"+Avatar1);
		System.out.println("email:"+Email1);

		
		Header[] headerArray= closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		for(Header header:headerArray)
		{
			allHeaders.put(header.getName(), header.getValue());
			
		}
		System.out.println("Headers Key and Value:"+allHeaders);
		
	}

}
