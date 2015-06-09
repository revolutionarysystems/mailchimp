package uk.co.revsys.mailchimp.camel;

import com.ecwid.mailchimp.MailChimpClient;
import org.apache.camel.Processor;
import uk.co.revsys.esb.component.ParameterAwareProcessor;

public abstract class MailChimpProcessor implements Processor, ParameterAwareProcessor {

    private MailChimpClient mailChimpClient;
    private String apiKey;

    public MailChimpClient getMailChimpClient() {
        return mailChimpClient;
    }

    public void setMailChimpClient(MailChimpClient mailChimpClient) {
        this.mailChimpClient = mailChimpClient;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
