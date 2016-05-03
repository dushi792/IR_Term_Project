package edu.pitt.IRWebProject.lucene.bo;

import java.util.List;

/**
 * Created by zhaojun on 12/14/15.
 */
public class ResultList {
    int totalDoc;
    int totalPage;
    int currentPage;
    List<Result> results;

    public int getTotalDoc() {
        return totalDoc;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<Result> getResults() {
        return results;
    }

    public ResultList(int totalDoc, int totalPage, int currentPage, List<Result> results) {
        this.totalPage = totalPage;
        this.totalDoc = totalDoc;
        this.currentPage = currentPage;
        this.results = results;
    }
}
