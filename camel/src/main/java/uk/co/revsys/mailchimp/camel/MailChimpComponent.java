package uk.co.revsys.mailchimp.camel;

import com.ecwid.mailchimp.MailChimpClient;
import java.util.Map;

import org.apache.camel.Processor;
import uk.co.revsys.esb.component.MappedProcessorComponent;

public class MailChimpComponent extends MappedProcessorComponent{

    private MailChimpClient mailChimpClient = new MailChimpClient();
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey){
        this.apiKey = apiKey;
    }
    
    @Override
    protected void populateMappings(Map<String, Class<? extends Processor>> mappings) {
        mappings.put("subscribe", SubscribeProcessor.class);
    }

    @Override
    protected void configureProcessor(Processor processor) {
        System.out.println("configure");
        ((MailChimpProcessor)processor).setMailChimpClient(mailChimpClient);
        ((MailChimpProcessor)processor).setApiKey(apiKey);
    }
    
}
