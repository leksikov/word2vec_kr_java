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
	Element html;
	 HashMap<String, String> movie_data = new HashMap<String, String>();
	
	public movieDescriptionCrawler(int idx) throws IOException {
		articles = new ArrayList<String>();
		
		doc	 = Jsoup.connect("https://movie.naver.com/movie/bi/mi/basic.nhn").data("code", Integer.toString(idx))  //193804, 188790
				  .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36")
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
		html = doc.body();
	  }
	  catch (Exception e) {
		  System.out.println("error: "  + e);
	  }
	
	if (movie_text.hasText() !=  false) {
		
		/*
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
		*/
		// Hash Map
		
		
		this.movie_data.put("idx", Integer.toString(idx));
		this.movie_data.put("title", cleanText(title));
		this.movie_data.put("movie_title", cleanText(movie_title));
		this.movie_data.put("movie_text", cleanText(movie_text.first().text().replaceAll("[a-zA-Z0-9]", "")));
		
		if ( extra_movie_text != null  ) {
			this.movie_data.put("extra_movie_text", cleanText(extra_movie_text.text().replaceAll("[a-zA-Z0-9]", ""))); //.replaceAll("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", "")
		}
		else {
			this.movie_data.put("extra_movie_text", null);
		}
		
		this.movie_data.put("country", cleanText(country.first().text()) );
		this.movie_data.put("genre", cleanText(genre.first().text()));
		this.movie_data.put("html", html.html());
		
		
		
		
		
		
	}
	
	

		
		
		
	}
	

	public HashMap<String, String> getData() throws IOException {
	
		
		return this.movie_data;
		
		
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
