Logging formatter pattern how to
https://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html


CDI
https://dzone.com/articles/cdi-di-p1
http://weld.cdi-spec.org/
https://www.42lines.net/2011/11/15/integrating-cdi-into-wicket/
http://www.amazon.com/JBoss-Weld-CDI-Java-Platform/dp/1782160183
!!!!! http://tomee.apache.org/examples-trunk/cdi-basic/README.html !!!!!
!!!!! http://tomee.apache.org/examples-trunk/simple-cdi-interceptor/README.html !!!!!
!!!!! http://www.javacodegeeks.com/2013/08/di-cdi-basics.html !!!!!!
!!!!! https://www.reddit.com/r/java/comments/20b9k9/guice_vs_cdi_weld/ !!!!!!!

Logging
Basic: http://www.devsniper.com/injectable-logger-with-cdi/; http://tech.krizic.net/2012/01/tip-injected-logger-using-cdi.html

/subsystem=logging/periodic-rotating-file-handler=mojlog:add(file={path=moj.log,relative-to="jboss.server.log.dir"},suffix=.yyyy-MM-dd,formatter="%-5p - BLABLA|%d|%X{sessionId}||%c{1}||%X{userId}|%F:%L|%M|%s%E%n",level=DEBUG)

/subsystem=logging/logger=mojlog.AUDIT:add(level=DEBUG)
/subsystem=logging/logger=mojlog.REQUEST_PROCESSING:add(level=DEBUG)
/subsystem=logging/logger=mojlog.LIFECYCLE:add(level=DEBUG)

/subsystem=logging/logger=mojlog.AUDIT:assign-handler(name=mojlog)
/subsystem=logging/logger=mojlog.REQUEST_PROCESSING:assign-handler(name=mojlog)
/subsystem=logging/logger=mojlog.LIFECYCLE:assign-handler(name=mojlog)

//update log format
/subsystem=logging/periodic-rotating-file-handler=mojlog:write-attribute(name="formatter", value="%-5p - BLABLA|%d|%X{sessionId}||%c{1}||%X{userId}%n|%F:%L|%M|%s%E%n") 

http://gulas.sme.sk/upload/posts/5/81/81725/large/e2520d5342c80de22841dd614f49ab59.jpg
