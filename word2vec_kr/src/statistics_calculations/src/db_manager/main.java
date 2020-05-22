package statistics_calculations.src.db_manager;

import java.sql.Connection;
import java.util.ArrayList;

import statistics_calculations.src.db_manager.DBQuery;

public class main {
	public static String db_path = "C:/sqlite/db/movie_description.db";
	static int word_counter = 0;
	
	public static void main(String[] args) {
		
		String sql = "SELECT movie_text, extra_movie_text FROM movie_description;";
		
		ArrayList<String> movie_text_lst = new DBQuery().getMovieText(sql);
		ArrayList<String> extra_movie_text_lst = new DBQuery().getExtraMovieText(sql);
		long startTime = System.currentTimeMillis();
		for (int i=1; i < movie_text_lst.size(); i++) {
			if (extra_movie_text_lst.get(i) != null) {
			int movie_text_size = movie_text_lst.get(i).split("\\s").length;
			
			 word_counter =  word_counter + movie_text_size;
			}
		}
		
		
		for (int i=1; i < extra_movie_text_lst.size(); i++) {
				if (extra_movie_text_lst.get(i) != null) {
					
			
			 int extra_movie_text_size = extra_movie_text_lst.get(i).split("\\s").length;
			 word_counter =  word_counter + extra_movie_text_size;
				}
		}
		 
		System.out.println(word_counter);
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;

		System.out.println("Execution time in milliseconds: " + timeElapsed);
		//.connect(db_path);
				//.getMovieText(conn, sql)
	
		
	}

}
