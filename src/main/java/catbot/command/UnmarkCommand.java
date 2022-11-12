package catbot.command;

import catbot.Storage;
import catbot.task.TaskList;
import catbot.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    Integer taskNum;

    public UnmarkCommand(Integer taskNum){
        this.taskNum = taskNum;
    }
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        if(taskNum < 0 || taskNum > t.getSize()-1 ){
            ui.showError("Human, Your task number should be not be below 0 or more than " + (t.getSize()-1) );
            return;
        }

        t.unmarkDone(taskNum);
        ui.showMessage("Lmeow, Cat unmarked your task as done, pls make up your mind human");
        ui.showMessage("[ ] " + t.getTask(taskNum).getTaskName());

        try{
            s.save(t);
        } catch (IOException ie) {
            System.out.println("Error saving");
        }
    }
}
