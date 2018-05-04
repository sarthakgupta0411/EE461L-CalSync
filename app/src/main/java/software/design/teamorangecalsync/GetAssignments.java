package software.design.teamorangecalsync;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetAssignments implements Runnable {
	String courseId;
	String Access_Token;
	String page;
	String response;
	String course;
	CopyOnWriteArrayList<Event> content;
	
	
	public GetAssignments(String courseid){
		this.courseId = courseId;
		this.Access_Token = "1017~iPDnl3GFOa88Pf7cklx9pJ5Sre7ua4mA6DifYmIBn9fvdmYlqvKtJrLxxQ1MkKSt";
	}
	public GetAssignments(String page, String CourseId, String token,CopyOnWriteArrayList<Event> content, String course ){
		this.courseId = CourseId;
		this.Access_Token = token;
		this.content = content;
		this.page = page;
		this.course = course;
	}
	
	@Override
	public void run() {
		
		try {
	         URL placeUrl = new URL("https://utexas.instructure.com/api/v1/courses/"+this.courseId+"/assignments?page="+ page +"&access_token=" + this.Access_Token);
	         HttpURLConnection connection = (HttpURLConnection)placeUrl.openConnection();
	         connection.setRequestMethod("GET");
	    
	         //System.out.println("here");

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
	           //  System.out.print(Integer.toString(responseCode));
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
	    	 //System.out.println(page + "  " + response);
	         JSONArray j = new JSONArray(response);
	        for(int i = 0; i < j.length(); i++){
	        	String name = j.getJSONObject(i).get("name").toString();
	        	String due = j.getJSONObject(i).get("due_at").toString(); 
	        	String des = j.getJSONObject(i).get("description").toString(); 
	        	if(!due.equals("null")){
	        		 String[] s = due.split("-|T|\\:|Z");
	        	    	Calendar c = new GregorianCalendar(Integer.parseInt(s[0]),Integer.parseInt(s[1])-1,Integer.parseInt(s[2]),Integer.parseInt(s[3]),Integer.parseInt(s[4]),Integer.parseInt(s[5]));
	        	        c.add(Calendar.HOUR, -6);
	        	        content.add(new Assignment(name,c.getTime(),des,this.course));	//TODO: fix
	        	}
	        	 }
	         }
	
	         
	    
	     
	     catch (JSONException e){
	    	 System.out.println(e.toString());
	     }
		
	}
}


