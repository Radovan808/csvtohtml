package cz.rado.csvtohtml;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ImportCSV {
    static ImportCSV instance;

    static {
        try {
            instance = new ImportCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Path for CSV file
     */
    public String CSV_Path = "src/resources/data.csv";
    List<ColumnsCSV> data;

    public ImportCSV() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(CSV_Path));
        CsvToBean<ColumnsCSV> exportCsv = new CsvToBeanBuilder(reader)
                .withSeparator(',')
                .withType(ColumnsCSV.class)
                .build();

        this.data = exportCsv.parse();
        reader.close();

    }

    /**
     * Return data from CSV file
     * @return
     */
    public List<ColumnsCSV> getData() {
        return data;
    }


    public static ImportCSV getInstance(){
    return instance;
    }
}