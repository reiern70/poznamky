package myapp.conversation.requests;

import myapp.conversation.CustomSession;
import myapp.conversation.screens.HomePage;
import myapp.conversation.logging.Log;
import org.apache.wicket.cdi.ConversationExpiredException;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.protocol.http.PageExpiredException;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.string.StringValue;
import org.slf4j.MDC;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.NonexistentConversationException;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CustomRequestCycleListener extends AbstractRequestCycleListener implements Serializable {

    @Inject
    private Conversation conversation;

    @Override
    public void onBeginRequest(RequestCycle cycle) {
        MDC.put("requestId", UUID.randomUUID().toString());

        if (CustomSession.exists()) {
            MDC.put("sessionId", CustomSession.get().getId());
            if (CustomSession.getUserId() != null) {
                MDC.put("userId", CustomSession.getUserId());
            } else {
                MDC.remove("userId");
            }

            if(conversation != null){
                MDC.put("conversationId", conversation.getId());
            }else{
                MDC.remove("conversationId");
            }
        } else {
            MDC.clear();
        }

        Log.REQUEST_PROCESSING.debug("Params " + getParamString(cycle));
    }

    @Override
    public void onEndRequest(RequestCycle cycle) {
        Log.REQUEST_PROCESSING.debug("Params " + getParamString(cycle));
    }

    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception ex) {
        if (ex instanceof ConversationExpiredException ||
                ex instanceof NonexistentConversationException) {
            Log.LIFECYCLE.debug("Conversation expired, ex={}", ex.getMessage());
            return new RenderPageRequestHandler(new PageProvider(HomePage.class));
        }

        if (ex instanceof PageExpiredException) {
            Log.LIFECYCLE.debug("Page expired, ex={}", ex.getMessage());
            return new RenderPageRequestHandler(new PageProvider(HomePage.class));
        }

        Log.LIFECYCLE.debug("Unknown exception ex={}" + ex.getMessage());
        return null;
    }

    private StringBuilder getParamString(RequestCycle cycle) {
        Request req = cycle.getRequest();
        StringBuilder bld = new StringBuilder();
        Set<String> params = req.getRequestParameters().getParameterNames();
        bld.append("[");
        for (String name : params) {
            bld.append(name);
            bld.append("=");
            List<StringValue> values = req.getRequestParameters().getParameterValues(name);
            bld.append(values);
        }
        bld.append("]");
        return bld;
    }
}