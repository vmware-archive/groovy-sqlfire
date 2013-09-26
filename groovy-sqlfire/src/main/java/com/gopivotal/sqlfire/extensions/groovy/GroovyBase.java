/**
 * 
 */
package com.gopivotal.sqlfire.extensions.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author cdelashmutt
 * 
 */
public abstract class GroovyBase {

	GroovyClassLoader loader;
	GroovyObject script;

	PreparedStatement statement;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.sqlfire.callbacks.RowLoader#init(java.lang.String)
	 */
	public void init(String scriptName) throws SQLException {
		try {
			statement.setString(1, scriptName);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Class<?> groovyClass = loader.parseClass(rs.getString(1));

				script = (GroovyObject) groovyClass.newInstance();

			} else {
				throw new IllegalStateException("No script found with name "
						+ scriptName);
			}
		} catch (Exception e) {
			throw new SQLException("Error loading script: " + e.toString(), e);
		}
	}

	public GroovyBase() {
		try {
			ClassLoader parent = getClass().getClassLoader();
			loader = new GroovyClassLoader(parent);

			Connection connection = DriverManager
					.getConnection("jdbc:default:connection");
			statement = connection
					.prepareStatement("select script from GROOVY.SCRIPTS where name = ?");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
