package myapp.conversation.components;

import myapp.conversation.Client;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Created by te071784 on 8. 2. 2016.
 */
public abstract class Slider<T extends Client> extends Panel {
    private boolean debug = false;
    private int min;
    private int max;
    private int maxWithException;
    private int maxWithoutException;

    public Slider(String id, IModel<?> model) {
        super(id, model);
        setMarkupId(id);

        Label slider = new Label("slider");
        slider.setMarkupId(getId());
        add(slider);

        WebMarkupContainer inputWrapper = new WebMarkupContainer("inputWrapper");
        inputWrapper.setMarkupId(id + "-" + inputWrapper.getId());
        TextField sliderInput =  new TextField("sliderInput", Model.of(""));
        sliderInput.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                System.out.println("ble");
            }
        });
        sliderInput.setMarkupId(getId() + "-input");
        inputWrapper.add(sliderInput);
        add(inputWrapper);

        // debug fields for setting min, max
        WebMarkupContainer debugFields = new WebMarkupContainer("debugFields") {
            @Override
            public boolean isVisible() {
                return debug;
            }
        };

        debugFields.setMarkupId(id + "-" + debugFields.getId());
        add(debugFields);
    }

    public abstract int getMin();

    public abstract int getMax();

    public abstract int getMaxWithException();

    public abstract int getMaxWithoutException();

    //    min: 0,
//    max: 1000,
//    sliderId: 'slider',
//    inputId: 'input'
    @Override
    public void renderHead(IHeaderResponse response) {
        String jsElId = getId();
        String sliderInitScript = "$(document).ready(function(){" +
                "var " + jsElId  + " = new PacolSlider({ " +
                    "min: " + getMin() + " , " +
                    "max: " + getMax() + ", " +
                    "sliderId: '" + getId() + "', " +
                    "inputId: '" + getId() + "-input" + "' }); " +
                ""  + jsElId + ".initSlider(); });";
        response.render(JavaScriptHeaderItem.forScript(sliderInitScript, getId() + "dsac"));
    }
}
