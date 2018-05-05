package software.design.teamorangecalsync;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;

public class AddEvent extends AppCompatActivity {

    public static Event passedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        if(passedEvent != null) {
            //it's from the API
            ((EditText)findViewById(R.id.TitleInput)).setText(passedEvent.getTitle());
            String date = passedEvent.getStartDate();
            String[] split = date.split(" ");
            ((EditText)findViewById(R.id.DateInputStart)).setText(split[0] + "/" + (passedEvent.getStartTime().getMonth() + 1) + "/" + split[2]);

        }

    }








    public void onClickOkButton(View myView){
        //Get all field values from input fields in view
        String inputNotes = ((EditText)findViewById(R.id.InputNotes)).getText().toString();
        String inputLocation = ((EditText)findViewById(R.id.InputLocation)).getText().toString();
        String minuteInputEnd = ((EditText)findViewById(R.id.MinuteInputEnd)).getText().toString();
        String hourInputEnd = ((EditText)findViewById(R.id.HourInputEnd)).getText().toString();
        String dateInputEnd = ((EditText)findViewById(R.id.DateInputEnd)).getText().toString();
        String minuteInputStart = ((EditText)findViewById(R.id.MinuteInputStart)).getText().toString();
        String hourInputStart = ((EditText)findViewById(R.id.HourInputStart)).getText().toString();
        String dateInputStart = ((EditText)findViewById(R.id.DateInputStart)).getText().toString();
        String titleInput = ((EditText)findViewById(R.id.TitleInput)).getText().toString();

        //Parse the mm/dd/yyyy
        String[] splitStartDate = new String[3];
        try {
            splitStartDate = dateInputStart.split("/");
            if(splitStartDate.length < 3){
                throw new Exception();
            }
        }catch(Exception e) {
            Toast toastStart = Toast.makeText(this, "Invalid start date!  Please try again.", Toast.LENGTH_LONG);
            toastStart.setGravity(Gravity.TOP|Gravity.LEFT, 150, 400);
            View view = toastStart.getView();
            TextView text = (TextView) view.findViewById(android.R.id.message);
            text.setTextColor(Color.parseColor("#d849d1"));
            toastStart.show();
            return;
        }


        Integer startDay = Integer.parseInt(splitStartDate[1]);
        Integer startMonth = Integer.parseInt(splitStartDate[0]) - 1;
        Integer startYear = Integer.parseInt(splitStartDate[2]);


        String[] splitEndDate = new String[3];
        try {
            splitEndDate = dateInputEnd.split("/");
            if(splitEndDate.length < 3){
                throw new Exception();
            }
        }catch(Exception e) {
            Toast toastEnd = Toast.makeText(this, "Invalid end date!  Please try again.", Toast.LENGTH_LONG);
            toastEnd.setGravity(Gravity.TOP|Gravity.LEFT, 150, 400);
            View view = toastEnd.getView();
            TextView text = (TextView) view.findViewById(android.R.id.message);
            text.setTextColor(Color.parseColor("#d849d1"));
            toastEnd.show();
            return;
        }


        Integer endDay = Integer.parseInt(splitEndDate[1]);
        Integer endMonth = Integer.parseInt(splitEndDate[0]) -1;
        Integer endYear = Integer.parseInt(splitEndDate[2]);


        //Get ints for minutes and hours
        Integer minInputStart = Integer.parseInt(minuteInputStart);
        Integer hrInputStart = Integer.parseInt(hourInputStart);
        Integer minInputEnd = Integer.parseInt(minuteInputEnd);
        Integer hrInputEnd = Integer.parseInt(hourInputEnd);

        //Create start and end dates
        Calendar myCal = Calendar.getInstance();
        try {
            myCal.set(startYear, startMonth, startDay, hrInputStart, minInputStart);
        }catch(Exception e){
            Toast invalidDate = Toast.makeText(this, "Invalid Date!  Try again", Toast.LENGTH_SHORT);
            invalidDate.show();
            return;
        }
        Date startDate = myCal.getTime();

        try {
            myCal.set(endYear, endMonth, endDay, hrInputEnd, minInputEnd);
        }catch(Exception e){
            Toast invalidDate = Toast.makeText(this, "Invalid Date!  Try again", Toast.LENGTH_SHORT);
            invalidDate.show();
            return;
        }
        Date endDate = myCal.getTime();

        /*
        try{
            if (startDate.after(endDate)) {
                System.out.println("start date later than end date!");
                throw new Exception();
            }
        }catch(Exception e) {
            Toast toastStartAfterEnd = Toast.makeText(this, "Start date cannot be later than end date! \n  Please try again.", Toast.LENGTH_LONG);
            toastStartAfterEnd.setGravity(Gravity.TOP | Gravity.LEFT, 150, 400);
            View view = toastStartAfterEnd.getView();
            TextView text = (TextView) view.findViewById(android.R.id.message);
            text.setTextColor(Color.parseColor("#d849d1"));
            toastStartAfterEnd.show();
            return;
        }
        */




    }

}
