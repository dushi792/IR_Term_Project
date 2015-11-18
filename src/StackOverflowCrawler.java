import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class StackOverflowCrawler {
    
    public static String getSinglePage(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            
            connection.connect();
            // Get all the response headers
            Map<String, List<String>> map = connection.getHeaderFields();

            // traverse all the headers
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }

            // uncompress the InputStream 
            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream()), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("send GET request exception!" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    
    
    public static void StringToJSON(String str) throws Exception{
    	try{   		
	    	JSONParser parser = new JSONParser();
	    	String output = new GsonBuilder().setPrettyPrinting().create().toJson(parser.parse(str));
	    	FileWriter file = new FileWriter("topic.json", true);
			file.write(output);
			file.flush();
    	}
    	catch(IOException e){
    		System.out.println("-Error:" + e.getMessage());
    		e.printStackTrace();
    	}
    	catch (ParseException e) {
    		System.out.println("-Error:" + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args) throws Exception{
//        int PageNo = 3;
//        String Tag = "java";
//        for(int i = 1; i <= PageNo; i++){
//	        String s = StackOverflowCrawler.getSinglePage("https://api.stackexchange.com/2.2/search/advanced", "page=" + PageNo +"&pagesize=100&order=desc&sort=votes&tagged=" + Tag +"&site=stackoverflow");
//	        StringToJSON(s);
//        }
//     
    }
    
    public String getAllPages(String tag, int pageNo) {
    	if (tag == null || pageNo <= 0) {
    		return null;
    	}
    	
    	StringBuffer sb = new StringBuffer();
        for(int i = 1; i <= pageNo; i++){
	         sb.append(StackOverflowCrawler.getSinglePage("https://api.stackexchange.com/2.2/search/advanced", "page=" + pageNo +"&pagesize=100&order=desc&sort=votes&tagged=" + tag +"&site=stackoverflow"));
        }
        
        return sb.toString();
    }
}