package textProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import db_manager.DBQuery;
import textProcessor.tokenizer;
import java.io.FileWriter;
import java.io.IOException;
import textProcessor.*;
import db_manager.insertTokenizedArticles;


public class main {
	static int sent_count = 0;
	static int tokens_count = 0;
	static HashSet<String> unique_tokens = new HashSet<String>(); 
	static String db_path = "C:\\sqlite\\db\\naver_news.db";
	
	public static void main(String[] args) throws IOException {
		String sql = "SELECT DISTINCT id, article_text FROM naver_news LIMIT 1000;";
				
		HashMap<Integer, String> article_text_lst = DBQuery.selectQuery(sql);
		
		System.out.println(article_text_lst.size());
		textProcessor processor = new textProcessor();
		
		System.out.println(article_text_lst);
		for (Integer key : article_text_lst.keySet()) {
		    System.out.println(key);
			Integer id = key;
		    String text = article_text_lst.get(key);
		    
			text = processor.remove_extra_spaces(text.replaceAll("-,", ""));
			
			String[] sentences = text.split("[\\.\\?!]");
			sent_count = sent_count + sentences.length;
			
			tokenizer tkn = new tokenizer();
			ArrayList<String> lstTokens = tkn.getTokens(text);
			
			tokens_count = tokens_count + lstTokens.size();
			
			// insert into db table tokenized news articles
			System.out.println(Integer.toString(id) + lstTokens);
			insertTokenizedArticles.insertTokenizedArticles(id, lstTokens);
			
			
			
			for (int i=0; i < lstTokens.size(); i++) {
				unique_tokens.add(lstTokens.get(i));		
				
			}
			
			int unique_tokens_cnt = unique_tokens.size();
			
			
		}

		/*
		
		System.out.println(lstTokens);
		
		// output list of unique tokens
		FileWriter writer = new FileWriter("out/unique_tokens_output.txt"); 
		for(String str: unique_tokens) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
		
		/// output for sentence and word statistics
		FileWriter writer2 = new FileWriter("out/sentenceWordCount_output.txt"); 
		String str2 = "tokens_count:" + Integer.toString(tokens_count) + System.lineSeparator() + "sent_count:" + Integer.toString(sent_count) + System.lineSeparator() + "unique_tokens_cnt:" +  Integer.toString(unique_tokens_cnt);
		
		writer2.write(str2 + System.lineSeparator());		
		writer2.close();
		
		
		*/
		
	}



}
