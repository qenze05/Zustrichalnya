package com.smakab.datingapp.zustrichalnya.Models.Search;

import java.util.Comparator;

public class PersonComparator implements Comparator<PersonComparable> {
    @Override
    public int compare(PersonComparable p1, PersonComparable p2) {
        if(p1.compatability > p2.compatability) return 1;
        if(p1.compatability < p2.compatability) return -1;
        return 0;
    }
}