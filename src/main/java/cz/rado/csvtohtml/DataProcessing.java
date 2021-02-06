package cz.rado.csvtohtml;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;

public class DataProcessing {

    public Object[] keyVendor;

    HashMap<String, Double> hashMap = new HashMap<String, Double>();

    private double totalCount;

    /**
     * clearing hashMap and setting
     */
    public DataProcessing() {
        hashMap.clear();
        totalCount = 0;
        fillHashMap();
    }


    public void addVendor(Vendor vendor) {
        if (!hashMap.containsKey(vendor.getName())) {
            hashMap.put(vendor.getName(), vendor.getUnits());
        } else {
            hashMap.put(vendor.getName(), hashMap.get(vendor.getName()) + vendor.getUnits());
        }
        this.totalCount += vendor.getUnits();
    }


    /**
     * Fills the hashmap with all data
     */
    public void fillHashMap() {
        for (ColumnsCSV columns : ImportCSV.getInstance().getData()) {
            addVendor(new Vendor(columns.getVendor(), columns.getUnits()));
        }
        this.keyVendor = hashMap.keySet().toArray();
    }

    /**
     * Fill HashMap only with values which meet the conditions of given timeScale
     * Overloaded method with restriction of given timesclace.
     *
     * @param timescale
     */
    public void fillHashMap(String timescale) {
        for (ColumnsCSV columns : ImportCSV.getInstance().getData()) {
            if (columns.getTimescale().equals(timescale))
                addVendor(new Vendor(columns.getVendor(), columns.getUnits()));
        }
        this.keyVendor = hashMap.keySet().toArray();
    }

    /**
     * Update the hashMap when the user changes timescale
     *
     * @param timescale
     */

    public void Quarter(String timescale) {
        hashMap.clear();
        keyVendor = null;
        totalCount = 0.0;
        fillHashMap(timescale);
    }

    /**
     * Manufacturer's percentage sales calculator and rounding to one decimal place with HTML tags
     *
     * @param nameVendor
     * @return
     */

    public String CalculatePercentageHTML(String nameVendor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tr align=\"center\"> <td> " + nameVendor + " </td> <td> ");

        if(hashMap.isEmpty()){  fillHashMap(); }

        stringBuilder.append( String.format("%,d", hashMap.get(nameVendor).intValue()) +" </td> ");
        stringBuilder.append( "<td> "+ decimalFormat.format((hashMap.get(nameVendor)*100)/this.totalCount)+ "% </td> </tr>");

        return  stringBuilder.toString() ;
    }

    /**
     * return string with html tags and all info about vendors
     */

    public String visualizeOutputTableInHTML() {
        if (hashMap.isEmpty()) {
            fillHashMap();
        }
        StringBuilder sb = new StringBuilder();
        for (Object key : keyVendor) {
            sb.append(CalculatePercentageHTML((String) key) + "\n");
        }
        sb.append("<tr align=\"center\"> <td bgcolor= \" #FFFF00\"> Total </td> <td bgcolor= \" #FFFF00\"> "+ String.format("%,d", (int)totalCount) + " </td> <td bgcolor= \" #FFFF00\"> 100% </td> </tr> \n");
        return sb.toString();
    }

    /**
     * Changes order keys and sort them alphabetically by name
     */
    public void hashAlphabetically(){
        Object[] keys = hashMap.keySet().toArray();
        Arrays.sort(keys, new AlphabetComparator());
        this.keyVendor = keys;
    }

    /**
     * This method change order of keys and sort it by Units
     */
    public void hashByUnits(){
        Object[] values = hashMap.values().toArray();
        Arrays.sort(values, new UnitsComparator());

        for (int i = 0; i<values.length; i++){
            keyVendor[i]=getKeyFromValue(hashMap, values[i]);
        }
    }
    /**
     * This method return key of the given value
     */
     private static Object getKeyFromValue(HashMap hm, Object value){
         for (Object o: hm.keySet()) {
             if (hm.get(o).equals(value)){
                 return o;
             }
         }
         return null;
     }

}
