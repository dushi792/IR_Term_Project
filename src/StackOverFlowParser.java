import java.io.StringReader;

import com.google.gson.Gson;
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
		
		for (int page = 1; page < 2; page++) {
			// Set topic and num of pages
			Page singlePage = parser.parse("search", page);
			
			for (Question ques : singlePage.items) {
//				System.out.println(ques.title);
				String link = ques.link;
				System.out.println(link);
				MyParser myParser = new MyParser();
				myParser.parseStacakOverFlowUrl(link);
			}
		}
		
	}
}
