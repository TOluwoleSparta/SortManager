package com.sparta;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

public class SortManagerGUI extends JFrame {
    private JPanel panel;
    private JButton binarySortButton;
    private JButton quickSortButton;
    private JButton iterativeMergeSortButton;
    private JButton insertionSortButton;
    private JButton bubbleSortButton;
    private JButton mergeSortButton;
    private JButton selectionSortButton;
    private JTextField selectAlgorithmText;
    private JTextArea sortResultsText;
    private JTextArea unsortedArrayText;
    private JTextField timeTakenText;
    private JTextField algorithmUsedText;
    private JButton compareAllSortersButton;
    private JTextField inputArrayLengthText;
    private JSpinner arrayLengthSpinner;
    private JTextField messagesText;
    private JScrollPane scrollPanel1;
    private JScrollPane scrollPanel2;
    private JButton reRandomiseArrayButton;

    public SortManager sortManager;
    public int[] bigNumberArray;

    public SortManagerGUI(String s) {
        super(s);
        setUpJFrame();
        styleTextFields();
        buttonActionHandlers();
    }

    public void setUpJFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(960, 540);
        this.setContentPane(panel);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void styleTextFields() {
        // hiding borders of some text fields and giving others gray borders
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        selectAlgorithmText.setBorder(null);
        inputArrayLengthText.setBorder(null);
        messagesText.setBorder(null);
        algorithmUsedText.setBorder(border);
        timeTakenText.setBorder(border);

        // left aligning text boxes
        selectAlgorithmText.setHorizontalAlignment(JTextField.CENTER);
        inputArrayLengthText.setHorizontalAlignment(JTextField.CENTER);

        // setting text box background colours, making message text field background transparent
        algorithmUsedText.setBackground(Color.white);
        timeTakenText.setBackground(Color.white);
        messagesText.setOpaque(false);

        // default text areaa text showing contents of text areas
        algorithmUsedText.setText(" Algorithm Used:");
        unsortedArrayText.setText("================\n Unsorted Array \n================");
        sortResultsText.setText("==============\n Sort Results \n==============");
        timeTakenText.setText(" Time Taken:");

        // prevent automatic scrolling of unsorted array and sorting results text areas
        ((DefaultCaret) unsortedArrayText.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        ((DefaultCaret) sortResultsText.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        // only allowing legal array length values
        arrayLengthSpinner.setModel(new SpinnerNumberModel(1, 0, 1_000_000, 1));
    }

    private void buttonActionHandlers() {
        binarySortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                callSorter(1);
            }
        });

        bubbleSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                callSorter(2);
            }
        });

        insertionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                callSorter(3);
            }
        });

        mergeSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                callSorter(4);
            }
        });

        iterativeMergeSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                callSorter(5);
            }
        });

        quickSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                callSorter(6);
            }
        });

        selectionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                callSorter(7);
            }
        });

        compareAllSortersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                compareAllSorters();
            }
        });

        reRandomiseArrayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                sortManager.randomiseArray();
                messagesText.setForeground(Color.BLUE);
                messagesText.setText(" Array has been re-randomised.");
            }
        });
    }

    private void callSorter(int sorterChoice) {
        int inputLength = Integer.parseInt(arrayLengthSpinner.getValue().toString());
        int[] array;
        SortResults sortResults;

        if (!isArrayLengthInputValid(inputLength)) {
            return;
        }

        messagesText.setText("Sorter is running...");
        enableAllButtons(false);

        array = Arrays.copyOf(bigNumberArray, inputLength);
        sortResults = sortManager.callChosenSorter(sorterChoice, array);

        enableAllButtons(true);

        showSortResults(sortResults);
    }

    private boolean isArrayLengthInputValid(int inputLength) {
        if (inputLength < 0) {
            messagesText.setForeground(Color.RED);
            messagesText.setText(" Array cannot be of negative length. Please enter an integer value greater than 0.");
            return false;
        } else if (inputLength == 0) {
            messagesText.setForeground(Color.RED);
            messagesText.setText(" Array cannot be empty. Please enter an integer value greater than 0.");
            return false;
        } else {
            messagesText.setForeground(Color.BLUE);
            return true;
        }
    }

    private void enableAllButtons(boolean able) {
        binarySortButton.setEnabled(able);
        bubbleSortButton.setEnabled(able);
        insertionSortButton.setEnabled(able);
        mergeSortButton.setEnabled(able);
        iterativeMergeSortButton.setEnabled(able);
        quickSortButton.setEnabled(able);
        selectionSortButton.setEnabled(able);
        compareAllSortersButton.setEnabled(able);
    }

    private void showSortResults(SortResults sortResults) {
        messagesText.setText(" Sorter has finished running!");

        algorithmUsedText.setText(" Algorithm Used: " + sortResults.getAlgorithmUsed());
        unsortedArrayText.setText("================\n Unsorted Array \n================\n\n" + Arrays.toString(sortResults.getUnsortedArray()));
        sortResultsText.setText("==============\n Sorted Array \n==============\n\n" + Arrays.toString(sortResults.getSortedArray()));
        timeTakenText.setText(" Time Taken: " + sortResults.getSortTimeMilliSeconds() + " milliseconds");
    }

    private void compareAllSorters() {
        int inputLength = Integer.parseInt(arrayLengthSpinner.getValue().toString());
        List<AlgorithmSortTime> algorithmTimesList;
        int[] unsortedArray, array;

        if (!isArrayLengthInputValid(inputLength)) {
            return;
        }

        unsortedArray = Arrays.copyOf(bigNumberArray, inputLength);
        array = Arrays.copyOf(unsortedArray, inputLength);

        messagesText.setText(" Running all sorters...");
        enableAllButtons(false);

        algorithmTimesList = sortManager.runAllSorters(array);

        enableAllButtons(true);

        // sorts in ascending order of sort times
        algorithmTimesList.sort((a1, a2) -> (int) (1_000_000.0 * (a1.sortTime - a2.sortTime)));

        showComparisonResults(unsortedArray, algorithmTimesList);
    }

    private void showComparisonResults(int[] array, List<AlgorithmSortTime> algorithmTimesList) {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.UK);
        StringBuilder comparisonResultsString = new StringBuilder();
        messagesText.setText(" Sorters have finished running!");

        algorithmUsedText.setText(" Algorithm Used: All");
        unsortedArrayText.setText("================\n Unsorted Array \n================\n\n"
                                    + Arrays.toString(array));
        comparisonResultsString.append("==================================================================\n");
        comparisonResultsString.append(" Sorting Average Algorithm Times (Ascending) over ").append(format.format(array.length)).append(" elements\n");
        comparisonResultsString.append("==================================================================\n");

        for (AlgorithmSortTime algorithmSortTime : algorithmTimesList) {
            comparisonResultsString.append(String.format("%-25s : %.5f milliseconds\n", algorithmSortTime.algorithm, algorithmSortTime.sortTime));
        }
        sortResultsText.setText(comparisonResultsString.toString());

        timeTakenText.setText(" Fastest sorter over "
                                + format.format(array.length)
                                + " elements is " + algorithmTimesList.get(0).algorithm
                                + " with an average time of " + String.format("%.5f", algorithmTimesList.get(0).sortTime)
                                + "ms.");
    }
}
