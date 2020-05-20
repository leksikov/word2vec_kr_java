package crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;


public class Crawler {
	
	
	Document doc;
	Elements newsHeadlines;
	ArrayList<String> articles;
	
	public Crawler() throws IOException {
		doc	 = Jsoup.connect("https://en.wikipedia.org/").get();
		articles = new ArrayList<String>();
			newsHeadlines = doc.select("#mp-itn b a");
	}
	
	public ArrayList<String> getHeadline() throws IOException {
	
		for (Element headline : newsHeadlines) {
			articles.add(headline.attr("title"));
			System.out.printf("%s\n\t%s", headline.attr("title"), headline.absUrl("href")); 
		}
		
		return articles;
		
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
