package crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.HashMap;

public class movieDescriptionCrawler {
	
	
	Document doc;
	String movie_title;
	Elements movie_text;
	Element extra_movie_text;
	
	ArrayList<String> articles;
	String title ;
	Elements country;
	Elements genre;

	Elements year;
	
	HashMap<String, String> movie_data ;
	public movieDescriptionCrawler(int id) throws IOException {
		articles = new ArrayList<String>();
		
		doc	 = Jsoup.connect("https://movie.naver.com/movie/bi/mi/basic.nhn").data("code", Integer.toString(id))  //193804, 188790
				  .userAgent("Mozilla")
				  .timeout(3000)
				  .get();
		
	  title	= doc.title();
	
		
	  //Elements paragraphs = doc.select("p");
	  try {
		  
	  	movie_title = doc.getElementsByClass("h_movie2").attr("title");
		movie_text = doc.getElementsByClass("con_tx"); //
		extra_movie_text = doc.getElementById("makingnotePhase");
		country = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?nation=]");
		genre = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?genre=]");

		
		
		year = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?open=]");
		
	  }
	  catch (Exception e) {
		  System.out.println("error: "  + e);
	  }
	
	if (movie_text.hasText() !=  false) {
		
		
		System.out.println("title " + title);
		System.out.println("movie_title: " + movie_title);
		System.out.println("movie description: " + movie_text.get(0).text());
		
		if (extra_movie_text != null   ) {
			System.out.println("extra movie text: " + extra_movie_text.text());
			
		}
		else {
			extra_movie_text = null;
			
		}
		
		System.out.println("country: " + country.get(0).text());
		System.out.println("Genre: " + genre.get(0).text());
		System.out.println("year: " + year.text());
		System.out.println("============");
		movie_data = new HashMap<String, String>();
		movie_data.put("id", Integer.toString(id));
		movie_data.put("title", title);
		movie_data.put("movie_title", movie_title);
		movie_data.put("movie_text", movie_text.text());
		if ( extra_movie_text != null  ) {
			movie_data.put("extra_movie_text", extra_movie_text.text());
		}
		else {
			movie_data.put("extra_movie_text", null);
		}
		
		movie_data.put("country", country.text());
		movie_data.put("genre", genre.text());
		movie_data.put("year", year.text());
		
		
		
		
	}
	
	

		
		
		
	}
	

	public HashMap<String, String> getData() throws IOException {
		
		
		return movie_data;
		
		
	}
	
	
	public String getHeadline() throws IOException {
		System.out.println(title);
		
		for (Element headline : movie_text) {
			
			System.out.printf("%s\n\t%s",  headline.attr("title"), headline.text()); 
			
		}
		
		return movie_text.text();
		
	}
	
	/*
	
	@Override
	public String toString() {
		return "Crawler [doc=" + doc + ", newsHeadlines=" + newsHeadlines + ", headline=" + headline + "]";
	}
	
	public void setHeadline(Element headline) {
		this.headline = headline;
	}
	*/
	
}
