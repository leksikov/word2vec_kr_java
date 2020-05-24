package crawler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import db_manager.InsertNewsQuery;
import textProcessor.textProcessor;

public class naverNewsCrawler {

	public String db_path = "C:/sqlite/db/naver_news.db";
	
	Document doc;
	
	Elements content;
	String url;
	String article_url;
	Element article_title;
	
	HashMap<String, String> news_data = new HashMap<String, String>();
	
	Document doc2;
	Element content2;
	String article_text;
	
	
	public void naverCrawler(LocalDate date, int page) throws IOException {
		int date2 = Integer.parseInt(date.toString().replace("-", ""));
		url = "https://news.naver.com/main/list.nhn?mode=LSD&mid=sec&listType=title&sid1=001&date="+date2+"&page="+page;
	doc	 = Jsoup.connect(url)
			  .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36")
			  .timeout(3000)
			  .get();
	
	
	String title = doc.title();
	
	textProcessor txtProcessor = new textProcessor();
	title = txtProcessor.splitTitle(title);
	System.out.println(title);
	
	content = doc.getElementsByClass("list_body newsflash_body");
	
	 Elements links = content.get(0).getElementsByTag("a");
	 
	 for (Element link : links) {
		   article_url = link.attr("href");
		  String article_title = link.text();
		  
		 article_text = getArticleText(article_url);
		 
		 
		 textProcessor textProcessor = new textProcessor();
		 
		 news_data.put("date", date.toString());
		 news_data.put("article_title", article_title);
		 news_data.put("article_text", article_text);
		 news_data.put("article_text", textProcessor.cleanText(article_text).replaceAll("[\\[\\](){}]","").replaceAll("[a-zA-Z0-9]", ""));
		 news_data.put("url", url);
		 news_data.put("article_url", article_url);
		 
		 
		 
		 
		 new InsertNewsQuery().insert(db_path, news_data);
		}
	 
	 
	System.out.println(links.get(2).text());
	
	
	
	}


	private String getArticleText(String url2) throws IOException {
		doc2	 = Jsoup.connect(url2)
				  .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36")
				  .timeout(3000)
				  .get();
		
		String title = doc.title();
		
		textProcessor txtProcessor = new textProcessor();
		title = txtProcessor.splitTitle(title);
		System.out.println(title);
		
		//
		content2 = doc.getElementById("articleBodyContents");
		article_text = content2.text();
		return article_text;
		
		
	}
	
	
}
