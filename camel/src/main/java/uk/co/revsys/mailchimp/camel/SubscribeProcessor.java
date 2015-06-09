package uk.co.revsys.mailchimp.camel;

import com.ecwid.mailchimp.method.v2_0.lists.Email;
import com.ecwid.mailchimp.method.v2_0.lists.SubscribeMethod;
import org.apache.camel.Exchange;

public class SubscribeProcessor extends MailChimpProcessor {

    private String list;
    private String email;
    private boolean doubleOptIn = false;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getDoubleOptIn() {
        return doubleOptIn;
    }

    public void setDoubleOptIn(boolean doubleOptIn) {
        this.doubleOptIn = doubleOptIn;
    }

    @Override
    public void process(Exchange exchng) throws Exception {
        SubscribeMethod subscribeMethod = new SubscribeMethod();
        subscribeMethod.apikey = getApiKey();
        subscribeMethod.id = getList();
        subscribeMethod.email = new Email();
        subscribeMethod.email.email = (String) exchng.getProperty("emailAddress");
        subscribeMethod.double_optin = getDoubleOptIn();
        subscribeMethod.update_existing = true;
        System.out.println("subscribeMethod = " + subscribeMethod.toJson());
        Email result = getMailChimpClient().execute(subscribeMethod);
        System.out.println("result = " + result.toJson());
        exchng.getIn().setBody(result.toJson());
    }

}
