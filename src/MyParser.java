import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhaojun on 12/4/15.
 */
public class MyParser {
    public void parseStacakOverFlowString(String str) {
        Document doc = Jsoup.parse(str);

        Elements title = doc.getElementsByClass("question-hyperlink");
        System.out.println(title.get(0).text());

        Elements postCell = doc.getElementsByClass("postcell");
        Elements p = postCell.get(0).getElementsByTag("p");
        System.out.println(p.text());
    }

    public void parseStacakOverFlowUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

        getTitle(doc);

        getAnswers(doc);
    }

    public void getTitle(Document doc) {
        Elements title = doc.getElementsByClass("question-hyperlink");
        System.out.println(title.get(0).text());

        Elements postCell = doc.getElementsByClass("postcell");
        Elements p = postCell.get(0).getElementsByTag("p");
        System.out.println(p.text());
        System.out.println();

    }

    public void getAnswers(Document doc) {
        Element answerPage = doc.getElementById("answers");

        // Get accepted answer.
//        Element acceptedAnswer = answerPage.select("div.answer.accepted-answer").first();
//        String vote_count = acceptedAnswer.getElementsByClass("vote-count-post").first().text();
//        String accepted_An = acceptedAnswer.getElementsByClass("post-text").first().text();
//        System.out.println(vote_count);
//        System.out.println(accepted_An);

        // Get remain answers.
        Elements answers = answerPage.getElementsByClass("answer");
        for (Element answer : answers) {
            String voteCount = answer.getElementsByClass("vote-count-post").first().text();
            String answerText = answer.getElementsByClass("post-text").first().text();
            System.out.println(voteCount);
            System.out.println(answerText);
        }
    }
}
