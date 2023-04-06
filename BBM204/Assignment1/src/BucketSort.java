import java.util.ArrayList;
import java.util.Collections;
public class BucketSort implements ISort{
    @Override
    public long sort(int[] arr) {
        long startTime = System.nanoTime();

        int numOfBuckets = (int) Math.sqrt(arr.length);
        ArrayList<Integer>[] buckets = new ArrayList[numOfBuckets];

        for (int i = 0; i < numOfBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        for (int element : arr) {
            int bucketIndex = hash(element, max, numOfBuckets);
            buckets[bucketIndex].add(element);
        }

        int index = 0;
        for (int i = 0; i < numOfBuckets; i++) {
            Collections.sort(buckets[i]);
            for (int element : buckets[i]) {
                arr[index++] = element;
            }
        }
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    private int hash(double i, double max, int numOfBuckets) {
        return (int) (i / max * (numOfBuckets - 1));
    }
}
