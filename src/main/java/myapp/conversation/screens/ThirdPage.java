/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.conversation.screens;

import myapp.conversation.base.BasePage;
import myapp.conversation.logging.Log;
import myapp.conversation.components.BackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author te071784
 */
public class ThirdPage extends BasePage {

    public ThirdPage(int prevId) {
        super(prevId);
        Form form = new Form("form", new CompoundPropertyModel(client)){

            @Override
            protected void onSubmit() {
                setResponsePage(new FourthPage(getPageId()));
            }
            
        };
        form.add(new TextField("name"){

            @Override
            public boolean isEnabled() {
                return false;
            }
            
        });
        form.add(new TextField("lastName"){

            @Override
            public boolean isEnabled() {
                return false;
            }
            
        });
        form.add(new TextField("nickName"){

            @Override
            public boolean isEnabled() {
                return false;
            }
            
        });
        form.add(new TextField("age"){

            @Override
            public boolean isEnabled() {
                return false;
            }
            
        });
        form.add(new TextField("title"));
        add(form);

        add(new BackButton("backButton", getPrevPageId()));
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        Log.AUDIT.debug("");
    }

    @Override
    protected void onAfterRender() {
        super.onAfterRender();
        Log.AUDIT.debug("");
    }
}
