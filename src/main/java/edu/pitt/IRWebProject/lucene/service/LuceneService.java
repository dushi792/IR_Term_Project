package edu.pitt.IRWebProject.lucene.service;

import edu.pitt.IRWebProject.lucene.bo.Result;
import edu.pitt.IRWebProject.lucene.bo.ResultList;

/**
 * Created by zhaojun on 12/10/15.
 */

import edu.pitt.IRWebProject.lucene.bo.MyAnswer;
import edu.pitt.IRWebProject.lucene.bo.MyQuestion;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.ServletContext;

@Service
public class LuceneService {
	StandardAnalyzer analyzer;
	Directory dir1;
	Directory dir2;
	IndexWriterConfig config1;
	IndexWriterConfig config2;
	IndexWriter writer = null;
	IndexReader[] readers;
	IndexSearcher searcher;

	@Autowired
	private ServletContext servletContext;

	public LuceneService() throws IOException {
		// 0. Specify the analyzer for tokenizing text.
		// The same analyzer should be used for indexing and searching
		analyzer = new StandardAnalyzer();

		config1 = new IndexWriterConfig(analyzer);
		config2 = new IndexWriterConfig(analyzer);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		LuceneService lucene = new LuceneService();

		// lucene.writetoLucene("StackOverFlow");
		// lucene.writetoLucene("zhihu");

		// ResultList list = lucene.searchQuery("How to create google", 0);

	}

	private void initDirectory() throws IOException {
		String rootPath = servletContext.getRealPath("/");
		if (dir1 == null) {
			dir1 = FSDirectory.open(Paths.get(rootPath + "/data/ZhihuIndex/"));
		}
		if (dir2 == null) {
			dir2 = FSDirectory.open(Paths.get(rootPath + "/data/StackOverFlowIndex/"));
		}

	}

	private void initReader() throws IOException {
		initDirectory();

		readers = new IndexReader[2];
		readers[0] = DirectoryReader.open(dir1);
		readers[1] = DirectoryReader.open(dir2);

		searcher = new IndexSearcher(new MultiReader(readers));
	}

	public ResultList searchQuery(String query, int page) throws Exception {

		ScoreDoc[] results = searchDoc(query);

		ArrayList<Result> result = readSortedResults(results);

		// closeReader();
		int startNum = 10 * (page - 1);
		int endNum = startNum + 10;

		return new ResultList(result.size(), result.size() / 10, page,
				result.subList(startNum, endNum <= result.size() ? endNum : result.size()));
	}

	/*
	 * @SuppressWarnings("unused")
	 * private void writetoLucene(String type) throws IOException {
	 * initDirectory();
	 * 
	 * TrecWebReader webReader;
	 * 
	 * if (type.equals("zhihu")) {
	 * writer = new IndexWriter(dir1, config1);
	 * webReader = new TrecWebReader(type);
	 * } else {
	 * writer = new IndexWriter(dir2, config2);
	 * webReader = new TrecWebReader(type);
	 * }
	 * 
	 * Map<String, MyQuestion> map;
	 * 
	 * while ((map = webReader.nextQuestion()) != null) {
	 * addDoc(writer, map);
	 * }
	 * 
	 * writer.close();
	 * }
	 */

	private ScoreDoc[] searchDoc(String query) throws Exception {
		// the "title" arg specifies the default field to use
		// when no field is explicitly specified in the query.
		if (readers == null || readers.length == 0) {
			initReader();
		}

		String[] fields = new String[] { "title", "content", "answerContent" };
		MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(fields, analyzer);
		Query q = multiFieldQueryParser.parse(query);

		int hitsPerPage = 1000000;

		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		/*
		 * System.out.println("topdocid" + hits[0].doc);
		 * System.out.println("hitsLength" + hits.length);
		 */
		return hits;
	}

