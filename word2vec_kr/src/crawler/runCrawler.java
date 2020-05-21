package crawler;

import java.io.IOException;

import db_manager.DBQuery;

import java.sql.Connection;


public class runCrawler {
	private int id, end_id;
	private String url;
	public String db_path = "C:/sqlite/db/movie_description.db";
	
	public runCrawler() throws IOException {
		
		end_id = 100005; //500000;
		
		
		for (id=100000; id <= end_id; id++) {
			movieDescriptionCrawler cr = new movieDescriptionCrawler(id);
			String sql = "SELECT * FROM movie_description;";
			DBQuery q = new DBQuery(db_path, sql);
			String qres = q.qres;
			System.out.println("here "+qres );
			
				
			
			
			
			/* arrList = cr.getData();
			
			
			insertToDB(arrList);
			
			*/
			
		}
		
	}
}
