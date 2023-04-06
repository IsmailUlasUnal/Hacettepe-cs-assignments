import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SortManager {
    int[] sizeOfArrays;
    ISort[] sortMethods;
    int[] originalArray;


    int[][] arraysToSort;
    double[][][] timesForGraphs;



    public SortManager(int[] sizeOfArrays, ISort[] sortMethods, int[] originalArray) {
        this.originalArray = originalArray;
        this.sizeOfArrays = sizeOfArrays;
        this.sortMethods = sortMethods;

        arraysToSort = new int[sizeOfArrays.length][];
        for (int i = 0; i < sizeOfArrays.length; i++)
            arraysToSort[i] = Arrays.copyOf(originalArray, sizeOfArrays[i]);

        timesForGraphs = new double[3][sortMethods.length][];
        for (int i = 0; i < timesForGraphs.length; i++) {
            for (int j = 0; j < sortMethods.length; j++) {
                timesForGraphs[i][j] = new double[sizeOfArrays.length];
            }
        }

    }


    private double calculateAverageTime(ArrayList<Long> times) {
        long totalTime = 0;
        for (double time: times) totalTime += time;
        return (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / times.size();
    }



    private void runSort(int sortMethodIndex, int runTime, int arraySizeIndex) {
        ArrayList<Long> timesUnsorted = new ArrayList<>();
        ArrayList<Long> timesSorted = new ArrayList<>();
        ArrayList<Long> timesReverseSorted = new ArrayList<>();

        for (int i = 0; i < runTime; i++) {
            int[] currArr = Arrays.copyOf(arraysToSort[arraySizeIndex], arraysToSort[arraySizeIndex].length);

            timesUnsorted.add(sortMethods[sortMethodIndex].sort(currArr));
            checkSorted(currArr, sortMethodIndex, "unsorted", arraySizeIndex);

            timesSorted.add(sortMethods[sortMethodIndex].sort(currArr));
            checkSorted(currArr, sortMethodIndex, "sorted", arraySizeIndex);
            //Collections.reverse(Arrays.asList(currArr));
            reverseArray(currArr);
            checkReverse(currArr);

            timesReverseSorted.add(sortMethods[sortMethodIndex].sort(currArr));
            checkSorted(currArr, sortMethodIndex, "reverse sorted", arraySizeIndex);
        }


        timesForGraphs[0][sortMethodIndex][arraySizeIndex] = calculateAverageTime(timesUnsorted);
        System.out.print("duz + sortIndex " + sortMethodIndex + " + array size = " + arraySizeIndex + " === ");
        System.out.println(timesForGraphs[0][sortMethodIndex][arraySizeIndex]);


        timesForGraphs[1][sortMethodIndex][arraySizeIndex] = calculateAverageTime(timesSorted);
        System.out.print("sorted + sortIndex " + sortMethodIndex + " + array size = " + arraySizeIndex + " === ");
        System.out.println(timesForGraphs[1][sortMethodIndex][arraySizeIndex]);


        timesForGraphs[2][sortMethodIndex][arraySizeIndex] = calculateAverageTime(timesReverseSorted);
        System.out.print("rev sorted + sortIndex " + sortMethodIndex + " + array size = " + arraySizeIndex + " === ");
        System.out.println(timesForGraphs[2][sortMethodIndex][arraySizeIndex]);

    }

    private void checkSorted(int[] arr, int a, String b, int c) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.println("ERROR SORTLU DEGIL" + "sort method index = " + a + " " + b  + " array size index = " + c);
                break;
            }
        }
    }

    private void checkReverse(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                System.out.println("not reverse");
            }
        }
    }

    public void run(int runTime) {
        for (int i = 0; i < sortMethods.length; i++) {
            for (int j = 0 ; j < arraysToSort.length; j++) {
                runSort(i, runTime, j);
            }
        }
    }

    public void reverseArray(int[] arr) {
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    public double[][][] getTimesForGraphs() {
        return timesForGraphs;
    }

}