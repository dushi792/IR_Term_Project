package edu.pitt.IRWebProject.stackoverflow.parser;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;


/**
 * Created by zhaojun on 12/4/15.
 */
public class MyParser {
    public Document getPageContent(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (SocketTimeoutException e) {
            System.out.println("Timeout, try again");
            doc = getPageContent(url);
        }catch (HttpStatusException e) {
            System.out.println("404 for url " + url + " skip");
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public MyQuestion parseStacakOverFlowUrl(String url) throws IOException {
        MyQuestion quesiton = new MyQuestion();

        Document doc = getPageContent(url);
        if (doc == null) {
            return null;
        }

        getTitle(doc, quesiton);

        quesiton.answers = getAnswers(doc, quesiton);

        return quesiton;
    }

    public void getTitle(Document doc, MyQuestion ques) {
        Elements title = doc.getElementsByClass("question-hyperlink");
        ques.title = title.first().text();
//        System.out.println(title.get(0).text());

        Elements postCell = doc.getElementsByClass("postcell");
        Elements p = postCell.first().getElementsByTag("p");
        ques.content = p.text();

//        System.out.println(p.text());
//        System.out.println();

    }

    public ArrayList<MyAnswer> getAnswers(Document doc, MyQuestion ques) {
        Element answerPage = doc.getElementById("answers");
        ArrayList<MyAnswer> answersList = new ArrayList<MyAnswer>();
        // Get accepted answer.
//        Element acceptedAnswer = answerPage.select("div.answer.accepted-answer").first();
//        String vote_count = acceptedAnswer.getElementsByClass("vote-count-post").first().text();
//        String accepted_An = acceptedAnswer.getElementsByClass("post-text").first().text();
//        System.out.println(vote_count);
//        System.out.println(accepted_An);

        // Get remain answers.
        Elements answers = answerPage.getElementsByClass("answer");
        for (Element answer : answers) {
            MyAnswer newAnswer = new MyAnswer();
            newAnswer.votes = answer.getElementsByClass("vote-count-post").first().text();
            newAnswer.content = answer.getElementsByClass("post-text").first().text();
            answersList.add(newAnswer);

//            System.out.println(voteCount);
//            System.out.println(answerText);
        }

        return answersList;
    }
}
