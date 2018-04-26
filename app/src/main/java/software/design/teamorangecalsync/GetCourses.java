package software.design.teamorangecalsync;

import java.io.BufferedReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;
public class GetCourses implements Runnable {
	String page; 
	String Access_Token ;
	String response = "Nothing";
	ConcurrentHashMap<String,String> content;
	public GetCourses(){
		this.page = "Nothing";
		this.Access_Token = "1017~iPDnl3GFOa88Pf7cklx9pJ5Sre7ua4mA6DifYmIBn9fvdmYlqvKtJrLxxQ1MkKSt";
		this.response = "Nothing";
		
	}
	
	
	public GetCourses(String Access, int page, ConcurrentHashMap<String,String> content){
		this.page = Integer.toString(page);
		this.Access_Token = Access;
		this.response = "Nothing";
		this.content = content;
	}

	@Override
	public void run() {
	
		 
		 
	     try {
	         URL placeUrl = new URL("https://utexas.instructure.com/api/v1/courses?page=" + page + "&access_token=" + Access_Token);
	         HttpURLConnection connection = (HttpURLConnection)placeUrl.openConnection();
	         connection.setRequestMethod("GET");
	    
//	         System.out.println("here");

	         int responseCode = connection.getResponseCode();
	         if (responseCode == HttpURLConnection.HTTP_OK) {

	             BufferedReader reader = null;

	             InputStream inputStream = connection.getInputStream();
	             StringBuffer buffer = new StringBuffer();
	             if (inputStream == null) {
	                 // Nothing to do.

	             }
	             reader = new BufferedReader(new InputStreamReader(inputStream));

	             String line;
	             while ((line = reader.readLine()) != null) {

	                 buffer.append(line + "\n");
	             }

	             if (buffer.length() == 0) {

	             }
	            //System.out.print(Integer.toString(responseCode));
	            // System.out.print(buffer);
	             response = buffer.toString();
	             

	         } else {
	            System.out.print(Integer.toString(responseCode));
	         }
	         connection.disconnect();

	     }
	     catch(Exception e){

	     }
	     
	     try{
	    	 //response = "\"canvas\": " + response;
	    	 //System.out.println(page + "  " + response);
	         JSONArray j = new JSONArray(response);
	        for(int i = 0; i < j.length(); i++){
	        	 JSONObject c = j.getJSONObject(i);
	        	 if(c.length() > 10){
	        	 if(c.get("name").toString().contains("Sp18")){
	        		// System.out.println(c.get("id").toString() + "\t" + c.get("name").toString());
	        		 content.put(c.get("name").toString(), c.get("id").toString());
	        	 }
	        	 }
	         }
	        
	         
	        // String id = c.getJSONObject("id").toString();
	        
	         //String lat = ((JSONArray)j.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
	         //String lng = ((JSONArray)j.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();
	         
	     }
	     catch (JSONException e){
	    	 System.out.println(e.toString());
	     }
		
	}
	
	
}
