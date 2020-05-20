package textProcessor;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class textProcessor {

	public textProcessor() {
		
	}
	
	public  String remove_extra_spaces(String string) {
		 string = string.trim().replaceAll(" +", " ").replaceAll("\\s+", " ");
		return string;
	}
	
	
public  String remove_word_duplicates(String string) {
		
	String[] strWords = string.split("\\s+");
    
    //convert String array to LinkedHashSet to remove duplicates
    LinkedHashSet<String> lhSetWords = new LinkedHashSet<String>( Arrays.asList(strWords) );
    
    //join the words again by space
    StringBuilder sbTemp = new StringBuilder();
    int index = 0;
    
    for(String s : lhSetWords){
        
        if(index > 0)
            sbTemp.append(" ");
    
        sbTemp.append(s);
        index++;
    }
    
    string = sbTemp.toString();
    
   
    System.out.println(string);
    return string;
	}


}






