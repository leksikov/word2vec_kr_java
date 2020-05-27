package httpServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import org.sqlite.util.StringUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;

public class MyHandler  implements HttpHandler {
	
	 
        @Override
        public void handle(HttpExchange t) throws IOException {
        	String requestParamValue=null; 
        	System.out.println(t.toString());
    	    if("GET".equals(t.getRequestMethod())) { 
    	
    	       requestParamValue = handleGetRequest(t);
    	        	    
    	    }
    	    else if("POST".equals(t)) { 
    	
    	       requestParamValue = handleGetRequest(t);        
    	
    	      }  
    	    System.out.println(t.toString() + " " + requestParamValue.toString());
    	    handleResponse(t,requestParamValue);
      
        	
        	/*
        	String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            */
        }
        
        private String handleGetRequest(HttpExchange httpExchange) {
        	
        	            return httpExchange.
        	
        	                    getRequestURI()
        	
        	                    .toString()
        	
        	                    .split("\\?")[1]
        	
        	                    .split("=")[1];
        	
        	   }
        	   private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {
        	
        		   File file = new File("dashboard/index.html");
       			BufferedReader br = new BufferedReader(new FileReader(file)); 
       			
       			
               	 
               	  
               	  String html = ""; 
               	  String st;
               	  while ((st = br.readLine()) != null)
               		  html = html + st;
               	  
               	    System.out.println(html); 
               	  
        		   
        	            OutputStream outputStream = httpExchange.getResponseBody();
        	
        	            StringBuilder htmlBuilder = new StringBuilder();
        	
        	            
        	/*
        	            htmlBuilder.append("<html>").
        	
        	                    append("<body>").
        	
        	                    append("<h1>").
        	
        	                    append("Hello <script>\r\n" + 
        	                    		"function myFunction() {\r\n" + 
        	                    		"  alert(\"Hello! I am an alert box!\");\r\n" + 
        	                    		"}\r\n" + "myFunction(); " +
        	                    		"</script>")
        	
        	                    .append(requestParamValue)
        	
        	                    .append("</h1>")
        	
        	                   .append("</body>")
        	
        	                    .append("</html>");
        	
        	*/
        	            // encode HTML content 
        	
        	            String htmlResponse = html; //htmlBuilder.toString();
        	
        	     
        	
        	            // this line is a must
        	
        	            httpExchange.sendResponseHeaders(200, htmlResponse.length());
        	
        	
        	            outputStream.write(htmlResponse.getBytes());
        	
        	            outputStream.flush();
        	
        	            outputStream.close();
        	
        	        }
    

	
	

}
