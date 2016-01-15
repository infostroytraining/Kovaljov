package ua.nure.infostroy.log4j.appender;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;


@Plugin(name="CustomAppender", category="Core", elementType="appender", printObject=true)
public class CustomAppender extends AbstractAppender{
	private static final long serialVersionUID = 1L;
	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    public CustomAppender() {
    	super("",null,null);
    }
    protected CustomAppender(String name, Filter filter,
            Layout<? extends Serializable> layout, final boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }
    private void sendPost(String message) throws Exception {
    	HttpClient httpclient = HttpClients.createDefault();
    	HttpPost httppost = new HttpPost("http://localhost:8080/Task2/app/logs");
    	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
    	params.add(new BasicNameValuePair("logEvent", message));
    	httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    	HttpResponse response = httpclient.execute(httppost);
	}
    private String getMessage(LogEvent event) {
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SS");
    	StringBuilder message = new StringBuilder();
    	message.append(sdf.format(new Date(event.getTimeMillis())));
    	message.append(" ");
    	message.append(event.getLevel());
    	message.append(" ");
    	message.append(event.getLoggerName());
    	message.append(":");
    	message.append(event.getSource().getMethodName());
    	message.append(":");
    	message.append(event.getSource().getLineNumber());
    	message.append(" - ");
    	message.append(event.getMessage().getFormattedMessage());
    	return message.toString();
    }
    @Override
    public void append(LogEvent event) {
        readLock.lock();
        try {
        	String message = getMessage(event);
        	sendPost(message);
        } catch (Exception ex) {
            if (!ignoreExceptions()) {
            	throw new AppenderLoggingException(ex);
            }
        } finally {
            readLock.unlock();
        }
    }

    // Your custom appender needs to declare a factory method
    // annotated with `@PluginFactory`. Log4j will parse the configuration
    // and call this factory method to construct an appender instance with
    // the configured attributes.
    @PluginFactory
    public static CustomAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("otherAttribute") String otherAttribute) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImpl");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new CustomAppender(name, filter, layout, true);
    }
}
