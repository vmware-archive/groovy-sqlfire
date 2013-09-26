/**
 * 
 */
package com.gopivotal.sqlfire.extensions.groovy;

import java.sql.SQLException;

import com.vmware.sqlfire.callbacks.RowLoader;

/**
 * @author cdelashmutt
 *
 */
public class GroovyRowLoader
extends GroovyBase
implements RowLoader {
	
	/* (non-Javadoc)
	 * @see com.vmware.sqlfire.callbacks.RowLoader#getRow(java.lang.String, java.lang.String, java.lang.Object[])
	 */
	public Object getRow(String schema, String table, Object[] primaryKey)
			throws SQLException {
		return script.invokeMethod("getRow", new Object[] {schema, table, primaryKey});
	}

}
