package com.Services;

import java.io.IOException;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Config.PropertyConfig;

@Service
public class URLSenderService {

	@Autowired
	PropertyConfig properties;
	
	public boolean sendJsonToUrl(String jsonMessage,String corelationID) throws ClientAbortException, IOException {
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		StringEntity apiMessage=new StringEntity(jsonMessage);
		
		HttpPost postRequest = new HttpPost(properties.getDestinationURL());
		postRequest.addHeader("content-type","application/json");
		postRequest.addHeader("X-Correlation-Id",corelationID);
		postRequest.setEntity(apiMessage);
		HttpResponse response = httpClient.execute(postRequest);
		System.out.println(corelationID);
		if(response.getStatusLine().getStatusCode()==200)
		{
			System.out.println( "returning True :"+corelationID);
			return true;
		}
		else {
				System.out.println( "\nreturning False :"+corelationID+" reponse Code: "+response.getStatusLine().getStatusCode()+"\n");
				return false;
		}
			
	}
}
