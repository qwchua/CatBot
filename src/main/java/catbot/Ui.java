package catbot;

import java.util.Scanner;

/**
 * Represents a Ui object, main function is to deal with interactions with the user
 */
public class Ui {
    static Scanner in = new Scanner(System.in);

    /**
     * Show Cat and greet user.
     */
    public void showWelcome() {
        String logo = "          ██          ██                    \n" +
                "        ██░░██      ██░░██                  \n" +
                "        ██░░▒▒██████▒▒░░██                  \n" +
                "      ██▒▒░░░░▒▒▒▒▒▒░░░░▒▒██                \n" +
                "      ██░░░░░░░░░░░░░░░░░░██                \n" +
                "    ██▒▒░░░░░░░░░░░░░░░░░░▒▒██              \n" +
                "    ██░░░░██░░░░██░░░░██░░░░██              \n" +
                "    ██░░░░░░░░██░░██░░░░░░░░██              \n" +
                "  ██▒▒░░░░░░░░░░░░░░░░░░░░░░▒▒██            \n" +
                "  ██▒▒░░░░░░░░░░░░░░░░░░░░░░▒▒██            \n" +
                "  ██░░░░░░░░░░░░░░░░░░░░░░░░░░██            \n" +
                "  ██░░░░░░░░░░░░░░░░░░░░░░░░░░██            \n" +
                "██▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒██          \n" +
                "██▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒██          \n" +
                "██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██    ████  \n" +
                "██▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒██  ██░░░░██\n" +
                "██▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒██    ██▒▒██\n" +
                "██░░░░░░██░░░░░░░░░░░░░░██░░░░░░██    ██░░██\n" +
                "██▒▒░░░░██░░██░░░░░░██░░██░░░░▒▒██████░░░░██\n" +
                "  ██▒▒░░██░░██░░░░░░██░░██░░▒▒██░░░░▒▒░░██  \n" +
                "    ██████░░██████████░░████████████████    \n" +
                "        ██████      ██████                  \n";
        System.out.println(logo);
        System.out.println("Hello human" + System.lineSeparator());
        System.out.println("What can cat do for human?" + System.lineSeparator());
    }

    /**
     * Print a line on console
     */
    public void showLine() {
        System.out.println("_______________________________");
    }

    /**
     * Returns the next line of the command as a String
     *
     * @return next line
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * show message s in console
     *
     * @param s String to show on console
     */
    public void showMessage(String s) {
        System.out.println(s);
    }

    /**
     * show error message s in console
     *
     * @param s String to show on console
     */
    public void showError(String s) {
        System.out.println(s);
    }

    /**
     * show duplicated task detected error message in console
     */
    public void detectedDuplicateTask() {
        System.out.println("Cat detected a duplicated task and did not add this task" + System.lineSeparator() + "pls try again!");
    }

    /**
     * show loading tasklist loading error from harddisk
     */
    public void showLoadingError() {
        System.out.println("Cat cannot load from data/tasks.txt, So Cat will make new instance");
    }

    /**
     * show goodbye message when exiting
     */
    public void showGoodbye() {
        System.out.println("K THX BYE");
    }
}
