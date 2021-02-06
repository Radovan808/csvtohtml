package cz.rado.csvtohtml;

import java.io.IOException;


public class App 
{
    public static void main( String[] args ) throws IOException {
        DataProcessing data = new DataProcessing();
        ExportHtml html = new ExportHtml();

        data.Quarter("2010 Q3");
        data.hashByUnits();

        html.htmlTable(data);


    }

}

