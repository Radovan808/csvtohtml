package cz.rado.csvtohtml;

import com.opencsv.bean.CsvBindByName;

public class ColumnsCSV {
    @CsvBindByName(column = "Country")
    public String Country;

    @CsvBindByName(column = "Timescale")
    public String Timescale;

    @CsvBindByName(column = "Vendor")
    public String Vendor;

    @CsvBindByName(column = "Units")
    public double Units;


    public String getTimescale() {
        return Timescale;
    }

    public String getVendor() {
        return Vendor;
    }

    public double getUnits() {
        return Units;
    }
}
