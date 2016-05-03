package edu.pitt.IRWebProject.stackoverflow.parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhaojun on 12/6/15.
 */
public class MyFileWriter {
    PrintWriter writer;
    PrintWriter titleWriter;

    public MyFileWriter() {
        try {
            writer = new PrintWriter(new BufferedWriter(new
                    FileWriter("result.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyFileWriter(String type) {
        try {
            writer = new PrintWriter(new BufferedWriter(new
                    FileWriter("result.xml")));
            titleWriter = new PrintWriter(new BufferedWriter(new
                    FileWriter(type +".xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFromPage(Page page) throws IOException {
        for (Question ques : page.items) {
            writer.print("<DOC>\n");

            String link = ques.link;
            String quesitonID = link.split("/")[4];
            writer.print("<DOCNO>" + quesitonID + "</DOCNO>\n");
            writer.print("<DOCURL>" + link + "</DOCURL>\n");

            MyParser myParser = new MyParser();
            MyQuestion myQues = myParser.parseStacakOverFlowUrl(link);
            if (myQues == null) {
                continue;
            }

            writer.print("<title>" + myQues.title + "</title>\n");
            writer.println("<content>");
            writer.print("<p>\n" + myQues.content + "\n</p>\n");
            writer.println("</content>");

            if (titleWriter != null) {
                writeTitle(myQues);
            }

            for (MyAnswer ans : myQues.answers) {
                writer.print("<answer>\n");
                writer.print("<votes>" + ans.votes + "</votes>\n");
                writer.print("<p>\n" + ans.content + "\n</p>\n");
                writer.println("</answer>");
            }

            writer.print("</DOC>\n\n");
        }
    }

    public void writeTitle(MyQuestion myQues) throws IOException {
        titleWriter.println(myQues.title);
        titleWriter.println(myQues.content);
        titleWriter.println();
    }

    public void closeWriter() {
//        try {
            writer.close();
            titleWriter.close();
            System.out.println("Crete File Successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Crete File Fail");
//        }
    }
}
