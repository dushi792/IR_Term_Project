package edu.pitt.IRWebProject.stackoverflow.parser;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;


public class StackOverFlowParserTest {
	
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
		String subtitle;		
		transient String pubdate;
		String author;
		List<Tag> tags;
	}
			
	class Tag {
		int count;
		String name;
		String title;
	}

	public static void main(String[] args) throws Exception {		
//	    String json = readUrl("https://api.douban.com/v2/book/1220562");
//		String json = "{\"subtitle\":\"\",\"author\":\"[日] 片山恭一\",\"pubdate\":\"2005-1\",\"tags\":[{\"count\":134,\"name\":\"片山恭一\",\"title\":\"片山恭一\"},{\"count\":62,\"name\":\"日本\",\"title\":\"日本\"},{\"count\":60,\"name\":\"日本文学\",\"title\":\"日本文学\"},{\"count\":38,\"name\":\"小说\",\"title\":\"小说\"},{\"count\":32,\"name\":\"满月之夜白鲸现\",\"title\":\"满月之夜白鲸现\"},{\"count\":15,\"name\":\"爱情\",\"title\":\"爱情\"},{\"count\":8,\"name\":\"純愛\",\"title\":\"純愛\"},{\"count\":8,\"name\":\"外国文学\",\"title\":\"外国文学\"}]}";
	    
//		StackOverflowCrawler crawler = new StackOverflowCrawler();
//		String json = crawler.getAllPages("java", 999);
		
	    Gson gson = new Gson();	    	    	    	    	    	    
//	    Book book = gson.fromJson(json, Book.class);

//	    System.out.println(book.subtitle);
//	    for (Tag tag : book.tags)
//	        System.out.println("count:" + tag.count + " name:" + tag.name + " title:" + tag.title);
	}
}
