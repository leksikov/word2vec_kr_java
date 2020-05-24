package crawler;
import java.io.IOException;
import java.time.LocalDate;

import crawler.naverNewsCrawler;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		
		for (int days_ago=0; days_ago <= 365*4; days_ago++) {
			LocalDate date = LocalDate.now().minusDays(days_ago);
			
			for (int page = 1; page <= 100; page++) {
				new naverNewsCrawler().naverCrawler(date, page);
				
			}
			

		}
		

	}

}
