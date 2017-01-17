package com.majway.mlh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void requestAppointment(View view) {
        Intent intent = new Intent(this, AppointmentBroker.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Send button */
    public void scheduleAppointment(View view) {
        // Do something in response to button
    }

    /** Called when the user clicks the Send button */
    public void checkQueue(View view) {
        // Do something in response to button
    }

}
