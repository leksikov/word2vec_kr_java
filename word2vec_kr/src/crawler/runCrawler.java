package crawler;

import java.io.IOException;
import db_manager.Connect;

public class runCrawler {
	private int id, end_id;
	private String url;
	public String db_path = "C:/sqlite/db/movie_database.db";
	
	public runCrawler() throws IOException {
		
		end_id = 100005; //500000;
		
		
		for (id=100000; id <= end_id; id++) {
			movieDescriptionCrawler cr = new movieDescriptionCrawler(id);
			Connect.connect(db_path);
			
			/* arrList = cr.getData();
			
			
			insertToDB(arrList);
			
			*/
			
		}
		
	}
}
