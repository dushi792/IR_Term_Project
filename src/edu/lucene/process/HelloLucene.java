package edu.lucene.process;

/**
 * Created by zhaojun on 12/10/15.
 */
import edu.stackoverflow.parser.MyAnswer;
import edu.stackoverflow.parser.MyQuestion;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class HelloLucene {
    public static void main(String[] args) throws IOException, ParseException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        StandardAnalyzer analyzer = new StandardAnalyzer();

        // 1. create the index
        Directory directory = FSDirectory.open(Paths.get("./data/indexweb/"));

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter writer = new IndexWriter(directory, config);

        TrecWebReader webReader = new TrecWebReader();
        Map<String, MyQuestion> map;

        while ((map = webReader.nextQuestion()) != null) {
            addDoc(writer, map);
        }

        writer.close();

        // 2. query
        String querystr = args.length > 0 ? args[0] : "algorithm";

        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        Query q = new QueryParser("title", analyzer).parse(querystr);

        // 3. search
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        // 4. display results
        System.out.println("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("docno"));
            System.out.println(d.get("title"));
            System.out.println(d.get("content"));
            System.out.println(d.get("votes"));
            System.out.println(d.get("answerContent"));
        }

        // reader can only be closed when there
        // is no need to access the documents any more.
        reader.close();
    }

    private static void addDoc(IndexWriter writer, Map<String, MyQuestion> map) throws IOException {
        for (Map.Entry<String, MyQuestion> entry : map.entrySet()) {
            String docno = entry.getKey();

            MyQuestion ques = entry.getValue();
            String title = ques.getTitle();
            String content = ques.getContent();

            for (MyAnswer ans : ques.getAnswers()) {
                Document doc = new Document();

                doc.add(new StoredField("docno", docno));
                doc.add(new TextField("title", title, Field.Store.YES));
                if (content != null && content.length() > 0) {
                    doc.add(new TextField("content", content, Field.Store.YES));
                }

                doc.add((new StoredField("votes", ans.getVotes())));
                doc.add((new TextField("answerContent", ans.getContent(), Field.Store.YES)));

                writer.addDocument(doc);
            }
        }
        writer.close();
    }
}