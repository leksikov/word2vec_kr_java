package textProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.openkoreantext.processor.OpenKoreanTextProcessorJava;

import com.google.gson.Gson;

import db_manager.DBQuery;
import db_manager.insertTokenizedArticles;

import java.io.FileWriter;
import java.io.IOException;
import textProcessor.insertTokenizedIntoDB;

public class main {
	static int sent_count = 0;
	static int tokens_count = 0;
	static HashSet<String> unique_tokens = new HashSet<String>(); 
	static String db_path = "C:\\sqlite\\db\\naver_news.db";
	static int avg_word_size = 0;
	
	
	public static ArrayList<Integer> token_size_distribution = new ArrayList<Integer>();
	public static ArrayList<Integer> sentence_size_distribution = new ArrayList<Integer>();
	public HashMap<String, Integer> count_of_word_types = new HashMap<String, Integer>();
	
	
	public static  void main(String[] args) throws IOException {
		// new insertTokenizedIntoDB().insertTokenizedIntoDB(); To insert tokenized sentences into db
		
	
		
		String sql = "SELECT DISTINCT A.id, A.article_text, B.article_tokens FROM naver_news as A LEFT JOIN naver_news_tokenized as B ON A.id = B.id LIMIT 1000;";
				
		HashMap<Integer, LinkedList<String>> article_text_lst = DBQuery.selectQuery2(sql);
		
		System.out.println(article_text_lst.size());
		textProcessor processor = new textProcessor();
		
	
		for (Integer key : article_text_lst.keySet()) { // iterate over articles
		    Integer id = key;
		
		    LinkedList<String> tuple = article_text_lst.get(key);
		    String text = null;
		    String[] lstTokens = null;
		    try {

			    text = tuple.get(0);
			    
		    	lstTokens = tuple.get(1).split(",");
				
		    }catch(Exception e) {
		    	
		    	
		    	continue;
		    }
		    
			
			String[] sentences = text.split("[\\.\\?!]");
			
			sent_count = sent_count + sentences.length;
			
			for (String sentence : sentences) {
				sentence_size_distribution.add(sentence.length());
				
			}
			
			
			tokens_count = tokens_count + lstTokens.length;
			
			
			
			for (String word : lstTokens) {
				 avg_word_size = avg_word_size +word.length();
				 token_size_distribution.add(word.length());
				 
			}
			
			
			
			for (int i=0; i < lstTokens.length; i++) {
				unique_tokens.add(lstTokens[i]);		
				
			}
			
			int unique_tokens_cnt = unique_tokens.size();
			
			
		}
		
		avg_word_size = avg_word_size/tokens_count;
		System.out.println(sent_count + " " + tokens_count + " word size" + avg_word_size);
		
		long startTime = System.currentTimeMillis();
		long stopTime = System.currentTimeMillis();
	      long elapsedTime = stopTime - startTime;
	      System.out.println("exec time ms: " + elapsedTime);
	
	      
	      HashMap<String, String> result = new  HashMap<String, String>();
	      result.put("sent_count", Integer.toString(sent_count));
	      result.put("token_count", Integer.toString(tokens_count));
	      result.put("avg_word_size", Integer.toString(avg_word_size));
	      
	      result.put("token_size_distribution", token_size_distribution.toString());
	      result.put("sentence_size_distribution", sentence_size_distribution.toString());
	      
	      Gson gson = new Gson(); 
	      String json = gson.toJson(result); 
	      
	      
	      
	    FileWriter writer = new FileWriter("out/result.json"); 
		writer.write(json + System.lineSeparator());
		writer.close();
		System.out.println("done \\a");
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
