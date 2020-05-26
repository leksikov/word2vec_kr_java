package db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class insertTokenizedArticles {
	public static String db_path = "C:\\sqlite\\db\\naver_news.db";
	
	 private static Connection connect(String db_path) {
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
	 
	 
	public static void insertTokenizedArticles(Integer id, ArrayList<String> lstTokens) {
		String str = null;
		
		for(String token : lstTokens) {
			if (str == null) {
				str  = token;
			}
			else {
				str = str + "," +  token;
			}
			
			
		}
		
		//
		String sql = "INSERT INTO naver_news_tokenized( id, article_tokens) VALUES(?,?)";
		
        
		System.out.println("Insertion");
        
		try (Connection conn = insertTokenizedArticles.connect(db_path);
        		
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		
			 if ( str.length()!=0 & str != null) {
				  		   
		    	  pstmt.setInt(1, id);
		    	  pstmt.setString(2, str);
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
