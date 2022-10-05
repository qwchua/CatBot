public class Deadline extends Task{
    private String by;
    private TaskType taskType = TaskType.DEADLINE;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        this.isDone = isDone;
        this.name = description;
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