	private ArrayList<Result> readSortedResults(ScoreDoc[] hits) throws IOException {
		ArrayList<Result> resultList = new ArrayList<Result>();

		List<Map.Entry<Integer, Double>> list = sortDocScore(hits);

		for (Map.Entry<Integer, Double> entry : list) {
			Result result = new Result();
			Document d = searcher.doc(entry.getKey());
			// Double score = entry.getValue();
			String source = getSource(d.get("docno"));

			// System.out.println("Score:" + score);
			result.setTitle(d.get("title"));
			result.setUrl(d.get("url"));
			result.setSource(source);
			result.setVotes(d.get("votes"));
			result.setAnswer(d.get("answerContent"));

			resultList.add(result);

			// System.out.println("DOCNO:" + d.get("docno"));
			// System.out.println("title:" + d.get("title"));
			// System.out.println("content:" + d.get("content"));
			// System.out.println("votes:" + d.get("votes"));
			// System.out.println("Answer:" + d.get("answerContent"));
		}

		return resultList;
	}

	private String getSource(String docno) {
		return docno.charAt(0) == 'z' ? "zhihu" : "stackoverflow";
	}

	private List<Map.Entry<Integer, Double>> sortDocScore(ScoreDoc[] hits) throws IOException {
		TreeMap<Integer, Double> treeMap = new TreeMap<Integer, Double>();

		// **原始最高分:
		Double topScore = Double.valueOf(hits[0].score);

		for (ScoreDoc hit : hits) {
			Document d = searcher.doc(hit.doc);

			Double votes = Double.valueOf(d.get("votes"));
			Double totalVotes = Double.valueOf(d.get("totalVotes"));
			votes = votes < 0 ? 0 : votes;
			totalVotes = totalVotes < 0 ? 0 : totalVotes;
			int ansLength = d.get("answerContent").length();
			String source = getSource(d.get("docno"));

			// Calculate Final Score
			// Double Origin = hit.score * (Double.valueOf(d.get("votes")) + 1);

			// while (votes > 1000) {
			// votes /= 10;
			// }
			// Double newScore = hit.score + ((votes + 1.0) + ansLength) / (totalVotes - votes + 1);

			// ln function algorithm
			Double newScore;
			if (source.equals("zhihu")) {
				newScore = (double) hit.score / topScore * Math.log1p(ansLength)
						+ Math.log1p(votes) / 2.5 + Math.log1p(totalVotes) / 25;
			} else {
				newScore = (double) hit.score / topScore * Math.log1p(ansLength / 0.8)
						+ Math.log1p(votes) / 2 + Math.log1p(totalVotes) / 20;
			}

			treeMap.put(hit.doc, newScore);
		}

		List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer, Double>>(
				treeMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		return list;
	}

	/**
	 * lucene tokenize query to terms
	 * give credit to http://stackoverflow.com/questions/6334692/how-to-use-a-lucene-analyzer-to-tokenize-a-string#answer-9562816
	 * 
	 * @throws IOException
	 */
	public List<String> tokenizeString(Analyzer analyzer, String string) {
		List<String> result = new ArrayList<String>();
		try {
			TokenStream stream = analyzer.tokenStream(null, new StringReader(string));
			stream.reset();
			while (stream.incrementToken()) {
				result.add(stream.getAttribute(CharTermAttribute.class).toString());
			}
		} catch (IOException e) {
			// not thrown b/c we're using a string reader...
			throw new RuntimeException(e);
		}
		return result;
	}

	@SuppressWarnings("unused")
	private void closeReader() throws IOException {
		for (IndexReader reader : readers) {
			reader.close();
		}
	}

	@SuppressWarnings("unused")
	private void addDoc(IndexWriter writer, Map<String, MyQuestion> map) throws IOException {
		for (Map.Entry<String, MyQuestion> entry : map.entrySet()) {
			String docno = entry.getKey();

			MyQuestion ques = entry.getValue();
			String url = ques.getUrl();
			String title = ques.getTitle();
			String content = ques.getContent();

			for (MyAnswer ans : ques.getAnswers()) {
				Document doc = new Document();

				doc.add(new StoredField("docno", docno));
				doc.add(new StoredField("url", url));
				doc.add(new TextField("title", title, Field.Store.YES));
				doc.add((new StoredField("totalVotes", ques.getTotalvotes())));
				if (content != null && content.length() > 0) {
					doc.add(new TextField("content", content, Field.Store.YES));
				}

				doc.add((new StoredField("votes", ans.getVotes())));

				doc.add((new TextField("answerContent", ans.getContent(), Field.Store.YES)));

				writer.addDocument(doc);
			}
		}
	}
}