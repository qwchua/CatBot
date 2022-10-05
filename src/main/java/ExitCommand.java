public class ExitCommand extends Command {
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        exit = true;
    }
}
