package text_statistics;


import java.util.List;

import org.openkoreantext.processor.KoreanPosJava;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;

import org.openkoreantext.processor.tokenizer.KoreanTokenizer.KoreanToken;

import scala.collection.Seq;



public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text= " !!부산 ## .... 교통 ; 사망사)고를 줄이기 위해 경찰이 오늘부터 도심 도로의 제한 속도를 시속 ㎞로 낮춰 단속에 들어간 가운데 속도위반 차량이 잇따라 적발됐습니다. 부산경찰청이 무인 단속 장비를 분석한 결과 오늘 새벽 시부터 오후 시까지 부산 도심을 운행한 차량 대가 속도를 위반한 것으로 나타났습니다. 과속한 차종의 경우 승용차가 대로 가장 많았고, 대부분 시속 ㎞에서 ㎞로 달려 적발됐습니다. 경찰은 고정식 단속 장비 대와 함께 이동식 장비 대를 활용해 수시로 제한속도 위반 차량을 단속하기로 했습니다. 황현규 기자 @.. ▶ ‘코로나 팩트체크’ 제대로 알아야 이긴다 ▶ 우리 동네 무슨 일이? 지역뉴스 ▶ 더 빠르고 정확한 소식을 원하시면 뉴스 구독!";
		// Normalize
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
		System.out.println(normalized);
		
		// Tokenize
		Seq<KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
	   
		
		List<KoreanTokenJava> tokenized_lst = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);
	    
	    for (int i=0; i<tokenized_lst.size(); i++) 
	    { 
	     KoreanTokenJava var = tokenized_lst.get(i);
	     KoreanPosJava type = var.getPos();
	     boolean stmt = (type.toString() =="Punctuation") | (type.toString() =="Modifier") | (type.toString() =="Josa")  | (type.toString() =="Foreign");
	     if ( !stmt) {
	     System.out.println(var.getText() + var.getPos());
	     }
	    }
	    
	    
	    // get rows from database
	    // count total number of sentences and words
	    // insert tokenized text into db
	    // create unique word set
	    // create unique noun set
	    // enumerated word mapping. Id to word and word to id
	    // for each word count all words occurence and number of documents it is occured
	    // calculate idf frequency
	    
	 
	    
	}

}
