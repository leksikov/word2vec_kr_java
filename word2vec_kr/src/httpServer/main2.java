package httpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;
import java.util.logging.Logger;


public class main2 {
	public static HttpServer my_server = null;
	
	public main2(String[] args) throws IOException {
		System.out.println("Server started on port 8001 ");
		/*
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.createContext("/get", new getData());
        server.setExecutor(null); // creates a default executor
        server.start();
		*/
		
		System.out.println("Server started on port 8001 ");
	}
	/*
	static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
	
	static class getData implements HttpHandler {
		// https://www.rgagnon.com/javadetails/java-have-a-simple-http-server.html
		@Override
        public void handle(HttpExchange t) throws IOException {
			File file = new File("out/result.json");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			
			
        	 
        	  
        	  String st; 
        	  while ((st = br.readLine()) != null) 
        	    System.out.println(st); 
        	  OutputStream os = t.getResponseBody();
        	  os.write(st.getBytes());
              os.close();
        	  }
			
        }
	*/
    

}
