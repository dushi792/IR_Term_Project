package edu.stackoverflow.parser;

import java.util.ArrayList;

/**
 * Created by zhaojun on 12/6/15.
 */
public class MyQuestion {
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

    String title;
    String content;
    ArrayList<MyAnswer> answers = new ArrayList<>();
}
