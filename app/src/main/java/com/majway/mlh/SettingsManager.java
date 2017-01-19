package com.majway.mlh;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsManager extends AppCompatActivity {

    private TextView settingsList;
    EditText ed1,ed2,ed3;
    Button b1, b2;
    //public static final String mlhInstancePreferences = "mlhInstancePreferencesFile" ;
    //public static final String FriendlyName = "friendlyNameKey";
    //public static final String UserName = "userNameKey";
    //public static final String Password= "passwordKey";

    //SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_manager);
        settingsList=(TextView)findViewById(R.id.settingsView1);
        b1=(Button)findViewById(R.id.btnEditSettings);
        b2=(Button)findViewById(R.id.btnClearSettings);
        //sharedpreferences = getSharedPreferences(mlhInstancePreferences, Context.MODE_PRIVATE);

        ed1=(EditText)findViewById(R.id.editText1);
        //ed1.setText(sharedpreferences.getString("friendlyNameKey", "Your Preferred Name"));
        ed2=(EditText)findViewById(R.id.editText2);
        //ed2.setText(sharedpreferences.getString("userNameKey", "Login User Name for Appointment Service"));
        ed3=(EditText)findViewById(R.id.editText3);
        //ed3.setText(sharedpreferences.getString("passwordKey", "Login Password for Appointment Service"));
        refreshLocalView();
        // Setup Controls
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MothersSharedPreferences msp = new MothersSharedPreferences(SettingsManager.this);
                msp.setSetting("friendlyNameKey", ed1.getText().toString());
                msp.setSetting("userNameKey", ed2.getText().toString());
                msp.setSetting("passwordKey", ed3.getText().toString());

                refreshLocalView();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MothersSharedPreferences msp = new MothersSharedPreferences(SettingsManager.this);
                msp.clearSetting();
                refreshLocalView();
            }
        });
    }

    // Manage Primary Settings Activity TextView
    public void refreshLocalView() {
        MothersSharedPreferences msp = new MothersSharedPreferences(SettingsManager.this);
        // Init TextView
        settingsList.setText("Here are your new stored values: " + "\n");
        settingsList.append("Your Name: " +  msp.getSetting("friendlyNameKey")+ "\n");
        settingsList.append("Login User Name: " +  msp.getSetting("userNameKey")+ "\n");
        settingsList.append("Password: " +  msp.getSetting("passwordKey"));
        // Init Fields
        ed1.setText(msp.getSetting("friendlyNameKey"));
        ed2.setText(msp.getSetting("userNameKey"));
        ed3.setText(msp.getSetting("passwordKey"));
    }
}
