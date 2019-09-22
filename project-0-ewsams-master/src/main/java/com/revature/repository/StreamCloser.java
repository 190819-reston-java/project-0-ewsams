
package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Helper class used to close open streams
 * @author 
 *
 */
public class StreamCloser {
	private static Logger logger = Logger.getLogger(StreamCloser.class.getName());
	public static void close(ResultSet resource) {
		if(resource != null) {
			try {
				resource.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occur", e);
			}
		}
	}
	
	public static void close(Statement resource) {
		if(resource != null) {
			try {
				resource.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occur", e);
			}
		}
	}
	
	public static void close(Connection resource) {
		if(resource != null) {
			try {
				resource.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occur", e);
			}
		}
	}
	
}