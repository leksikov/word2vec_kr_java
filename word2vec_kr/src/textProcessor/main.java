package textProcessor;
import java.util.ArrayList;
import java.util.HashSet;

import textProcessor.tokenizer;
import java.io.FileWriter;
import java.io.IOException;

public class main {
	static int sent_count = 0;
	static int tokens_count = 0;
	static HashSet<String> unique_tokens = new HashSet<String>(); 
	
	public static void main(String[] args) throws IOException {
		String text= " !!부산 ## .... 교통 ; 사망사)고를 줄이기 위해 경찰이 오늘부터 도심 도로의 제한 속도를 시속 ㎞로 낮춰 단속에 들어간 가운데 속도위반 차량이 잇따라 적발됐습니다. 부산경찰청이 무인 단속 장비를 분석한 결과 오늘 새벽 시부터 오후 시까지 부산 도심을 운행한 차량 대가 속도를 위반한 것으로 나타났습니다. 과속한 차종의 경우 승용차가 대로 가장 많았고, 대부분 시속 ㎞에서 ㎞로 달려 적발됐습니다. 경찰은 고정식 단속 장비 대와 함께 이동식 장비 대를 활용해 수시로 제한속도 위반 차량을 단속하기로 했습니다. 황현규 기자 @.. ▶ ‘코로나 팩트체크’ 제대로 알아야 이긴다 ▶ 우리 동네 무슨 일이? 지역뉴스 ▶ 더 빠르고 정확한 소식을 원하시면 뉴스 구독!";
		
		String[] sentences = text.split("[\\.\\?!]");
		
		 sent_count = sent_count + sentences.length;
		System.out.println("sent cnt:" + sent_count);
		tokenizer tkn = new tokenizer();
		ArrayList<String> lstTokens = tkn.getTokens(text);
		
		tokens_count = tokens_count + lstTokens.size();
		
		// insert into db table tokenized news articles
		//insertTokenizedArticles(id, tokens);
		
		for (int i=0; i < lstTokens.size(); i++) {
			unique_tokens.add(lstTokens.get(i));		
			
		}
		
		int unique_tokens_cnt = unique_tokens.size();
		
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
		
		
		
		
	}

}
