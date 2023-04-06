
public class SelectionSort implements ISort{
    public  long sort(int[] arr) {
        long startTime = System.nanoTime();
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) { // found min
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
