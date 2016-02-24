/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.conversation.screens;

import myapp.conversation.Client;
import myapp.conversation.base.BasePage;
import myapp.conversation.components.Slider;
import myapp.conversation.logging.Log;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author te071784
 */
public class HomePage extends BasePage {

    public HomePage() {
        super();
        conversation.begin();
        Log.AUDIT.debug("Conversation begin, conversationId={}", conversation.getId());
        Slider sl = new Slider("sumSlider", Model.of("")){
            @Override
            public int getMin() {
                return 200;
            }

            @Override
            public int getMax() {
                return 10000;
            }

            @Override
            public int getMaxWithException() {
                return 6000;
            }

            @Override
            public int getMaxWithoutException() {
                return 5000;
            }
        };
        sl.setMarkupId("blebleble");
        add(sl);
        Form form = new Form("form1", new CompoundPropertyModel<Client>(client)){

            @Override
            protected void onSubmit() {
                setResponsePage(new SecondPage(getPageId()));
            }
            
        };
        final TextField name = new TextField("name");
        name.setOutputMarkupId(true);
        name.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(name);
            }
        });
        form.add(name);
        form.add(new TextField("lastName"));
        add(form);
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
