import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Planner {

    public final Task[] taskArray;
    public final Integer[] compatibility;
    public final Double[] maxWeight;
    public final ArrayList<Task> planDynamic;
    public final ArrayList<Task> planGreedy;

    public Planner(Task[] taskArray) {

        // Should be instantiated with an Task array
        // All the properties of this class should be initialized here

        this.taskArray = taskArray;
        this.compatibility = new Integer[taskArray.length];
        maxWeight = new Double[taskArray.length];

        this.planDynamic = new ArrayList<>();
        this.planGreedy = new ArrayList<>();
    }

    /**
     * @param index of the {@link Task}
     * @return Returns the index of the last compatible {@link Task},
     * returns -1 if there are no compatible {@link Task}s.
     */
    public int binarySearch(int index) {
        String startTime = taskArray[index].getStartTime();
        int left = 0;
        int right = index - 1;

        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            String midFinishTime = taskArray[mid].getFinishTime();

            if (compareTimeStrings(startTime, midFinishTime) >= 0) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }


    /**
     * {@link #compatibility} must be filled after calling this method
     */
    public void calculateCompatibility() {
        for (int i = 0; i < compatibility.length; i++) {
            compatibility[i] = binarySearch(i);
        }
    }


    /**
     * Uses {@link #taskArray} property
     * This function is for generating a plan using the dynamic programming approach.
     * @return Returns a list of planned tasks.
     */
    public ArrayList<Task> planDynamic() {
        calculateCompatibility();

        System.out.println("Calculating max array");
        System.out.println("---------------------");
        calculateMaxWeight(taskArray.length - 1);
        System.out.println();

        System.out.println("Calculating the dynamic solution");
        System.out.println("--------------------------------");
        solveDynamic(taskArray.length - 1);
        System.out.println();

        System.out.println("Dynamic Schedule");
        System.out.println("----------------");
        for (Task task : planDynamic) {
            System.out.println("At" + " " + task.getStartTime() + ", " + task.getName() + ".");
        }

        return planDynamic;
    }

    /**
     * {@link #planDynamic} must be filled after calling this method
     */
    public void solveDynamic(int i) {
        System.out.println("Called" + " " + "solveDynamic" + "(" + i + ")");

        if (i == 0) {
            planDynamic.add(taskArray[i]);
            return;
        }

        if (maxWeight[i] > maxWeight[i - 1]) {
            if (compatibility[i] != -1) {
                solveDynamic(compatibility[i]);
            }

            planDynamic.add(taskArray[i]);
        } else {
            solveDynamic(i - 1);
        }
    }

    /**
     * {@link #maxWeight} must be filled after calling this method
     */
    /* This function calculates maximum weights and prints out whether it has been called before or not  */
    public Double calculateMaxWeight(int i) {
        System.out.println("Called" + " " + "calculateMaxWeight" + "(" + i + ")");
        if (i < 0) {
            return 0.0;
        }

        if (maxWeight[i] != null && i != 0) {
            return maxWeight[i];
        }

        maxWeight[i] = Math.max(taskArray[i].getWeight() + calculateMaxWeight(compatibility[i]), calculateMaxWeight(i - 1));
        return maxWeight[i];
    }

    /**
     * {@link #planGreedy} must be filled after calling this method
     * Uses {@link #taskArray} property
     *
     * @return Returns a list of scheduled assignments
     */

    /*
     * This function is for generating a plan using the greedy approach.
     * */
    public ArrayList<Task> planGreedy() {
        Task lastChosenTask = taskArray[0];
        planGreedy.add(lastChosenTask);
        for (int i = 1; i < taskArray.length; i++) {
            if (compareTimeStrings(taskArray[i].getStartTime(), lastChosenTask.getFinishTime()) >= 0) {
                lastChosenTask = taskArray[i];
                planGreedy.add(lastChosenTask);
            }
        }

        System.out.println("Greedy Schedule");
        System.out.println("---------------");
        for (Task task : planGreedy) {
            System.out.println("At" + " " + task.getStartTime() + ", " + task.getName() + ".");
        }

        return planGreedy;
    }

    // returns 1 if time1 > time2
    private int compareTimeStrings(String time1, String time2) {
        LocalTime firstTime = LocalTime.parse(time1);
        LocalTime secondTime = LocalTime.parse(time2);

        return firstTime.compareTo(secondTime);
    }
}
