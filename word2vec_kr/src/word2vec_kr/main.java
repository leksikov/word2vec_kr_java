/**
 * 
 */
package word2vec_kr;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import crawler.Crawler;
/**
 * @author lexik
 *
 */
public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello word2vec");
		Crawler cr = new Crawler();
		System.out.println(cr.getHeadline());
		
		
	}


}
