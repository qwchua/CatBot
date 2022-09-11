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

        Scanner in = new Scanner(System.in);

        while(true){
            String line;
            System.out.println("Type your message: ");
            line = in.nextLine();

            if(line.equals("bye")){
                System.out.println("K THX BYE");
                return;
            }

            else if(line.equals("list")){
                Task.printAllTasks();
                continue;
            }

            else if(line.startsWith("mark")){
                int selectedTask = Character.getNumericValue(line.charAt(5));
                Task.markDone(selectedTask);
                continue;
            }

            else if(line.startsWith("unmark")){
                int selectedTask = Character.getNumericValue(line.charAt(7));
                Task.unmarkDone(selectedTask);
                continue;
            }

            else if(line.startsWith("deadline")){
                //sample deadline string => deadline return book /by Sunday
                try{
                    if (line.length() < 10) {
                        throw new CatBotException("Human, deadline description cannot be empty!");
                    }

                    Integer indexOfBy = line.indexOf("/by");

                    if(indexOfBy == -1) {
                        throw new CatBotException("Human, deadline missing /by)");
                    }

                    if(line.length() == indexOfBy+3){
                        throw new CatBotException("Human, deadline must have Date/Time!");
                    }

                    String deadlineName = line.substring(9,indexOfBy-1);
                    String byDate = line.substring(indexOfBy+4, line.length());

                    if(byDate.length() == 0) {
                        throw new CatBotException("Human, deadline must have Date/Time!");
                    }

                    Deadline d = new Deadline(deadlineName, byDate);
                    Task.addTask(d);
                }
                catch (CatBotException cbe) {
                    System.out.println(cbe.getMessage());
                }
            }

            else if(line.startsWith("todo")){
                //sample todo string ==> todo borrow book
                try{
                    if (line.length() < 6) {
                        throw new CatBotException("Human, todo description cannot be empty!");
                    }

                    String toDoName = line.substring(5,line.length());
                    ToDo t = new ToDo(toDoName);
                    Task.addTask(t);
                }
                catch (CatBotException cbe) {
                    System.out.println(cbe.getMessage());
                }
            }

            else if(line.startsWith("event")){
                //sample event string => event project meeting /at Mon 2-4pm
                try{
                    if (line.length() < 8) {
                        throw new CatBotException("Human, event description cannot be empty!");
                    }

                    Integer indexOfAt = line.indexOf("/at");

                    if(indexOfAt == -1) {
                        throw new CatBotException("Human, event must have at Date/Time!");
                    }

                    String eventName = line.substring(6,indexOfAt-1);
                    String atDate = line.substring(indexOfAt+4, line.length());

                    if(atDate.length() == 0) {
                        throw new CatBotException("Human, event must have Date/Time!");
                    }

                    Event e = new Event(eventName, atDate);
                    Task.addTask(e);

                }
                catch (CatBotException cbe) {
                    System.out.println(cbe.getMessage());
                }
            }

            else {
                System.out.println("Cat dont understand what human wants");
            }
        }
    }
}
