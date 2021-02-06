package cz.rado.csvtohtml;

import java.util.Comparator;

/**
 * Class implements Comparator
 * sort by units
 */

public class UnitsComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        if((double)o1 > (double) o2){
            return -1;
        }else{
            return 1;
        }
    }
}
