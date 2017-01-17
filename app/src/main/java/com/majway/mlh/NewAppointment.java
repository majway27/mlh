package com.majway.mlh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.app.ProgressDialog;


public class NewAppointment extends AppCompatActivity {

    private TextView returnStatus;
    private Spinner svc_id_spinner, e_id_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        Intent intent = getIntent();
        // Setup output window
        returnStatus=(TextView)findViewById(R.id.textView1);
        returnStatus.setText("Get Ready!");
        // Setup spinners
        svc_id_spinner = (Spinner) findViewById(R.id.svc_id_spinner);
        e_id_spinner = (Spinner) findViewById(R.id.e_id_spinner);
    }

    public void onBtnClicked(View v)
    {
        if(v.getId() == R.id.btnSendAppointmentRequest)
        {
            String svc_id_spinner_value = String.valueOf(svc_id_spinner.getSelectedItem());
            String e_id_spinner_value = String.valueOf(e_id_spinner.getSelectedItem());
            // Debug
            MessageBox(svc_id_spinner_value);
            MessageBox(e_id_spinner_value);
            AsyncTaskRunner runner = new AsyncTaskRunner();
            // Run Mechanize call with string args
            runner.execute(svc_id_spinner_value,e_id_spinner_value);
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

                resp = aptsvc.TestCallAppointmentService(params[0], params[1]);
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
