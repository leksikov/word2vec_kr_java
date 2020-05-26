package textProcessor;

import java.util.ArrayList;
import java.util.HashMap;

import db_manager.DBQuery;
import db_manager.insertTokenizedArticles;

public class insertTokenizedIntoDB {
	
	public void insertTokenizedIntoDB() {
		
	String sql = "SELECT DISTINCT id, article_text FROM naver_news;";
	
	HashMap<Integer, String> article_text_lst = DBQuery.selectQuery(sql);
	
	
	textProcessor processor = new textProcessor();
	
	
	for (Integer key : article_text_lst.keySet()) { // iterate over articles
	    
		Integer id = key;
	    String text = article_text_lst.get(key);
	    
		text = processor.remove_extra_spaces(text.replaceAll("-,", ""));
		text = processor.cleanText(text);
		text = processor.remove_extra_spaces(text);
		
		Tokenizer tkn = new Tokenizer();
		
		ArrayList<String> lstTokens = tkn.getTokens(text);
			
		insertTokenizedArticles.insertTokenizedArticles(id, lstTokens);
		
		
		
		
	}
	}
}
