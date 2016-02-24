package myapp.conversation.screens;

import myapp.conversation.base.BasePage;
import myapp.conversation.components.BackButton;
import myapp.conversation.logging.Log;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Created by racak on 01-Feb-16.
 */
public class FourthPage extends BasePage {

    public FourthPage(int prevId){
        super(prevId);
        setDefaultModel(new CompoundPropertyModel<Object>(client));
        add(new Label("name"));
        add(new Label("lastName"));
        add(new Label("nickName"));
        add(new Label("age"));
        add(new Label("title"));
        add(new Link("link"){
            @Override
            public void onClick() {
                setResponsePage(new SomeAnotherPage());
            }
        });

        add(new BackButton("backButton", getPrevPageId()));
    }

}
