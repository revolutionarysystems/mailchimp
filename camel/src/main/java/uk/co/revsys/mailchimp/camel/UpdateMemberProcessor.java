package uk.co.revsys.mailchimp.camel;

import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.v2_0.lists.Email;
import com.ecwid.mailchimp.method.v2_0.lists.SubscribeMethod;
import com.ecwid.mailchimp.method.v2_0.lists.UpdateMemberMethod;
import org.apache.camel.Exchange;

public class UpdateMemberProcessor extends MailChimpProcessor {

    private String list;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    @Override
    public void process(Exchange exchng) throws Exception {
        UpdateMemberMethod updateMemberMethod = new UpdateMemberMethod();
        updateMemberMethod.apikey = getApiKey();
        updateMemberMethod.id = getList();
        updateMemberMethod.email = new Email();
        updateMemberMethod.email.email = (String) exchng.getProperty("emailAddress");
        updateMemberMethod.replace_interests = false;
        updateMemberMethod.merge_vars = new MailChimpObject();
        updateMemberMethod.merge_vars.put("EMAIL", (String)exchng.getProperty("newEmailAddress"));
        System.out.println("updateMemberMethod = " + updateMemberMethod.toJson());
        Email result = getMailChimpClient().execute(updateMemberMethod);
        System.out.println("result = " + result.toJson());
        exchng.getIn().setBody(result.toJson());
    }

}
