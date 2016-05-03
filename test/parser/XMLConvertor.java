package edu.pitt.IRWebProject.stackoverflow.parser;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhaojun on 12/6/15.
 */
public class XMLConvertor {
    Element rootElement;

    public XMLConvertor() {
        rootElement = new Element("Document");

    }

    public void convertPageToXML(Page page) {
        try {
            for (Question ques : page.items) {
                Element newQues = new Element("DOC");
                Element docNo = new Element("DOCNO");
                Element docUrl = new Element("DOCURL");

                String link = ques.link;
                String quesitonID = link.split("/")[4];
                docNo.setText(quesitonID);
                docUrl.setText(link);
                newQues.addContent(docNo);
                newQues.addContent(docUrl);


                MyParser myParser = new MyParser();
                MyQuestion myQues = myParser.parseStacakOverFlowUrl(link);

                Element title = new Element("title");
                title.setText(myQues.title);
                newQues.addContent(title);

                Element content = new Element("p");
                content.setText(myQues.content);
                newQues.addContent(content);

                Element answers = new Element("answers");
                for (MyAnswer ans : myQues.answers) {
                    Element answer = new Element("answer");

                    Element votes = new Element("votes");
                    votes.setText(ans.votes);
                    answer.addContent(votes);

                    Element text = new Element("p");
                    text.setText(ans.content);
                    answer.addContent(text);

                    answers.addContent(answer);
                }
                newQues.addContent(answers);

                rootElement.addContent(newQues);
            }

            Document document = new Document(rootElement);
            Format format = Format.getCompactFormat();
            format.setIndent("");
            XMLOutputter xmloutputter = new XMLOutputter(format);
            OutputStream outputStream;
            outputStream = new FileOutputStream("document.xml");
            xmloutputter.output(document, outputStream);
            System.out.println("xml文档生成成功！");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
