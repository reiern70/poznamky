package myapp.conversation.base;

import myapp.conversation.Client;
import myapp.conversation.screens.SomeAnotherPage;
import myapp.conversation.logging.Log;
import org.apache.wicket.PageReference;
import org.slf4j.MDC;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 * Created by racak on 01-Feb-16.
 */
public class BasePage extends BlankPage {

    @Inject
    protected Client client;

    @Inject
    protected Conversation conversation;

    private int prevPageId;

    public BasePage(){

    }

    public BasePage(int prevPageId){
        this.prevPageId = prevPageId;
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
    }

    @Override
    protected void onAfterRender() {
        super.onAfterRender();
        client.setCurrentPageId(getPageId());
    }

    public int getPrevPageId() {
        return prevPageId;
    }

    public void setPrevPageId(int prevPageId) {
        this.prevPageId = prevPageId;
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        Log.AUDIT.debug("dsd");
    }
}
