package db_manager;
// https://www.sqlitetutorial.net/download-install-sqlite/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



	public class Connect {
	     /**
	     * Connect to a sample database
	     */
	    public static void connect(String db_path) {
	        Connection conn = null;
	        try {
	            // db parameters
	            String url = "jdbc:sqlite:" + db_path;
	            // create a connection to the database
	            conn = DriverManager.getConnection(url);
	            
	            System.out.println("Connection to SQLite has been established.");
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
	    }

	
	/*
	public void insertToDB(arr) {
		this.headline = headline;
	}
	
	public void DBquery(query) {
		this.headline = headline;
	}
	*/
	}
	
	


