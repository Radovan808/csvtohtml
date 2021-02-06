package cz.rado.csvtohtml;

public class Vendor  {

    private String name;
    private Double units;


    public Vendor(String name, Double units) {
        this.name = name;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public Double getUnits() {
        return units;
    }
}

