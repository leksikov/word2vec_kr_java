package db_manager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public class InsertQuery {

	public InsertQuery() {
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
		 	
	        String sql = "INSERT INTO movie_description(idx,title, movie_title, movie_text, extra_movie_text, genre, country, html, timestamp) VALUES(?,?, ?, ?, ?,?, ?,?,?)";
	        
	        
	        
			try (Connection conn = this.connect(db_path);
	        		
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
				System.out.println("Insertion");
				 int i = 1;
				 //System.out.println(data);
				 
				 
			  
				 if ( data.size()!=0) {
					  pstmt.setInt(1, Integer.parseInt(data.get("idx")));			   
			    	  pstmt.setString(2, data.get("title").replaceAll("네이버 영화", ""));
			    	  pstmt.setString(3, data.get("movie_title"));
			    	  pstmt.setString(4, data.get("movie_text"));
			    	  pstmt.setString(5, data.get("extra_movie_text"));
			    	  pstmt.setString(6, data.get("genre"));
			    	  pstmt.setString(7, data.get("country"));
			    	  pstmt.setString(8, data.get("html"));
			    	  System.currentTimeMillis();
			    	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			          
			          
			    	  pstmt.setString(9, timestamp.toString());
			    	  
		         int row =  pstmt.executeUpdate();
		         //System.out.println(row); //1
					 
				 }
					  
				 conn.close();
			    }
			  
	            
	            
	            
	         catch (SQLException e) {
	            System.out.println(e.getMessage());
	            
	        }
	    }
}
