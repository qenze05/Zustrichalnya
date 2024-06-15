package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.smakab.datingapp.zustrichalnya.Models.Person;

public class PersonComparable implements Comparable<PersonComparable>{

    double compatability = 0;

    Person person;

    public PersonComparable(Person person, double compatability){
        this.person = person;
        this.compatability = compatability;
    }

    @Override
    public int compareTo(PersonComparable that) {
        if(this.compatability > that.compatability) return 1;
        if(this.compatability < that.compatability) return -1;
        return 0;
    }
}
