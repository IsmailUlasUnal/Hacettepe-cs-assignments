import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SearchManager {
    int[] sizeOfArrays;
    ISearch[] searchMethods;
    int[] originalArray;
    int[][] sortedArraysToSearch;
    int[][] unsortedArraysToSearch;
    double[][] timesForGraphs;

    SearchManager(int[] sizeOfArrays, ISearch[] searchMethods, int[] originalArray) {
        this.originalArray = originalArray;
        this.sizeOfArrays = sizeOfArrays;
        this.searchMethods = searchMethods;

        sortedArraysToSearch = new int[sizeOfArrays.length][];
        unsortedArraysToSearch = new int[sizeOfArrays.length][];

        for (int i = 0; i < sizeOfArrays.length; i++) {
            unsortedArraysToSearch[i] = Arrays.copyOf(originalArray, sizeOfArrays[i]);

            sortedArraysToSearch[i] = Arrays.copyOf(originalArray, sizeOfArrays[i]);
            Arrays.sort(sortedArraysToSearch[i]);


        }

        timesForGraphs = new double[3][]; // unsorted linear, sorted linear, sorted binary
        for (int i = 0; i < timesForGraphs.length; i++) {
            timesForGraphs[i] = new double[sizeOfArrays.length];
        }
    }

    private double calculateAverageTime(ArrayList<Long> times) {
        long totalTime = 0;
        for (double time: times) totalTime += time;
        return (double) totalTime / times.size();
    }

    private int randomNumber(int[] arr) {
        int randomIndex = (int)(Math.random() * arr.length);
        return arr[randomIndex];
    }

    private long calculateTime(int[] arr, ISearch searchMethod, int num) {
        long startTime = System.nanoTime();
        searchMethod.search(arr, num);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private void runSearch(int runTime, int arraySizeIndex) {
        ArrayList<Long> timesUnsortedLinear = new ArrayList<>();
        ArrayList<Long> timesSortedLinear = new ArrayList<>();
        ArrayList<Long> timesSortedBinary = new ArrayList<>();

        for (int i = 0; i < runTime; i++) {
            int randomNum = randomNumber(unsortedArraysToSearch[arraySizeIndex]);

            timesUnsortedLinear.add(calculateTime(unsortedArraysToSearch[arraySizeIndex],
                    searchMethods[0],
                    randomNum));

            timesSortedLinear.add(calculateTime(sortedArraysToSearch[arraySizeIndex],
                    searchMethods[0],
                    randomNum));

            timesSortedBinary.add(calculateTime(sortedArraysToSearch[arraySizeIndex],
                    searchMethods[1],
                    randomNum));
        }

        timesForGraphs[0][arraySizeIndex] = calculateAverageTime(timesUnsortedLinear);
        System.out.println("linear unsorted time for " + arraySizeIndex + " " + timesForGraphs[0][arraySizeIndex]);

        timesForGraphs[1][arraySizeIndex] = calculateAverageTime(timesSortedLinear);
        System.out.println("linear sorted time for " + arraySizeIndex + " " +  timesForGraphs[1][arraySizeIndex]);

        timesForGraphs[2][arraySizeIndex] = calculateAverageTime(timesSortedBinary);
        System.out.println("binary sorted time for " + arraySizeIndex + " " + timesForGraphs[2][arraySizeIndex]);
    }


    public void run(int runTime) {
        for (int i = 0 ; i < unsortedArraysToSearch.length; i++) {
            runSearch(runTime, i);
        }
    }

    public double[][] getTimesForGraphs() {
        return timesForGraphs;
    }


}
