package crawler;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import crawler.naverNewsCrawler;

public class main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		for (int days_ago=0; days_ago <= 365*4; days_ago++) {
			LocalDate date = LocalDate.now().minusDays(days_ago);
			
			for (int page = 1; page <= 50; page++) {
				try {
					new naverNewsCrawler().naverCrawler(date, page);
						
				}
				catch (Exception e) {
		            System.out.println(e.getMessage() +" " + days_ago +" " + page);
		            Thread.sleep(10000);
		        }
				Thread.sleep(1000);
				
				
			}
			

		}
		

	}

}
