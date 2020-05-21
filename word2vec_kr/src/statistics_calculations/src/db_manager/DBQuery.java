package statistics_calculations.src.db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
	public Connection conn = null;
	public String qres;
	public String db_path = "C:/sqlite/db/movie_description_small.db";
	
	public DBQuery(String db_path, String sql) {
			
			conn = connect(db_path);
			
			selectAll(conn, sql);
			
        
	}
	
	public  Connection connect(String db_path) {
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
    public String selectAll(Connection conn,String sql){
    	System.out.println("query");
        
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	
            	qres = rs.getInt("idx") +  "," + 
                        rs.getString("title") + "," +
                        rs.getString("movie_title") + "," +
                        rs.getString("movie_text") + "," +
                        rs.getString("extra_movie_text") + "," +
                        rs.getString("genre");
            	
                System.out.println(qres);
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
    return qres;
    }
    
    public void insert(Connection conn, String name, double capacity) {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
