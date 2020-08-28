<img src="https://github.com/TOluwoleSparta/SortManager/blob/master/Assets/SpartaGlobal.png" width="300">

# Sort Manager
Sort Manager is a command line program that allows the user to select any of seven algorithms to sort an array of random integers in ascending order.

When you first run the program, the following menu will be shown to you in the command line:

<img src="https://github.com/TOluwoleSparta/SortManager/blob/master/Assets/SorterMenu.png" width="300">

As shown, there are seven sorting algorithms from which to choose
- Binary Sort
- Bubble Sort
- Insertion Sort
- Merge Sort
- (Iterative) Merge Sort
- Quick Sort
- Selection Sort
You will be asked to enter the corresponding number for the option you wish to choose; the sorters being options 1 through 7

You also have the option to compare the sorting time of all of the algorithms (option 8).

Option 9 further allows you to re-randomise the array, so that future sorting is carried out on a different set of random numbers.

Finally, option 10 will allow you to exit the program.


If you chose a sorting algorithm or you chose to compare them all, the program will then ask you to input the length of the random array you wish to sort. Please note that the size is limited to 1,000,000.


After setting the length of the array, if you chose a sorting algorithm, the program will then sort the array in ascending order. You will be shown
- the unsorted random array
- the sorting algorithm that was used to sort the array
- the sorted array
- the time taken to sort the array

Otherwise, if you chose to compare all of the algorithms, the program will instead display a table listing the sorting times (averaged over 20 runs) of each algorithm on the random array. The table is sorted in ascending order of time taken, with the fastest algorithm for that comparison at the top of the table. The image below shows an example.

<img src="https://github.com/TOluwoleSparta/SortManager/blob/master/Assets/SorterMenu.png" width="300">
