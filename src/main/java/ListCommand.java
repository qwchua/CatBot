public class ListCommand extends Command{
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        ui.showMessage(t.toString());
    }
}
