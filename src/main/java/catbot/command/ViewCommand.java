package catbot.command;

import catbot.exceptions.DuplicatedTaskException;
import catbot.Storage;
import catbot.task.TaskType;
import catbot.task.Deadline;
import catbot.task.Event;
import catbot.task.Task;
import catbot.task.TaskList;
import catbot.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewCommand extends Command {
    LocalDate dateToView;

    public ViewCommand(LocalDate dateToView) {
        this.dateToView = dateToView;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        ArrayList<Task> arrOfTasks = t.getTasksList();
        TaskList results = new TaskList();
        LocalDate currentTaskLd;

        for (Task task : arrOfTasks) {
            if (task.getTaskType() == TaskType.DEADLINE) {
                Deadline d = (Deadline) task;
                currentTaskLd = d.getBy().toLocalDate();
            } else if (task.getTaskType() == TaskType.EVENT) {
                Event e = (Event) task;
                currentTaskLd = e.getFromDateTime().toLocalDate();
            } else {
                continue;
            }

            if (currentTaskLd.compareTo(dateToView) == 0) {
                try {
                    results.addTask(task);
                } catch (DuplicatedTaskException dte) {
                }
            }

        }

        if (results.getSize() == 0) {
            ui.showMessage("Human have 0 task for " + dateToView);
            return;
        }

        ui.showMessage(results.toString());
    }
}
