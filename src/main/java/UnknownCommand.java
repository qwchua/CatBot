public class UnknownCommand extends Command{
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        ui.showMessage("Cat dont understand what human wants");
    }
}
