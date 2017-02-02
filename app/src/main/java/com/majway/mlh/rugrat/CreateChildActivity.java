package com.majway.mlh.rugrat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.majway.mlh.R;
import com.majway.mlh.utils.MothersSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class CreateChildActivity extends AppCompatActivity {

    int eid;
    Button b1;
    EditText ce1,ce2;
    Spinner e_id_spinner;
    Context ctx;
    List<Child> children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_child);

        b1=(Button)findViewById(R.id.btnCreateChild);
        ce1=(EditText)findViewById(R.id.editText1);
        ce2=(EditText)findViewById(R.id.editText2);
        e_id_spinner = (Spinner) findViewById(R.id.e_id_spinner);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MothersSharedPreferences msp = new MothersSharedPreferences(CreateChildActivity.this);

                String name = ce1.getText().toString();
                String type = getResources().getStringArray(R.array.e_id_array_values)[e_id_spinner.getSelectedItemPosition()];
                int eid = Integer.parseInt(ce2.getText().toString());

                Child child = new Child(eid, name, type);
                List<Child> children = msp.getChildren(CreateChildActivity.this);

                children.add(child);
                msp.saveChildren(CreateChildActivity.this, children);
                // Close activity
                finish();
            }
        });
    }
}
