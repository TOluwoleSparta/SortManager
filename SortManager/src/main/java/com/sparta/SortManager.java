package com.sparta;

import com.sparta.exceptions.menu.*;
import com.sparta.sorters.*;
import java.text.NumberFormat;
import java.util.*;

public class SortManager {
    private final int[] bigNumberArray = new int[1_000_000];
    private final int NUMBER_OF_SORTERS = 7;
    private final int COMPARE_ALL_SORTS_OPTION = 8;
    private final int RE_RANDOMISE_ARRAY_OPTION = 9;
    private final int EXIT_OPTION = 10;
    private final int MAX_RUNS = 20;;   // over which to average the compared sort times

    public SortManager() {
    }

    public void runSortManager() {
        int inputChoice, inputLength;
        SortResults sortResults;
        int[] array;

        randomiseArray();

        do {
            inputChoice = getSorterChoice();

            if (inputChoice != EXIT_OPTION) {

                if (inputChoice != RE_RANDOMISE_ARRAY_OPTION) {
                    inputLength = getArrayLengthInput();
                    array = Arrays.copyOf(bigNumberArray, inputLength);

                    if (inputChoice == COMPARE_ALL_SORTS_OPTION) {
                        compareAllSorters(array);
                    } else {
                        sortResults = callChosenSorter(inputChoice, array);
                        printSorterResults(sortResults);
                    }
                } else {
                    randomiseArray();
                    System.out.println("\nArray has been re-randomised.");
                }


                System.out.println();
            }
        } while (inputChoice != EXIT_OPTION);
    }

    private int getSorterChoice() {
        Scanner scanner = new Scanner(System.in);
        int inputChoice;

        do {
            System.out.println("\n==================================================================\n");

            System.out.println("Enter the number for the sorting algorithm you would like to use.");
            System.out.println("You may also compare all algorithms or refill the random array.");
            System.out.println("----------------------------------------------------------------");
            System.out.println("  1. Binary Sort");
            System.out.println("  2. Bubble Sort");
            System.out.println("  3. Insertion Sort");
            System.out.println("  4. Merge Sort");
            System.out.println("  5. Merge Sort (Iterative)");
            System.out.println("  6. Quick Sort");
            System.out.println("  7. Selection Sort");
            System.out.println("  8. Compare All Algorithm Times");
            System.out.println("  9. Re-Randomise Array");
            System.out.println(" 10. EXIT");

            System.out.print("\nYour Choice: ");

            try {
                inputChoice = scanner.nextInt();

                if (inputChoice < 1 || inputChoice > EXIT_OPTION) {
                    throw new MenuOutOfBoundsException("Input is out of bounds. Please enter an integer between 1 and " + EXIT_OPTION + ".");
                }
            } catch (MenuException m) {
                inputChoice = 0;
                System.out.println(m.getMessage());
            } catch (InputMismatchException i) {
                inputChoice = 0;
                System.out.println("Invalid input. Expected an integer input.");
                scanner.nextLine();
            } catch (Exception e) {
                inputChoice = 0;
                System.out.println(e.getMessage());
            }
        } while (inputChoice == 0);

        return inputChoice;
    }

    private int getArrayLengthInput() {
        Scanner scanner = new Scanner(System.in);
        int inputLength;

        do {
            System.out.println("\nHow long is the array? (This will max out at 1,000,000)");
            System.out.print("Array Length: ");

            try {
                inputLength = scanner.nextInt();

                if (inputLength == 0) {
                    throw new MenuInvalidInputException("Array cannot be empty. Please enter an integer value greater than 0.");
                } else if (inputLength   < 0) {
                    throw new MenuInvalidInputException("Array cannot be of negative length. Please enter an integer value greater than 0.");
                }
            } catch (MenuInvalidInputException m) {
                inputLength = 0;
                System.out.println(m.getMessage());
            } catch (InputMismatchException i) {
                inputLength = 0;
                System.out.println("Invalid input. Expected an integer input.");
                scanner.nextLine();
            }
            inputLength = Math.min(inputLength, 1_000_000);

        } while (inputLength < 1);

        return inputLength;
    }

    public SortResults callChosenSorter(int inputChoice, int[] array) {
        Sorter sorter = getSorter(inputChoice);
        return sorter.sort(array);
    }

    private void printSorterResults(SortResults sortResults) {
        System.out.println();
        sortResults.printResults();
    }

    private void compareAllSorters(int[] array) {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.UK);
        List<AlgorithmSortTime> algorithmTimesList;

        algorithmTimesList = runAllSorters(array);

        // sorts in ascending order of sort times
        algorithmTimesList.sort((a1, a2) -> (int) (1_000_000.0 * (a1.sortTime - a2.sortTime)));

        System.out.println("\n-----------------------------------------------------");
        System.out.println(  "Sorting Algorithm Times (Asc) over " + format.format(array.length) + " elements");
        System.out.println(  "-----------------------------------------------------");

        for (int index = 0; index < algorithmTimesList.size(); index++) {
            System.out.printf("%-25s : %.5f ms\n", algorithmTimesList.get(index).algorithm, algorithmTimesList.get(index).sortTime);
        }
    }

    public List<AlgorithmSortTime> runAllSorters(int[] array) {
        List<AlgorithmSortTime> times = new ArrayList<>();
        SortResults sortResults;

        for (int index = 0; index < NUMBER_OF_SORTERS; index++) {
            times.add(new AlgorithmSortTime());
            times.get(index).algorithm = getSorterName(index + 1);
            times.get(index).sortTime = 0;

            // gets average time across MAX_RUNS runs
            for (int run = 0; run < MAX_RUNS; run++) {
                sortResults = callChosenSorter(index + 1, array);
                times.get(index).sortTime += (sortResults.getSortTimeMilliSeconds() / ((double) MAX_RUNS));
            }
        }

        return times;
    }

    public Sorter getSorter(int inputChoice) {
        Sorter sorter;

        switch (inputChoice) {
            case 1:
                sorter = new BinarySorter();
                break;
            case 2:
                sorter = new BubbleSorter();
                break;
            case 3:
                sorter = new InsertionSorter();
                break;
            case 4:
                sorter = new MergeSorter();
                break;
            case 5:
                sorter = new MergeIterativeSorter();
                break;
            case 6:
                sorter = new QuickSorter();
                break;
            case 7:
                sorter = new SelectionSorter();
                break;
            default /* case 8 */:
                sorter = new TreeInterfaceBinarySorter();
                break;
        }
        return sorter;
    }

    public String getSorterName(int inputChoice) {
        switch (inputChoice) {
            case 1:
                return "Binary Sorter";
            case 2:
                return "Bubble Sorter";
            case 3:
                return "Insertion Sorter";
            case 4:
                return "Merge Sorter";
            case 5:
                return "Merge Sorter (Iterative)";
            case 6:
                return "Quick Sorter";
            case 7:
                return "Selection Sorter";
            default /* case 8 */:
                return "Binary Sort 2";
        }
    }

    public void randomiseArray() {
        for (int index = 0; index < bigNumberArray.length; index++) {
            bigNumberArray[index] = (int) Math.round(Math.random() * 1000);
        }
    }
}
