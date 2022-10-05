import java.io.IOException;

enum TaskType {
    NONE,
    EVENT,
    DEADLINE,
    TODO;
}

public class AddCommand extends Command{
    private String description;
    private TaskType taskType;
    private String date;

    public AddCommand(TaskType tt, String description){
        this.taskType = tt;
        this.description = description;
    }

    public AddCommand(TaskType tt, String description, String date){
        this.taskType = tt;
        this.description = description;
        this.date = date;
    }
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        if(this.taskType.equals(TaskType.DEADLINE)){
            t.addTask(new Deadline(description,date));
        } else if (this.taskType.equals(TaskType.EVENT)) {
            t.addTask(new Event(description,date));
        } else if (this.taskType.equals(TaskType.TODO)) {
            t.addTask(new ToDo(description));
        }
        ui.showMessage("Got it. Cat added this task: \n" + description);
        ui.showMessage("Now human have " + t.getSize() + " tasks in the list");

        try{
            s.save(t);
        } catch (IOException ie) {
            System.out.println("Error saving!!");
        }
    }
}
