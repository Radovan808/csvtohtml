package cz.rado.csvtohtml;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ExportHtml {
    public ExportHtml() throws IOException {
    }

    /**
     * export data to an html file using a template
     * @param dataProcessing
     * @throws IOException
     */
    public static void htmlTable(DataProcessing dataProcessing) throws IOException {

        File template = new File("src/resources/template.html");
        String htmlString = FileUtils.readFileToString(template);
        String body = dataProcessing.visualizeOutputTableInHTML();
        htmlString = htmlString.replace("$body", body);
        File newHtml = new File("src/resources/table.html");
        FileUtils.writeStringToFile(newHtml, htmlString);
    }

}

