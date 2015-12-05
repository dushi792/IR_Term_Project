import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


public class StackOverFlowParser {
	public Page parse(String tag, int PageNo) {
		StackOverflowCrawler crawler = new StackOverflowCrawler();
		String json = crawler.getAllPages(tag, PageNo);
		
	    Gson gson = new Gson();
	    
	    
	    JsonReader reader = new JsonReader(new StringReader(json));
	    reader.setLenient(true);
	    Page pages = gson.fromJson(reader, Page.class);
	    
	    return pages;
	}
	
	public static void main(String[] args) throws Exception {		
		StackOverFlowParser parser = new StackOverFlowParser();
		
		for (int page = 1; page < 50; page++) {
			Page singlePage = parser.parse("java", page);
			
			for (Question ques : singlePage.items) {
				System.out.println(ques.title);
			}
		}
		
	}
}
