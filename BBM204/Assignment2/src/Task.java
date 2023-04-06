import java.time.LocalTime;

public class Task implements Comparable {
    public String name;
    public String start;
    public int duration;
    public int importance;
    public boolean urgent;

    /*
        Getter methods
     */
    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getImportance() {
        return this.importance;
    }

    public boolean isUrgent() {
        return this.urgent;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
        LocalTime startTime = LocalTime.parse(getStartTime());
        LocalTime finishTime = startTime.plusHours(getDuration());
        return finishTime.toString();
    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() {
        return (getImportance() * ((isUrgent()) ? 2000.0 : 1.0)) / getDuration();
    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Task)) {
            throw new ClassCastException("Cannot compare Task object with non-Task object");
        }

        Task otherTask = (Task) o;
        LocalTime thisFinishTime = LocalTime.parse(getFinishTime());
        LocalTime otherFinishTime = LocalTime.parse(otherTask.getFinishTime());

        return thisFinishTime.compareTo(otherFinishTime);
    }
}
