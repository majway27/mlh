package com.majway.mlh;

import com.gistlabs.mechanize.Mechanize;
import com.gistlabs.mechanize.document.AbstractDocument;
import com.gistlabs.mechanize.document.html.HtmlDocument;
import com.gistlabs.mechanize.document.html.form.Form;
import com.gistlabs.mechanize.impl.MechanizeAgent;
import com.gistlabs.mechanize.interfaces.Resource;
import com.gistlabs.mechanize.parameters.Parameters;
import com.gistlabs.mechanize.interfaces.document.Document;

public class AppointmentService {

    // ToDo pull agent instatiantion out of methods
    //private MechanizeAgent agent = new MechanizeAgent();

    protected String TestCallAppointmentService(String... params) {
        MechanizeAgent agent = new MechanizeAgent();
        Parameters parameters = new Parameters()
                .add("service_id",  params[0])
                .add("e_id", params[1]);
        HtmlDocument page = agent.post("http://posttestserver.com/post.php", parameters);
        String pageString = page.htmlElements().toString();
        System.out.println(pageString);
        return pageString;
        //resp = "test";
    }

    protected String CallAppointmentService(String... params) {
        MechanizeAgent agent = new MechanizeAgent();
        Parameters parameters = new Parameters()
                .add("service_id", params[0])
                .add("e_id", params[1]);
        HtmlDocument page = agent.post("http://posttestserver.com/post.php", parameters);
        String pageString = page.htmlElements().toString();
        System.out.println(pageString);
        return pageString;
        //resp = "test";
    }

    protected String LoginAppointmentService() {
        MechanizeAgent agent = new MechanizeAgent();
        agent.setUserAgent("Mac Safari");
        AbstractDocument myPage = agent.get("http://url");
        Form myForm = myPage.form("auth_form");
        myForm.get("loginname").setValue("user");
        myForm.get("password").setValue("pass");
        HtmlDocument page = myForm.submit();
        String pageString = page.htmlElements().toString();
        System.out.println(pageString);
        return pageString;
    }

    protected String LogoutAppointmentService() {
        MechanizeAgent agent = new MechanizeAgent();
        agent.setUserAgent("Mac Safari");
        HtmlDocument page = agent.get("http://url");
        String pageString = page.htmlElements().toString();
        System.out.println(pageString);
        return pageString;
    }

    /** Pick Time Frame.  See apt-plus-timescale.csv in project root. **/
    protected void SvcIDAppointmentService() {
        MechanizeAgent agent = new MechanizeAgent();

    }

    /** Pick kid type. **/
    protected void EIDAppointmentService() {
        MechanizeAgent agent = new MechanizeAgent();

    }

    /** Set other key data mimicing "javascript:doSubmit() that validates and posts".  Mechanize doesn't support Javascript.  Set Other hidden data on form.
        Use Mechanize#post, for some reason submitting the report_form object(like above) doesn't work. Notably uri picks up a trailing action value and the action field on the form isn't set..
        This selects an available day to set a reservation.
        Returns day's available slots for reservation.  **/
    protected void ViewAptsAppointmentService() {
        MechanizeAgent agent = new MechanizeAgent();

    }

    /** Choose/submit time slot on day's calendar
       Checks a time slots availability, and if availabile soft-reserves it.
       Returns confirmation to be finalized below. **/
    protected void ConfirmAppointmentService() {
        MechanizeAgent agent = new MechanizeAgent();

    }

    /** Finalizes a selected reservation.
        Returns success message if reservation was successful. **/
    protected void FinalizeAppointmentService() {
        MechanizeAgent agent = new MechanizeAgent();

    }

}
