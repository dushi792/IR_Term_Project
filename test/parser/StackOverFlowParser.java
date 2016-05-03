package edu.pitt.IRWebProject.stackoverflow.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;


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
//		XMLConvertor xmlConvertor = new XMLConvertor();
		MyFileWriter myFileWriter = new MyFileWriter("title");

		for (int page = 1; page <= 200; page++) {
			// Set topic and num of pages
			Page singlePage = parser.parse("search", page);
//			xmlConvertor.convertPageToXML(singlePage);

			myFileWriter.writeFromPage(singlePage);
			System.out.println("Finish Page " + page);
		}
		myFileWriter.closeWriter();
	}

}
