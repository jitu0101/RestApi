package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient2 {
	
	public void post(String url, String entityString, HashMap<String, String> headerMap) throws IOException
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(entityString));
		
		for(Map.Entry<String, String> entry:headerMap.entrySet())
		httpPost.addHeader(entry.getKey(),entry.getValue());
	}

	public static void main(String[] args) {

	}

}
