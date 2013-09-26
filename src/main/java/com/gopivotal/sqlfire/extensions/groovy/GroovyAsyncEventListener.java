/**
 * 
 */
package com.gopivotal.sqlfire.extensions.groovy;

import java.util.List;

import com.vmware.sqlfire.callbacks.AsyncEventListener;
import com.vmware.sqlfire.callbacks.Event;

/**
 * @author cdelashmutt
 *
 */
public class GroovyAsyncEventListener
extends GroovyBase
implements AsyncEventListener {

	public void close() {
	}

	@Override
	public void init(String scriptName) {
		try {
			super.init(scriptName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean processEvents(List<Event> events) {
		return (Boolean)script.invokeMethod("processEvents", new Object[] {events});
	}

	public void start() {
	}

}
