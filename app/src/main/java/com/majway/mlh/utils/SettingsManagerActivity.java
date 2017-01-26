package com.majway.mlh.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.majway.mlh.R;

public class SettingsManagerActivity extends AppCompatActivity {

    private TextView settingsList;
    EditText ed1,ed2,ed3, ed4, ed5, ed6;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_manager);
        settingsList=(TextView)findViewById(R.id.settingsView1);

        b1=(Button)findViewById(R.id.btnEditSettings);
        b2=(Button)findViewById(R.id.btnClearSettings);

        ed1=(EditText)findViewById(R.id.editText1);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);
        ed4=(EditText)findViewById(R.id.editText4);
        ed5=(EditText)findViewById(R.id.editText5);
        ed6=(EditText)findViewById(R.id.editText6);

        MothersSharedPreferences msp = new MothersSharedPreferences(SettingsManagerActivity.this);
        msp.setChildTest();
        refreshLocalView();
        // Setup Controls
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MothersSharedPreferences msp = new MothersSharedPreferences(SettingsManagerActivity.this);
                msp.setSetting("friendlyNameKey", ed1.getText().toString());
                msp.setSetting("userNameKey", ed2.getText().toString());
                msp.setSetting("passwordKey", ed3.getText().toString());
                msp.setSetting("instanceKey", ed4.getText().toString());
                msp.setSetting("customerIdKey", ed5.getText().toString());
                msp.setSetting("locationIdKey", ed6.getText().toString());

                refreshLocalView();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MothersSharedPreferences msp = new MothersSharedPreferences(SettingsManagerActivity.this);
                msp.clearSetting();
                refreshLocalView();
            }
        });
    }

    // Manage Primary Settings Activity TextView
    public void refreshLocalView() {
        MothersSharedPreferences msp = new MothersSharedPreferences(SettingsManagerActivity.this);
        // Init TextView
        settingsList.setText("Here are your new stored values: " + "\n");
        settingsList.append("Your Name: " +  msp.getSetting("friendlyNameKey")+ "\n");
        settingsList.append("Login User Name: " +  msp.getSetting("userNameKey")+ "\n");
        settingsList.append("Password: " +  msp.getSetting("passwordKey")+ "\n");
        settingsList.append("Instance: " +  msp.getSetting("instanceKey")+ "\n");
        settingsList.append("Customer ID: " +  msp.getSetting("customerIdKey")+ "\n");
        settingsList.append("Location ID: " +  msp.getSetting("locationIdKey")+ "\n");
        settingsList.append("Children: " +  msp.getSetting("Children"));

        // Init Fields
        ed1.setText(msp.getSetting("friendlyNameKey"));
        ed2.setText(msp.getSetting("userNameKey"));
        ed3.setText(msp.getSetting("passwordKey"));
        ed4.setText(msp.getSetting("instanceKey"));
        ed5.setText(msp.getSetting("customerIdKey"));
        ed6.setText(msp.getSetting("locationIdKey"));
    }
}
