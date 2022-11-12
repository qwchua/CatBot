package catbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task type of Deadline
 * Deadline has a by date
 */
public class Deadline extends Task {
    private LocalDateTime byDateTime;
    private TaskType taskType = TaskType.DEADLINE;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    public Deadline(boolean isDone, String description, LocalDateTime byDateTime) {
        this.isDone = isDone;
        this.name = description;
        this.byDateTime = byDateTime;
    }

    /**
     * Returns the specified Deadline by Date.
     *
     * @return Deadline's By Date
     */
    public LocalDateTime getBy() {
        return byDateTime;
    }

    /**
     * Returns the specified Deadline Tasktype.
     *
     * @return Deadline's Type
     */
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
