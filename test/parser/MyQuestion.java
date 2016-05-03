package edu.pitt.IRWebProject.stackoverflow.parser;

import java.util.ArrayList;

/**
 * Created by zhaojun on 12/6/15.
 */
public class MyQuestion {
    String url;
    String title;
    String content;
    int totalvotes;
    ArrayList<MyAnswer> answers = new ArrayList<MyAnswer>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotalvotes() {
        return totalvotes;
    }

    public void setTotalvotes(int totalvotes) {
        this.totalvotes = totalvotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<MyAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<MyAnswer> answers) {
        this.answers = answers;
    }

}
