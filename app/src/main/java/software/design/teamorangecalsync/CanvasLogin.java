package software.design.teamorangecalsync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CanvasLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_login);
    }

    public void canvasCall(View view) {
        CanvasCalendar.token = ((EditText)(findViewById(R.id.editText))).getText().toString();
        MainActivity.canvas = true;
        MainCalendar.linkCanvas();

        //finishActivity(1);
        MainActivity.canvas = false;
        Intent activityIntent = new Intent(this, MainActivity.class);
        startActivity(activityIntent);

    }
}
