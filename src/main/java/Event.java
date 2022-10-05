public class Event extends Task {
    private String at;
    private TaskType taskType = TaskType.EVENT;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(boolean isDone, String description, String at) {
        this.isDone = isDone;
        this.name = description;
        this.at = at;
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    public String getAt(){
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
