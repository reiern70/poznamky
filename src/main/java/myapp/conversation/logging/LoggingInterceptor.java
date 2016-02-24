/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.conversation.logging;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author te071784
 */
@Interceptor
@LogAnnotation
public class LoggingInterceptor implements Serializable {
    private static final long serialVersionUID = 8139854519874743530L;
    
    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        Log.LIFECYCLE.debug(getClass().getSimpleName() + " Entering method: " + ctx.getMethod().getName() + " of class " + ctx.getMethod().getDeclaringClass().getName());
        return ctx.proceed();
    }
}
