package db_manager;
// https://www.sqlitetutorial.net/download-install-sqlite/
// https://www.sqlitetutorial.net/sqlite-java/create-database/

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
	    
	    public static void createNewDatabase(String fileName) {

	        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;

	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
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
	
	


