package myapp.conversation.screens;

import myapp.conversation.base.BlankPage;
import myapp.conversation.logging.Log;
import org.apache.wicket.markup.html.link.Link;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 * Created by racak on 01-Feb-16.
 */
public class SomeAnotherPage extends BlankPage {

    @Inject
    Conversation conversation;

    public SomeAnotherPage() {
        if (!conversation.isTransient()) {
            conversation.end();
            Log.AUDIT.debug("Conversation end, conversationId={}", conversation.getId());
        }
        add(new Link("link") {

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }
}
