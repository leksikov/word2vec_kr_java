package crawler;

import java.io.IOException;

import db_manager.DBQuery;
import db_manager.InsertQuery;

import java.sql.Connection;
import java.util.HashMap;


public class runCrawler {
	private int id, end_id;
	private String url;
	public String db_path = "C:/sqlite/db/movie_description.db";
	
	public runCrawler() throws IOException {
		
		end_id = 100005; //500000;
		
		
		for (id=100000; id <= end_id; id++) {
			movieDescriptionCrawler cr = new movieDescriptionCrawler(id);
			
			HashMap<String, String> data = cr.getData();
			new InsertQuery().insert(db_path, data);
			
			
			
			/*
			 * Get querry daa
			String sql = "SELECT * FROM movie_description;";
			
			DBQuery q = new DBQuery(db_path, sql);
			String qres = q.qres;
			System.out.println("here " + qres );
			*/

		}
		
	}
}
