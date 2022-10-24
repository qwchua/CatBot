package catbot.command;

import catbot.*;
import catbot.task.*;

import java.io.IOException;
import java.time.LocalDateTime;


public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        tl.addTask(task);
        ui.showMessage("Got it. Cat added this task: \n" + task.getTaskName());
        ui.showMessage("Now human have " + tl.getSize() + " tasks in the list");

        try{
            s.save(tl);
        } catch (IOException ie) {
            System.out.println("Error saving!!");
        }
    }
}
