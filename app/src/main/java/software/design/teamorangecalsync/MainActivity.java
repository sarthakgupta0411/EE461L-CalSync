package com.example.nikhilgaur.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void testAndroidDatabaseConnection(View view){
        System.out.println("HI");
        
       // DatabaseAccess.simpleInsert();
    }
}
