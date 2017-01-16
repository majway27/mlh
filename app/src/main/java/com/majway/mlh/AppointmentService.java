package com.majway.mlh;

import com.gistlabs.mechanize.Mechanize;
import com.gistlabs.mechanize.document.AbstractDocument;
import com.gistlabs.mechanize.document.html.HtmlDocument;
import com.gistlabs.mechanize.impl.MechanizeAgent;
import com.gistlabs.mechanize.interfaces.Resource;
import com.gistlabs.mechanize.parameters.Parameters;
import com.gistlabs.mechanize.interfaces.document.Document;

public class AppointmentService {

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

}
