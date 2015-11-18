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
		Page pages = parser.parse("java", 30);
		
		for (Question ques : pages.items) {
			System.out.println(ques.title);
		}
	}
}
