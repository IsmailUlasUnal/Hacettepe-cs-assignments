import java.util.Stack;
public class QuickSort implements ISort{
    @Override
    public long sort(int[] arr) {
        long startTime = System.nanoTime();
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();
            int pivotIndex = partition(arr, start, end);
            if (pivotIndex - 1 > start) {
                stack.push(start);
                stack.push(pivotIndex - 1);
            }
            if (pivotIndex + 1 < end) {
                stack.push(pivotIndex + 1);
                stack.push(end);
            }
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    private int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }
}
