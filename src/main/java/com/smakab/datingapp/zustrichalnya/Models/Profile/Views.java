package com.smakab.datingapp.zustrichalnya.Models.Profile;

import java.util.TreeSet;

public class Views {
    private TreeSet<String> movements;

    public Views() {
        this.movements = new TreeSet<>();
    }

    public TreeSet<String> getMovements() {
        return movements;
    }

    public void setMovements(TreeSet<String> movements) {
        this.movements = movements;
    }

    public void renameMovement(String oldValue, String newValue) {
        removeMovement(oldValue);
        this.movements.add(newValue);
        System.out.println(movements);
    }

    public void removeMovement(String value) {
        this.movements.remove(value);
    }
}
