package edu.lucene.process;

import edu.stackoverflow.parser.MyAnswer;
import edu.stackoverflow.parser.MyQuestion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by zhaojun on 12/11/15.
 */
public class TrecWebReader {
    FileInputStream fis;
    BufferedReader reader;
    String line;

    public TrecWebReader() throws IOException {
        fis = new FileInputStream("./data/zhihutrecweb");
        reader = new BufferedReader(new InputStreamReader(fis));
    }

    // This method returns the content in "<>" tag
    String getLineType(String readline) {
        if (readline == null) {
            return null;
        }

        if (readline.length() == 0 || readline.charAt(0) != '<') {
            return "NoTag";
        }

        int start = 1;
        int end = readline.indexOf('>');

        if (start > end) {
            return "NoTag";
        }

        return readline.substring(start, end);
    }

    // This method reads the number in DOCNO tag
    private String getTagText(String line) throws IOException {
        line = line.replaceAll("<\\w+>|</\\w+>", "");

        return line;
    }

    // This method reads the text between "TEXT" tags
    private String readWebText() throws IOException {
        StringBuilder sb = new StringBuilder();

        line = reader.readLine();

        while (!getLineType(line).equals("/p")) {
            sb.append(line);
            line = reader.readLine();
        }

        return sb.toString();
        // Remove HTML tags using regular expression
        //String removeTag = sb.toString().replaceAll("<[^><]*>", "");
    }

    public Map<String, MyQuestion> nextQuestion() throws IOException {
        line = reader.readLine();
        if (line == null) {
            reader.close();
            fis.close();
            return null;
        }

        Map<String, MyQuestion> map = new HashMap<>();
        String docNo = null;
        MyQuestion ques = null;
        int totalvotes = 0;

        for ( ;line != null; line = reader.readLine()){
            if (line.length() == 0 ) {
                continue;
            }

            String lineType = getLineType(line);

            switch (lineType) {
                case ("DOCNO") :
                    totalvotes = 0;
                    docNo = getTagText(line);
                    ques = new MyQuestion();
                    break;
                case ("title") :
                    ques.setTitle(getTagText(line).toLowerCase());
                    break;
                case ("content") :
                    setContent(ques);
                    break;
                case ("answer") :
                    totalvotes += addAnswer(ques);
                    break;
                case ("/DOC") :
                    if (ques != null) {
                        ques.setTotalvotes(totalvotes);
                    }

                    map.put(docNo, ques);
                    return map;

            }

        }

        return map;
    }

    private void setContent(MyQuestion ques) throws IOException {
        StringBuilder sb = new StringBuilder();
        line = reader.readLine();

        if (getLineType(line).equals("p")) {
            line = reader.readLine();

            for ( ; !getLineType(line).equals("/content"); line = reader.readLine()) {

                sb.append(line.toLowerCase());
            }
            sb.delete(sb.length() - 1, sb.length());
            ques.setContent(sb.toString());
        }
    }

    private int addAnswer(MyQuestion ques) throws IOException {
        MyAnswer ans = new MyAnswer();

        String lineType;
        int votesInt = 0;

        for (line = reader.readLine(); !(lineType = getLineType(line)).equals("/answer") ; line = reader.readLine()) {
            if (lineType.equals("votes")) {
                ans = new MyAnswer();
                String votes = getTagText(line);
                ans.setVotes(votes);

                votesInt = Integer.valueOf(votes);
            }
            else if (lineType.equals("p")) {
                StringBuilder sb = new StringBuilder();

                for (line = reader.readLine();!getLineType(line).equals("/p") ; line = reader.readLine()) {
                    sb.append(line.toLowerCase());
                    sb.append('\n');
                }

                ans.setContent(sb.toString());
                ques.getAnswers().add(ans);
            }
        }
        return votesInt;
    }


    public static void main(String[] args) throws IOException {
        TrecWebReader trecWebReader = new TrecWebReader();

        Map<String, MyQuestion> map;

        while ((map = trecWebReader.nextQuestion()) != null) {
            for (Map.Entry<String, MyQuestion> entry : map.entrySet()) {
                System.out.println("DOCNO" + entry.getKey());
                System.out.println(entry.getValue().getTitle());
                System.out.println(entry.getValue().getContent());
                for (MyAnswer ans : entry.getValue().getAnswers()) {
                    System.out.println(ans.getVotes());
                    System.out.println(ans.getContent());
                }
            }
        }
    }

}
