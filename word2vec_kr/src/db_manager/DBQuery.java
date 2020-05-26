package db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DBQuery {
	public static Connection conn = null;
	public static String qres;
	static String db_path = "C:\\sqlite\\db\\naver_news.db";
	
	public DBQuery() {
			
	
        
	}
	
	public static  Connection connect(String db_path) {
        // SQLite connection string
        String url = db_path;
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:"+url);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    /**
     * select all rows in the warehouses table
     * @return 
     */
    public static  HashMap<Integer, String> selectQuery(String sql){
    	//System.out.println("query");
        
    	HashMap<Integer,String> article_text_lst = new HashMap<Integer, String>();
    	
        try (
        		Connection conn = DBQuery.connect(db_path);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	Integer id = rs.getInt("id");
            	qres =  rs.getString("article_text");
            	//System.out.println("here: s"+id);
            	article_text_lst.put(id, qres);
            	
              
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    return article_text_lst;
    }
    
    public static  HashMap<Integer, LinkedList<String>> selectQuery2(String sql){
    	//System.out.println("query");
    	HashMap<Integer, LinkedList<String>> article_text_lst = new HashMap<Integer, LinkedList<String>>();
    	 
        try (
        		Connection conn = DBQuery.connect(db_path);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	Integer id = rs.getInt("id");
            	qres =  rs.getString("article_text");
            	String tokens = rs.getString("article_tokens");
            	//System.out.println("here: s"+id);
            	LinkedList<String> tuple = new LinkedList<String>();
            	tuple.add(qres);
            	tuple.add(tokens);
            	article_text_lst.put(id, tuple);
            	
            	
              
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    return article_text_lst;
    }
    
    


}
