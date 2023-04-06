public class LinearSearch implements ISearch{
    @Override
    public int search(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        System.out.println("Cannot find");
        return -1;
    }
}
