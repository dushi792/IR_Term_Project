import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zhaojun on 12/6/15.
 */
public class MyFileWriter {
    FileWriter writer;

    public MyFileWriter() {
        try {
            writer = new FileWriter("Result.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFromPage(Page page) throws IOException {
        for (Question ques : page.items) {
            writer.append("<DOC>\n");

            String link = ques.link;
            String quesitonID = link.split("/")[4];
            writer.append("<DOCNO>" + quesitonID + "</DOCNO>\n");
            writer.append("<DOCURL>" + link + "</DOCURL>\n");

            MyParser myParser = new MyParser();
            MyQuestion myQues = myParser.parseStacakOverFlowUrl(link);
            if (myQues == null) {
                continue;
            }

            writer.append("<title>" + myQues.title + "</title>\n");
            writer.append("<p>\n" + myQues.content + "\n</p>\n");

            for (MyAnswer ans : myQues.answers) {
                writer.append("<answer>\n");
                writer.append("<votes>" + ans.votes + "</votes>\n");
                writer.append("<p>\n" + ans.content + "\n</p>\n");
            }

            writer.append("</DOC>\n\n");
        }
    }

    public void closeWriter() {
        try {
            writer.close();
            System.out.println("Crete File Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Crete File Fail");
        }
    }
}
