import java.util.Scanner;

public class CatBot {
    public static void main(String[] args) {
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

        Request[] requests = new Request[100];
        int numOfRequest = 0;

        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            System.out.println("Type your message: ");
            line = in.nextLine();
            Request r = new Request(line);

            if(r.getRequestName().equals("bye")){
                System.out.println("K THX BYE");
                return;
            }

            if(r.getRequestName().equals("list")){
                for(int i = 0; i < numOfRequest; i++){
                    System.out.println(i+1 + ". " + requests[i].getRequestName());
                }
                continue;
            }

            System.out.println("added: " + r.getRequestName());
            requests[numOfRequest] = r;
            numOfRequest++;
        }
    }
}
