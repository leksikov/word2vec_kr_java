package httpServer;

import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;


public class main {
	public static HttpServer my_server = null;
	
	public static void main(String[] args) throws IOException {
		my_server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);

	}

}
