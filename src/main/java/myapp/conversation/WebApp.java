/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.conversation;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import myapp.conversation.requests.CustomRequestCycleListener;
import myapp.conversation.screens.HomePage;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.cdi.*;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author te071784
 */
public class WebApp extends AuthenticatedWebApplication{

    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();
        mountPage("/home", HomePage.class);
        try {
            BeanManager beanManager;
            beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
            CdiConfiguration cdiConfiguration = new CdiConfiguration(beanManager);
            cdiConfiguration.setPropagation(ConversationPropagation.NONBOOKMARKABLE);
            cdiConfiguration.configure(this);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        getRequestCycleListeners().add(new CustomRequestCycleListener());
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return CustomSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return null;
    }

}
