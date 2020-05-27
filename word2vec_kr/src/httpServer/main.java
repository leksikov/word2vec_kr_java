package httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;
import httpServer.MyHandler;
public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("here");
		HttpServer server = HttpServer.create(new InetSocketAddress(8002), 0);
        server.createContext("/dashboard/", new  MyHandler());
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor); // creates a default executor
        
        server.start();
        System.out.println("Started");
	}

}
