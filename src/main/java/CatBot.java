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
                Integer indexOfBy = line.indexOf("/by");
                String deadlineName = line.substring(9,indexOfBy-1);
                String byDate = line.substring(indexOfBy+4, line.length());

                Deadline d = new Deadline(deadlineName, byDate);
                Task.addTask(d);
            }

            else if(line.startsWith("todo")){
                //sample todo string ==> todo borrow book
                String toDoName = line.substring(5,line.length());

                ToDo t = new ToDo(toDoName);
                Task.addTask(t);
            }

            else if(line.startsWith("event")){
                //sample event string => event project meeting /at Mon 2-4pm
                Integer indexOfAt = line.indexOf("/at");
                String eventName = line.substring(6,indexOfAt-1);
                String atDate = line.substring(indexOfAt+4, line.length());

                Event e = new Event(eventName, atDate);
                Task.addTask(e);
            }
        }
    }
}
