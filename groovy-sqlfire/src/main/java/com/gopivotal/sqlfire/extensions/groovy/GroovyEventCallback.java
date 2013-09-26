/**
 * 
 */
package com.gopivotal.sqlfire.extensions.groovy;

import java.sql.SQLException;

import com.vmware.sqlfire.callbacks.Event;
import com.vmware.sqlfire.callbacks.EventCallback;

/**
 * @author cdelashmutt
 *
 */
public class GroovyEventCallback 
extends GroovyBase
implements EventCallback {

	/* (non-Javadoc)
	 * @see com.vmware.sqlfire.callbacks.EventCallback#close()
	 */
	public void close() throws SQLException {
	}

	/* (non-Javadoc)
	 * @see com.vmware.sqlfire.callbacks.EventCallback#onEvent(com.vmware.sqlfire.callbacks.Event)
	 */
	public void onEvent(Event event) throws SQLException {
		script.invokeMethod("onEvent", new Object[] {event});
	}

}
