package myapp.conversation.components;

import myapp.conversation.Client;
import org.apache.wicket.PageReference;
import org.apache.wicket.markup.html.link.Link;

import javax.inject.Inject;

/**
 * Created by racak on 02-Feb-16.
 */
public class BackButton extends Link {

    private int prevPageId;

    public BackButton(){
        super(null);

    }
    public BackButton(String id, int pageId) {
        super(id);
        this.prevPageId = pageId;
    }

    @Override
    public void onClick() {
        PageReference ref = new PageReference(prevPageId);
        setResponsePage(ref.getPage());
    }
}
