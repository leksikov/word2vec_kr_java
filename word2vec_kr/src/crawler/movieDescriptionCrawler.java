package crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;


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
	
	
	public movieDescriptionCrawler(int id) throws IOException {
		articles = new ArrayList<String>();
		
		doc	 = Jsoup.connect("https://movie.naver.com/movie/bi/mi/basic.nhn").data("code", Integer.toString(id))  //193804, 188790
				  .userAgent("Mozilla")
				  .timeout(3000)
				  .get();
		
	  title	= doc.title();
	  
		
	  //Elements paragraphs = doc.select("p");
	  
	  	movie_title = doc.getElementsByClass("h_movie2").attr("title");
		movie_text = doc.getElementsByClass("con_tx"); //
		extra_movie_text = doc.getElementById("makingnotePhase");
		country = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?nation=]");
		genre = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?genre=]");

		
		
		year = doc.select("a[href*=/movie/sdb/browsing/bmovie.nhn?open=]");
		
		
		/*
				#content > div.article > div.mv_info_area > div.mv_info > dl > dd:nth-child(2) > p > span:nth-child(2) > a > font > font
				<font style="vertical-align: inherit;">Spain</font>
				
				href="/movie/sdb/browsing/bmovie.nhn?nation="
						+ ""
						+ ""
			*/
	if (movie_text.hasText() !=  false) {
		
		
		System.out.println("title " + title);
		System.out.println("movie_title: " + movie_title);
		System.out.println("movie description: " + movie_text.text());
		//System.out.println("extra movie text: " + extra_movie_text);
		System.out.println("country: " + country.text());
		System.out.println("Genre: " + genre.text());
		System.out.println("year: " + year.text());
		
		/*
		for (Element headline : newsHeadlines) {
			
			System.out.printf("%s\n\t%s",  headline.attr("title"), headline.text()); 
			
		}
		*/
		
	}
	
	

		
		
		
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
