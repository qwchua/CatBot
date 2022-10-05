import java.util.Scanner;

public class Ui {
    static Scanner in = new Scanner(System.in);

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

    public void showLine() {
        System.out.println("_______________________________");
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showMessage(String s){
        System.out.println(s);
    }

    public  void showGoodbye(){
        System.out.println("K THX BYE");
    }
}
