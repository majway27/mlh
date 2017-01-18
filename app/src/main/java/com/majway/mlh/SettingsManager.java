package com.majway.mlh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsManager extends AppCompatActivity {

    //private TextView settingsList;
    EditText ed1,ed2,ed3;
    Button b1;
    public static final String mlhInstancePreferences = "mlhInstancePreferencesFile" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_manager);
        //Intent intent = getIntent();
        //settingsList=(TextView)findViewById(R.id.resultView1);
        //settingsList.setText("Get Ready!");

        ed1=(EditText)findViewById(R.id.editText1);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.btnEditSettings);
        sharedpreferences = getSharedPreferences(mlhInstancePreferences, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = ed1.getText().toString();
                String ph = ed2.getText().toString();
                String e = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(SettingsManager.this, "Thanks", Toast.LENGTH_LONG).show();
            }
        });
    }

}
