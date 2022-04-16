package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int RESPONCE_STATUS_200=200; 
	public int RESPONCE_STATUS_500=500; 
	public int RESPONCE_STATUS_400=400; 
	public int RESPONCE_STATUS_402=402; 
	public int RESPONCE_STATUS_201=201; 

	public static Properties prop;
	
	//its base classs
	
	public TestBase() 
	{
		try
		{
		prop=new Properties();
		FileInputStream ip = new FileInputStream("D:\\selenium\\APITest\\src\\main\\java\\com\\qa\\config\\config.properties");
		prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
