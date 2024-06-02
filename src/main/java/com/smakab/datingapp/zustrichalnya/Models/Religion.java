package com.smakab.datingapp.zustrichalnya.Models;

public class Religion {

    String name;
    Religion parent;
    public Religion(String name){
        this.name = name;
        this.parent = null;
    }

    public Religion(String name, Religion parent){
        this.name = name;
        this.parent = parent;
    }

    public boolean hasParent(){
        if(parent == null) return false;
        return true;
    }
}
