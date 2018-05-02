package software.design.teamorangecalsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import java.util.Date;

public class AddEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
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
        }catch(Exception e) {
            Toast invalidDate = Toast.makeText(this, "Invalid Start Date!  Try again", Toast.LENGTH_LONG);
            invalidDate.show();
            System.out.println("caught!");
            return;
        }
        Integer startDay = Integer.parseInt(splitStartDate[1]);
        Integer startMonth = Integer.parseInt(splitStartDate[0]) - 1;
        Integer startYear = Integer.parseInt(splitStartDate[2]);


        String[] splitEndDate = new String[3];
        try {
            splitEndDate = dateInputEnd.split("/");
        }catch(Exception e) {
            Toast invalidDate = Toast.makeText(this, "Invalid End Date!  Try again", Toast.LENGTH_LONG);
            invalidDate.show();
            System.out.println("caught!");
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
            //System.out.println("caught!");
            return;
        }
        Date startDate = myCal.getTime();

        try {
            myCal.set(endYear, endMonth, endDay, hrInputEnd, minInputEnd);
        }catch(Exception e){
            Toast invalidDate = Toast.makeText(this, "Invalid Date!  Try again", Toast.LENGTH_SHORT);
            invalidDate.show();
            //System.out.println("caught!");
            return;
        }
        Date endDate = myCal.getTime();

        /*
        System.out.println("titleInput = " + titleInput);
        System.out.println("start date = " + startDate);
        System.out.println("end date = " + endDate);
        System.out.println("inputNotes = " + inputNotes);
        System.out.println("inputLocation = " + inputLocation);
        */





    }
}
