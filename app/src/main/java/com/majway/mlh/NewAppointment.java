package com.majway.mlh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.app.ProgressDialog;


public class NewAppointment extends AppCompatActivity {

    private TextView returnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        Intent intent = getIntent();
        returnStatus=(TextView)findViewById(R.id.textView1);
        returnStatus.setText("Get Ready!");
    }

    public void onBtnClicked(View v)
    {
        if(v.getId() == R.id.btnSendAppointmentRequest)
        {
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute();
            MessageBox("Request Sent");
        }
    }

    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            onProgressUpdate("Scheduling Appointment.."); // Calls onProgressUpdate()
            AppointmentService aptsvc = new AppointmentService();

            try {
                /*resp = com.majway.mlh.CallAppointmentService("Bob", "Uncle");
                if (!(resp.contains(" Successfully dumped 2 post variables"))) {
                    throw new Exception("Appointment Request Call Failed!");
                }
                return resp;*/

                resp = aptsvc.TestCallAppointmentService("Bob", "Uncle");
                if (!(resp.contains(" Successfully dumped 2 post variables"))) {
                    throw new Exception("Appointment Request Call Failed!");
                }
                return resp;

            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            returnStatus.setText(result);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(NewAppointment.this,
                    "ProgressDialog",
                    "Status: Starting Call");
        }

        protected void onProgressUpdate(String result) {
            progressDialog.setMessage("Status: " + result);
        }
    }

 }
