package crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import textProcessor.textProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import textProcessor.textProcessor;

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
	  title = splitTitle(title);
		
	  //Elements paragraphs = doc.select("p");
	  try {
		  
	  	movie_title = doc.getElementsByClass("h_movie2").attr("title");
		movie_text = doc.getElementsByClass("con_tx"); //
		extra_movie_text = doc.getElementById("makingnotePhase");
		country = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?nation=]");
		genre = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?genre=]");
		
		
	  }
	  catch (Exception e) {
		  System.out.println("error: "  + e);
	  }
	
	if (movie_text.hasText() !=  false) {
		
		
		System.out.println("title " + title);
		System.out.println("movie_title: " + cleanText(movie_title));
		System.out.println("movie description: " + cleanText(movie_text.first().text()));
		
				if (extra_movie_text != null   ) {
					System.out.println("extra movie text: " + cleanText(extra_movie_text.text()));
					
				}
				else {
					extra_movie_text = null;
					
				}
		
		System.out.println("country: " +  cleanText(country.first().text()));
		System.out.println("Genre: " +  cleanText(genre.first().text()));
		
		System.out.println("============");
		
		// Hash Map
		
		movie_data = new HashMap<String, String>();
		movie_data.put("id", Integer.toString(id));
		movie_data.put("title", cleanText(title));
		movie_data.put("movie_title", cleanText(movie_title));
		movie_data.put("movie_text", cleanText(movie_text.first().text().replaceAll("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", "")));
		if ( extra_movie_text != null  ) {
			movie_data.put("extra_movie_text", cleanText(extra_movie_text.text().replaceAll("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", "")));
		}
		else {
			movie_data.put("extra_movie_text", null);
		}
		
		movie_data.put("country", cleanText(country.first().text()) );
		movie_data.put("genre", cleanText(genre.first().text()));
		
		
		
		
		
		
		
	}
	
	

		
		
		
	}
	

	public HashMap<String, String> getData() throws IOException {
		
		
		return movie_data;
		
		
	}
	
	
	public String cleanText(String string) {
		string =string.trim().replaceAll("[♪/‘’'!?;:^*()<>+_@%#]","");
		string = new textProcessor().remove_extra_spaces(string);
		
		return string;
		
		
	}
	
	public String splitTitle(String string) {
		String[] string_arr = cleanText(string).split(":",2 );
		
		
		return string_arr[0];
		
	}

	
}
