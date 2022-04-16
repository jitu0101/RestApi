package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//get method
	public CloseableHttpResponse get(String url) throws Throwable, IOException
	{
		
	CloseableHttpClient httpClient = HttpClients.createDefault(); 
	//class-httpClients, createDefault-will create 1 connection and return closable httpclientgobject
	
	System.out.println(url);
	HttpGet httpget = new HttpGet(url); //HttpGet is class ,httpGet will create one connection with this URL
	CloseableHttpResponse  closeableHttpResponse= httpClient.execute(httpget);//hit the url
	
	return closeableHttpResponse;
	}

	//post method
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws Throwable
	{
		CloseableHttpClient httpClient= HttpClients.createDefault();
		HttpPost httppost= new HttpPost(url);//http post request
		httppost.setEntity(new StringEntity(entityString));//setEntity method is used to define the payload
		
		//for Headers
		for(Map.Entry<String, String> entry:headerMap.entrySet())
        {
			httppost.addHeader(entry.getKey(), entry.getValue());
        }
		CloseableHttpResponse  closeableHttpResponse=httpClient.execute(httppost);
		return closeableHttpResponse;
	}
}
