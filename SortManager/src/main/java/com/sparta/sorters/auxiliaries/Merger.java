package com.sparta.sorters.auxiliaries;

public abstract class Merger {

    public void mergeSubArrays(int[] array, int start, int middle, int end) {
        int leftSubArraySize = middle - start + 1;
        int rightSubArraySize = end - middle;

        // fill in left sub array
        int[] leftSubArray = fillSubArray(array, start, leftSubArraySize);
        // fill in right sub array
        int[] rightSubArray = fillSubArray(array, middle + 1, rightSubArraySize);

        int leftSubArrayIndex = 0;
        int rightSubArrayIndex = 0;

        // merge both halves
        for (int index = start; index <= end; index++) {
            // while both sub arrays still have elements to sort into the main array
            if (leftSubArrayIndex < leftSubArraySize && rightSubArrayIndex < rightSubArraySize) {
                if (leftSubArray[leftSubArrayIndex] <= rightSubArray[rightSubArrayIndex]) {
                    array[index] = leftSubArray[leftSubArrayIndex];
                    leftSubArrayIndex++;
                } else {
                    array[index] = rightSubArray[rightSubArrayIndex];
                    rightSubArrayIndex++;
                }
            } else if (leftSubArrayIndex == leftSubArraySize) {
                // if done sorting in the left half, add in rest of right half
                array[index] = rightSubArray[rightSubArrayIndex];
                rightSubArrayIndex++;
            } else if (rightSubArrayIndex == rightSubArraySize) {
                // if done sorting in the right half, add in rest of left half
                array[index] = leftSubArray[leftSubArrayIndex];
                leftSubArrayIndex++;
            }
        }
    }

    public int[] fillSubArray(int[] array, int start, int subArraySize) {
        int[] subArray = new int[subArraySize];

        System.arraycopy(array, start, subArray, 0, subArraySize);

        return subArray;
    }
}
