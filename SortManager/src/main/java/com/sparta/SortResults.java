package com.sparta;

import java.util.Arrays;

public class SortResults {
    private final int ARRAY_COMPRESSION_SIZE = 100;
    private final int[] unsortedArray;
    private final String algorithmUsed;
    private final int[] sortedArray;
    private final double sortTimeMilliSeconds;

    public SortResults(int[] unsortedArray, String algorithmUsed, int[] sortedArray, double sortTimeMilliSeconds) {
        this.unsortedArray = unsortedArray;
        this.algorithmUsed = algorithmUsed;
        this.sortedArray = sortedArray;
        this.sortTimeMilliSeconds = sortTimeMilliSeconds;
    }

    public void printResults() {
        System.out.println("Unsorted Array: " + printableArray(this.unsortedArray));
        System.out.println("Algorithm Used: " + this.algorithmUsed);
        System.out.println("Sorted Array:   " + printableArray(this.sortedArray));
        System.out.println("Time Taken:     " + this.sortTimeMilliSeconds + " milliseconds");
    }

    private String printableArray(int[] array) {
        if (array.length <= ARRAY_COMPRESSION_SIZE) {
            return Arrays.toString(array);
        } else {
            String arrayString = "[";
            // first few elements
            for (int index = 0; index < (ARRAY_COMPRESSION_SIZE / 2); index++) {
                arrayString += Integer.toString(array[index]) + ", ";
            }
            arrayString += ".... ";
            // last few elements
            for (int index = array.length - (ARRAY_COMPRESSION_SIZE / 2); index < array.length; index++) {
                arrayString += ", " + Integer.toString(array[index]);
            }
            arrayString += "]";

            return arrayString;
        }
    }

    public String getAlgorithmUsed() {
        return this.algorithmUsed;
    }

    public int[] getUnsortedArray() {
        return this.unsortedArray;
    }

    public int[] getSortedArray() {
        return this.sortedArray;
    }

    public double getSortTimeMilliSeconds() {
        return this.sortTimeMilliSeconds;
    }
}
