package com.majway.mlh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.majway.mlh.appointment.AppointmentBroker;
import com.majway.mlh.utils.MothersSharedPreferences;
import com.majway.mlh.utils.SettingsManager;


public class MainActivity extends AppCompatActivity {

    private TextView welcomeMsgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Welcome Message
        MothersSharedPreferences msp = new MothersSharedPreferences(MainActivity.this);
        welcomeMsgView=(TextView)findViewById(R.id.welcomeMsgView1);
        welcomeMsgView.setText("Welcome: " + msp.getSetting("friendlyNameKey"));
        //welcomeMsgView.setText("Welcome: ");
    }

    @Override
    public void onResume()
    {  /**  This is to set friendly name after first time it is set in settings
            and user hits "back" button to previous activity.  Otherwise app
            would need restart. Also handles pauses */
        super.onResume();
        MothersSharedPreferences msp = new MothersSharedPreferences(this);
        welcomeMsgView.setText("Welcome: " + msp.getSetting("friendlyNameKey"));
    }

    /** Called when the user clicks the Send button */
    public void scheduleAppointment(View view) {
        Intent intent = new Intent(this, AppointmentBroker.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Send button */
    public void checkQueue(View view) {
        // Do something in response to button
    }

    /** Called when the user clicks the Send button */
    public void manageSettings(View view) {
        Intent intent = new Intent(this, SettingsManager.class);
        startActivity(intent);
    }

}
