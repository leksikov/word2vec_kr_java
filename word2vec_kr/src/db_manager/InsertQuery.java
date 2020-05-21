package db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		 	
	        String sql = "INSERT INTO movie_description(idx,title, movie_title, movie_text, extra_movie_text, genre, country) VALUES(?,?, ?, ?, ?,?, ?)";
	        
	        
	        
			try (Connection conn = this.connect(db_path);
	        		
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
				System.out.println("Insertion");
				 int i = 1;
			  for (String name : data.keySet()) {
				  
				  if (name=="idx") {
					  
					  int value = Integer.parseInt(data.get(name));
			    	  pstmt.setInt(i, value);
				      i++; 
				  }
				  else {
					  //System.out.println("name: " + name + " value: " + data.get(name));
					     
			    	  String value = data.get(name);
			    	  pstmt.setString(i, value);
				      i++; 
				  }
				  
			     
			      }
			     
			      
			      
		            
		         int row =  pstmt.executeUpdate();
		         System.out.println(row); //1
			    }
			  
	            
	            
	            
	         catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
}
