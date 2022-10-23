package catbot;

import catbot.command.Command;
import catbot.task.TaskList;

import java.io.IOException;

public class CatBot {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public CatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            System.out.println("File loaded!");
        } catch (CatBotException c) {
            //ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException f){
            System.out.println("Error finding file");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CatBotException c) {
                //ui.showError(c.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new CatBot("data/tasks.txt").run();
    }
}
