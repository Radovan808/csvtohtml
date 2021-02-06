package cz.rado.csvtohtml;

import java.util.Comparator;


/**
 * Class implements Comparator
 * sort by name
 */

public class AlphabetComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        String s1 = o1.toString().toLowerCase();
        String s2 = o2.toString().toLowerCase();
        return (s1.compareTo(s2));
    }


}
