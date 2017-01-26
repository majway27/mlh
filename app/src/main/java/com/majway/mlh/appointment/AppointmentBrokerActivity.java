package com.majway.mlh.appointment;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.majway.mlh.R;


public class AppointmentBrokerActivity extends AppCompatActivity {

    private TextView returnStatus;
    private Spinner svc_id_spinner, e_id_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_broker);

        Intent intent = getIntent();
        // Setup output window
        returnStatus=(TextView)findViewById(R.id.resultView1);
        returnStatus.setText("Get Ready!");
        // Setup spinners
        svc_id_spinner = (Spinner) findViewById(R.id.svc_id_spinner);
        e_id_spinner = (Spinner) findViewById(R.id.e_id_spinner);
    }

    public void onBtnClicked(View v)
    {
        if(v.getId() == R.id.btnSendAppointmentRequest)
        {
            // Instantiate new async task/thread, can not run on main/ui thread.
            AsyncTaskRunner runner = new AsyncTaskRunner();
            // Run Mechanize call with string args
            runner.execute(
                    /** Little trick to do a pretend key/value array via xml approach to spinner contents.
                       For consistency, apt times will be a long list and I didn't want to clutter up class file with
                       that long list.
                     **/
                    getResources().getStringArray(R.array.service_id_array_values)[svc_id_spinner.getSelectedItemPosition()],
                    getResources().getStringArray(R.array.e_id_array_values)[e_id_spinner.getSelectedItemPosition()]
            );
            MessageBox("Values: " +
                    getResources().getStringArray(R.array.service_id_array_values)[svc_id_spinner.getSelectedItemPosition()]
                    + " " +
                    getResources().getStringArray(R.array.e_id_array_values)[e_id_spinner.getSelectedItemPosition()]
                    + ". Request Sent"
            );
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

            // Wrap risky network calls in T/C
            try {
                resp = aptsvc.LoginAppointmentService();
                if (resp.contains(" We could not find your login information.")) {
                    throw new Exception("Login Failed!");
                }
                resp = aptsvc.LogoutAppointmentService();
                if (!(resp.contains("Jack18"))) {
                    throw new Exception("Logout Failed!");
                }
                /* Debug testing
                resp = aptsvc.TestCallAppointmentService(params[0], params[1]);
                if (!(resp.contains(" Successfully dumped 2 post variables"))) {
                    throw new Exception("Appointment Request Call Failed!");
                } */
                resp = "Testing";
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
            progressDialog = ProgressDialog.show(AppointmentBrokerActivity.this,
                    "ProgressDialog",
                    "Status: Starting Call");
        }

        protected void onProgressUpdate(String result) {
            progressDialog.setMessage("Status: " + result);
        }
    }

 }
