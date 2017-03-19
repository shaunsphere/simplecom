package com.xevate.zxing.client.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONoutString {
	
	public static String JsGetStatus(String username, String password, String urlink) {
		String status = "-1";
		ArrayList<String> allout = new ArrayList<String>();
		
		String url = urlink;
		
		if (isHttpUrlAvailable(urlink)){
		

		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    postParameters.add(new BasicNameValuePair("username", username ));  
	    postParameters.add(new BasicNameValuePair("password", password ));

		String result="";
		InputStream is=null;
			try{

			      httppost.setEntity(new UrlEncodedFormEntity(postParameters,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
			    e.printStackTrace();
			    Log.e("log_tag", "Error UnsupportedEncodingException "+e.toString());
			}  
			      
			try{  
				  HttpResponse response = httpclient.execute(httppost);
			      HttpEntity entity = response.getEntity();
			      if (entity != null) {
			      is = entity.getContent();
					try{
					      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
					      StringBuilder sb = new StringBuilder();
					      String line = null;
					      while ((line = reader.readLine()) != null) {
					              sb.append(line + "\n");
				              System.out.println(line);
					      }
					      is.close();

					      result=sb.toString();
					      
					}catch(Exception e){
					      Log.e("log_tag", "Error converting result "+e.toString());
					}
		 
					//parse json data
					try{
						JSONObject jsonresult = new JSONObject(result);
						status = jsonresult.getString("status");
						//String msg = jsonresult.getString("message");
						//System.out.println(status);
					}
					catch(JSONException e){
					      Log.e("log_tag", "Error parsing data "+e.toString());
					      status = "parsing data";
					}
			      }
			}catch(Exception e){
			      Log.e("log_tag", "Error in http connection "+e.toString());
			      status = "err in http connection";
			}

			return status;
		}
		else{
			allout.add("Connect Timeout, Please Check Host Service");
			return status;
		}
		
		}
	


		
	////////////////////////////////////////////////////
	 public static boolean isHttpUrlAvailable(String urlString) {
		    HttpURLConnection connection = null;
		    try {
		      // might as well test for the url we need to access
		      URL url = new URL(urlString);

		      connection = (HttpURLConnection) url.openConnection();
		      connection.setConnectTimeout(3000);
		  
		      connection.connect();
		      boolean success = (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
		  
		      return success;
		    } catch (IOException e) {
		  
		      return false;
		    } finally {
		      if (connection != null) {
		        connection.disconnect();
		      }
		    }
		  }
	////////////////////////////////////////////////////////////////
	}
