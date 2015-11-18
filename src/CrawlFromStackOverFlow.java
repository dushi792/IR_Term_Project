import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.groovy.JsonSlurper;

import com.google.gson.Gson;

public class CrawlFromStackOverFlow {
	
	public void ParseStackOverFlowJson(String urlString) throws Exception {		
//		ObjectMapper objectMapper = new ObjectMapper();
		URL url = new URL(urlString);
		String json = readUrl(urlString);
//		JSON json1 = parse(url);
//		String carJson =
//		        "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
//		        "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
//		        "  \"nestedObject\" : { \"field\" : \"value\" } }";
//		
//		try {
//		    JsonNode node = objectMapper.readValue(url, JsonNode.class);
//
//		    JsonNode tagsNode = node.get("tags");
//		    String tags = tagsNode.asText();
//		    System.out.println("brand = " + tags);
//
//		    JsonNode doorsNode = node.get("doors");
//		    int doors = doorsNode.asInt();
//		    System.out.println("doors = " + doors);
//
//		    JsonNode array = node.get("owners");
//		    JsonNode jsonNode = array.get(0);
//		    String john = jsonNode.asText();
//		    System.out.println("john  = " + john);
//
//		    JsonNode child = node.get("nestedObject");
//		    JsonNode childField = child.get("field");
//		    String field = childField.asText();
//		    System.out.println("field = " + field);
//
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
	}
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}

	static class Item {
	    String title;
	    String link;
	    String description;
	}

	static class Page {
	    String title;
	    String link;
	    String description;
//	    String language;
	    List<Item> items;
	}
	
	class Book {
		String id;
		String alt;
		String rating;
		
	}

	public static void main(String[] args) throws Exception {
//		String json = readUrl("http://api.stackexchange.com/2.2/search/advanced?order=desc&sort=votes&tagged=json&site=stackoverflow");		
//		CrawlFromStackOverFlow crawl = new CrawlFromStackOverFlow();
//		crawl.ParseStackOverFlowJson(urlString);
		
//	    String json = readUrl("https://api.douban.com/v2/book/1220562");
	    String json = readUrl("http://www.javascriptkit.com/"
                + "dhtmltutors/javascriptkit.json");
	    Gson gson = new Gson();        
	    Page page = gson.fromJson(json, Page.class);

	    System.out.println(page.title);
	    for (Item item : page.items)
	        System.out.println("    " + item.title);
	}
}
