package db_manager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public class InsertNewsQuery {

	public InsertNewsQuery() {
		// TODO Auto-generated constructor stub
	}
	
	 private Connection connect(String db_path) {
	        // SQLite connection string
	        String url = "jdbc:sqlite:"+db_path;
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 
	 public void insert(String db_path, HashMap<String, String> data) {
		 	
	        String sql = "INSERT INTO naver_news( date, article_title, article_text, url, article_url, timestamp) VALUES(?,?, ?, ?, ?,?)";
	        
	        
	        
			try (Connection conn = this.connect(db_path);
	        		
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
				//System.out.println("Insertion");
				 int i = 1;
				 //System.out.println(data);
				 
				 
			  
				 if ( data.size()!=0) {
					  		   
			    	  pstmt.setString(1, data.get("date"));
			    	  pstmt.setString(2, data.get("article_title"));
			    	  pstmt.setString(3, data.get("article_text"));
			    	  pstmt.setString(4, data.get("url"));
			    	  pstmt.setString(5, data.get("article_url"));
			    	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			          pstmt.setString(6, timestamp.toString());
			    	  
		         int row =  pstmt.executeUpdate();
		         System.out.println(row); //1
					 
				 }
					  
				 conn.close();
			    }
			  
	            
	            
	            
	         catch (SQLException e) {
	            System.out.println(e.getMessage());
	            
	        }
	    }
}
