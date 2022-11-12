package catbot.command;

import catbot.*;
import catbot.task.Task;
import catbot.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    Integer taskNum;

    public DeleteCommand(Integer taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        if (taskNum < 0 || taskNum > t.getSize() - 1) {
            ui.showError("Human, Your task number should be not be below 0 or more than " + (t.getSize() - 1));
            return;
        }

        Task task = t.getTask(taskNum);
        t.deleteTask(taskNum);
        ui.showMessage("Got it. Cat removed this task: \n" + task.getTaskName());
        ui.showMessage("Now human have " + t.getSize() + " tasks in the list");

        try {
            s.save(t);
        } catch (IOException ie) {
            System.out.println("Error saving");
        }
    }
}
