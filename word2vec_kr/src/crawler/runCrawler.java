package crawler;

import java.io.IOException;

public class runCrawler {
	private int id, end_id;
	private String url;
	
	public runCrawler() throws IOException {
		
		end_id = 100005; //500000;
		
		
		for (id=100000; id <= end_id; id++) {
			movieDescriptionCrawler cr = new movieDescriptionCrawler(id);
			/* arrList = cr.getData();
			
			
			insertToDB(arrList);
			
			*/
			
		}
		
	}
}
