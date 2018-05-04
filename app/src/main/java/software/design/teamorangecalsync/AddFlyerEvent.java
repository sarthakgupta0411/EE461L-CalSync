package software.design.teamorangecalsync;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddFlyerEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flyer_event);
        dispatchTakePictureIntent();
    }

    public static final String subscriptionKey = "ba39a14c81a64f1da56ce1cb59f8a0db";
    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/ocr";



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //this code will convert a file into a byte array....hopefully


        AsyncTask<String,String,String> myTask = new AsyncTask<String,String,String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... strings) {
                HttpClient httpClient = new DefaultHttpClient();

                try
                {

                    Bitmap bm = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] b = baos.toByteArray();
                    // NOTE: You must use the same location in your REST call as you used to obtain your subscription keys.
                    //   For example, if you obtained your subscription keys from westus, replace "westcentralus" in the
                    //   URL below with "westus".
                    URIBuilder uriBuilder = new URIBuilder(uriBase);

                    uriBuilder.setParameter("language", "unk");
                    uriBuilder.setParameter("detectOrientation ", "true");

                    // Request parameters.
                    URI uri = uriBuilder.build();
                    HttpPost request = new HttpPost(uri);

                    // Request headers.
                    request.setHeader("Content-Type", "application/octet-stream");
                    request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

                    // Request body.
                    request.setEntity(new ByteArrayEntity(b));
                    // Execute the REST API call and get the response entity.
                    HttpResponse response = httpClient.execute(request);
                    HttpEntity entity = response.getEntity();

                    if (entity != null)
                    {
                        // Format and display the JSON response.
                        String jsonString = EntityUtils.toString(entity);
                        JSONObject json = new JSONObject(jsonString);
                        flyerParser(json);
                        System.out.println("REST Response:\n");
                        System.out.println(json.toString(2));
                    }
                }
                catch (Exception e)
                {
                    // Display error message.
                    System.out.println(e.getMessage());
                }
                return null;
            }

        };// ... your AsyncTask code goes here
        System.out.println("here to get info");
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB)
            myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            myTask.execute();

        //call API


    }

    public Event flyerParser(JSONObject j) throws JSONException {
        //Date sendDate = new Date();
        //Time sendTime = new Time();
        GregorianCalendar sendDate = null;
        JSONArray ja = j.optJSONArray("regions");
        ArrayList<String> allWords = new ArrayList<>();
        //Event e = new Event();
        int biggestWordHeight = 0;
        for(int i = 0; i < ja.length(); i++){
            JSONObject region = ja.getJSONObject(i);

            JSONArray lines = region.optJSONArray("lines");
            for(int k = 0; k < lines.length(); k++){
                JSONObject box = lines.getJSONObject(k);
                JSONArray words = box.getJSONArray("words");
                String[] wordDims = words.getJSONObject(0).getString("boundingBox").split(",");
                ArrayList<String> wordList = new ArrayList();
                int wordHeight = Integer.parseInt(wordDims[3]);
                if(wordHeight > biggestWordHeight){
                    biggestWordHeight = wordHeight;
                    if(!allWords.isEmpty()){
                        allWords.clear();
                    }
                    for(int p = 0; p < words.length(); p++){
                        allWords.add(words.getJSONObject(p).getString("text"));

                    }
                }else{
                    for (int s = 0; s < words.length(); s++){
                        wordList.add(words.getJSONObject(s).getString("text"));
                    }
                    for(int w = 0; w < wordList.size(); w++){
                        String word = wordList.get(w);
                        if(isMonth(word)){
                            sendDate = new GregorianCalendar(2018,whatMonth(word) ,Integer.parseInt(wordList.get(w+1).substring(0,wordList.get(w+1).length()-2)));
                            System.out.println(sendDate);
                            break;
                        }
                    }

                    if(sendDate != null) {
                        break;
                    }


                    /*String text = "";
                    for (int c = 0; c < wordList.size(); c++){
                        text += wordList.get(c) + " ";
                    }*/

                    int test = 0;

                }

            }

        }
        String title = "";
        for (int i = 0; i < allWords.size(); i++){
            title += allWords.get(i) + " ";
        }
        Date startTime = sendDate.getTime();


        return new Event(title, startTime, null, null, "flyer_calendar");




    }

    public boolean isMonth(String m){
        m = m.toLowerCase();
        if ((m.equals("january")) || (m.equals("february")) || (m.equals("march")) || (m.equals("april")) || (m.equals("may")) || (m.equals("june")) || (m.equals("july")) || (m.equals("august")) || (m.equals("september")) || (m.equals("october")) || (m.equals("november")) || (m.equals("december"))){
            return true;
        }
        return false;
    }

    public int whatMonth(String m){
        String[] monthsArray = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
        ArrayList<String> months = new ArrayList<>();
        for(String month : monthsArray) {
            months.add(month);
        }
        return months.indexOf(m.toLowerCase());
    }
    static final int REQUEST_TAKE_PHOTO = 1;
    File photoFile = null;
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            //removed from here, added as a global
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            //...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }

        }


    }




    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


}


