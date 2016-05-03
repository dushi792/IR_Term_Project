package edu.pitt.IRWebProject.lucene.bo;

/**
 * Created by zhaojun on 12/13/15.
 */
public class Result {
    String title;
    String url;
    String source;
    String answer;
    String votes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
