package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.Headers;

public class RestClient1 {
	
	//get metyhod
	public void get(String url) throws IOException, IOException, JSONException
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //httpGet will create one connection with this URL
		//class-httpClients, createDefault-will create 1 connection and return closable httpclientgobject

		
		CloseableHttpResponse closeableHttpResponse= httpclient.execute(httpget); //hit the API and execute it and then return the response 
		int status_code = closeableHttpResponse.getStatusLine().getStatusCode();
		String responseString  = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API:" +responseJson);
		
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allheaders = new HashMap<String, String>();
		
		for(Header header:headerArray)
		{
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("header Array:"+allheaders);
	}
	
	public  static void main(String[] args) throws IOException, JSONException
	{
		RestClient1 restclient = new RestClient1();
		restclient.get("https://reqres.in/api/users");
	}

}
