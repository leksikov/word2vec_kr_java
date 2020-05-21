package statistics_calculations.src.db_manager;

import db_manager.DBQuery;

public class main {
	public static String db_path = "C:/sqlite/db/movie_description_small.db";
	public static void main(String[] args) {
		String sql = "SELECT * FROM movie_description_small LIMIT 100;";
		
		DBQuery q = new DBQuery(db_path, sql);
		String qres = q.qres;
		System.out.println("here " + qres );
	}

}
