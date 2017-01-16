package com.majway.mlh;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.gistlabs.mechanize.Mechanize;
import com.gistlabs.mechanize.document.AbstractDocument;
import com.gistlabs.mechanize.document.html.HtmlDocument;
import com.gistlabs.mechanize.impl.MechanizeAgent;
import com.gistlabs.mechanize.interfaces.Resource;
import com.gistlabs.mechanize.parameters.Parameters;
import com.gistlabs.mechanize.interfaces.document.Document;



public class NewAppointment extends AppCompatActivity {

    private TextView returnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        Intent intent = getIntent();
        String output="Get Ready!";
        returnStatus=(TextView)findViewById(R.id.textView1);
        returnStatus.setText(output);
    }

    public String sendRequest()
    {
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();
        String output = "google title";
        return output;
    }

    public void onBtnClicked(View v)
    {
        if(v.getId() == R.id.btnSendAppointmentRequest)
        {
            String output = sendRequest();
            // String output="Output from Call";
            TextView returnStatus = (TextView)findViewById(R.id.textView1);
            returnStatus.setText(output);
            MessageBox("Request Sent");
        }
    }

    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected String TestCallAppointmentService(String testparam1, String testparam2) {
        MechanizeAgent agent = new MechanizeAgent();
        Parameters parameters = new Parameters().add("param1", testparam1).add("param2", testparam2);
        HtmlDocument page = agent.post("http://posttestserver.com/post.php", parameters);
        String pageString = page.htmlElements().toString();
        System.out.println(pageString);
        return pageString;
        //resp = "test";
    }

    protected String CallAppointmentService(String testparam1, String testparam2) {
        MechanizeAgent agent = new MechanizeAgent();
        Parameters parameters = new Parameters().add("param1", testparam1).add("param2", testparam2);
        HtmlDocument page = agent.post("http://posttestserver.com/post.php", parameters);
        String pageString = page.htmlElements().toString();
        System.out.println(pageString);
        return pageString;
        //resp = "test";
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            onProgressUpdate("Scheduling Appointment.."); // Calls onProgressUpdate()
            try {
                /*resp = CallAppointmentService("Bob", "Uncle");
                if (!(resp.contains(" Successfully dumped 2 post variables"))) {
                    throw new Exception("Appointment Request Call Failed!");
                }
                return resp;*/
                resp = TestCallAppointmentService("Bob", "Uncle");
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
