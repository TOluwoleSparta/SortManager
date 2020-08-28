package com.sparta;


public class Main {

    public static void main(String[] args) {
        SortManagerGUI sortManagerGUI = new SortManagerGUI("Sort Manager");

        SortManager sortManager = new SortManager(sortManagerGUI);
        sortManager.runSortManager();

        System.exit(0);
    }
}
