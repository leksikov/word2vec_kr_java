package textProcessor;

import java.util.ArrayList;
import java.util.List;

import org.openkoreantext.processor.KoreanPosJava;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;

import org.openkoreantext.processor.tokenizer.KoreanTokenizer.KoreanToken;

import scala.collection.Seq;


public class Tokenizer {
	ArrayList<String> tokenLst = new ArrayList<String>();
	public void tokenizer() {
		
		
	}
	
	public ArrayList<String> getTokens(String text) {
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
		//System.out.println(normalized);
		
		// Tokenize
		Seq<KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
	   
		
		List<KoreanTokenJava> tokenized_lst = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);
		
		
		
	    for (int i=0; i<tokenized_lst.size(); i++) 
	    { 
	     KoreanTokenJava var = tokenized_lst.get(i);
	     KoreanPosJava type = var.getPos();
	     boolean stmt = (type.toString() =="Punctuation") | (type.toString() =="Modifier") | (type.toString() =="Josa")  | (type.toString() =="Foreign");
	     if ( !stmt) {
	     	tokenLst.add(var.getText());
	     	
	     }
	     
	    }
	    
	    return tokenLst;
	    
		
	}

}