package catbot.task;

/**
 * Represents a piece of work that need to be done by the user A <code>Task</code> object corresponds to
 * a task represented by a name and if it is done/completed
 */
public class Task {
    protected String name;
    protected boolean isDone;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns the specified Task Name
     *
     * @return Task Name
     */
    public String getTaskName() {
        return name;
    }

    /**
     * Returns if the specified Task is done
     *
     * @return Boolean
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Set the specified Task to undone
     */
    public void setToUndone() {
        this.isDone = false;
    }

    /**
     * Set the specified Task to done
     */
    public void setToDone() {
        this.isDone = true;
    }

    public TaskType getTaskType() {
        return TaskType.NONE;
    }

    @Override
    public String toString() {
        String isCompleted;

        if (this.getIsDone()) {
            isCompleted = "X";
        } else {
            isCompleted = " ";
        }
        return ("[" + isCompleted + "] " + this.getTaskName());
    }
}
