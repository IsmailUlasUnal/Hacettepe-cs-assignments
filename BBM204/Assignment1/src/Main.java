import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

class Main {
    public static void main(String args[]) throws IOException {
        // create size of arrays
        int[] sizeOfArrs = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};

        // read and array from command line
        CSVReader reader = new CSVReader();
        int max = sizeOfArrs[0];
        for (int i = 1; i < sizeOfArrs.length; i++) {
            max = Math.max(sizeOfArrs[i], max);
        }

        int[] originalArray = reader.readInteger("TrafficFlowDataset.csv", max);

        // create sort and search methods for managers
        ISort[] sortMethods = {new SelectionSort(), new QuickSort(), new BucketSort()};
        ISearch[] searchMehtods = {new LinearSearch(), new BinarySearch()};

        // create and managers for comparison and run them
        SortManager sortManager = new SortManager(sizeOfArrs, sortMethods, originalArray);
        SearchManager searchManager = new SearchManager(sizeOfArrs, searchMehtods, originalArray);
        searchManager.run(1000);
        System.out.println("second time for fixing first array search time");
        searchManager.run(1000);
        sortManager.run(10);

        // draw graph for results
        ChartManager cm = new ChartManager();
        cm.drawChartForSort(sortManager.getTimesForGraphs());
        cm.drawChartForSearch(searchManager.getTimesForGraphs());
    }
}
