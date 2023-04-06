public class BinarySearch implements ISearch{
    @Override
    public int search(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == num) {
                return mid;
            }

            if (num > arr[mid]) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        System.out.println("cannot find");
        return -1;
    }
}
