package catbot;

import catbot.command.*;

public class Parser {
    public static Command parse(String s) throws CatBotException{

        if(s.equals("bye")){
            return new ExitCommand();
        }

        else if(s.equals("list")){
            return new ListCommand();
        }

        else if(s.startsWith("mark")){
            Integer selectedTask = Character.getNumericValue(s.charAt(5));
            return new MarkCommand(selectedTask);
        }

        else if(s.startsWith("unmark")){
            Integer selectedTask = Character.getNumericValue(s.charAt(7));
            return new UnmarkCommand(selectedTask);
        }

        else if(s.startsWith("deadline")){
            //sample deadline string => deadline return book /by Sunday
            try{
                if (s.length() < 10) {
                    throw new CatBotException("Human, deadline description cannot be empty!");
                }

                Integer indexOfBy = s.indexOf("/by");

                if(indexOfBy == -1) {
                    throw new CatBotException("Human, deadline missing /by)");
                }

                if(s.length() == indexOfBy+3){
                    throw new CatBotException("Human, deadline must have Date/Time!");
                }

                String deadlineName = s.substring(9,indexOfBy-1);
                String byDate = s.substring(indexOfBy+4, s.length());

                if(byDate.length() == 0) {
                    throw new CatBotException("Human, deadline must have Date/Time!");
                }

                return new AddCommand(TaskType.DEADLINE, deadlineName, byDate);
            }
            catch (CatBotException cbe) {
                System.out.println(cbe.getMessage());
            }
        }

        else if(s.startsWith("todo")){
            //sample todo string ==> todo borrow book
            try{
                if (s.length() < 6) {
                    throw new CatBotException("Human, todo description cannot be empty!");
                }

                String toDoName = s.substring(5);

                return new AddCommand(TaskType.TODO, toDoName);
            }
            catch (CatBotException cbe) {
                System.out.println(cbe.getMessage());
            }
        }

        else if(s.startsWith("event")){
            //sample event string => event project meeting /at Mon 2-4pm
            try{
                if (s.length() < 8) {
                    throw new CatBotException("Human, event description cannot be empty!");
                }

                Integer indexOfAt = s.indexOf("/at");

                if(indexOfAt == -1) {
                    throw new CatBotException("Human, event must have at Date/Time!");
                }

                String eventName = s.substring(6,indexOfAt-1);
                String atDate = s.substring(indexOfAt+4, s.length());

                if(atDate.length() == 0) {
                    throw new CatBotException("Human, event must have Date/Time!");
                }

                return new AddCommand(TaskType.EVENT, eventName, atDate);
            }
            catch (CatBotException cbe) {
                System.out.println(cbe.getMessage());
            }
        }

        else if (s.startsWith("delete")){
            Integer selectedTask = Character.getNumericValue(s.charAt(7));
            return new DeleteCommand(selectedTask);
        }

        else if(s.startsWith("search")){
            //sample todo string ==> todo borrow book
            try{
                if (s.length() < 6) {
                    throw new CatBotException("Human, search cannot be empty!");
                }

                String query = s.substring(7);

                return new SearchCommand(query);
            }
            catch (CatBotException cbe) {
                System.out.println(cbe.getMessage());
            }
        }

        return new UnknownCommand();
    }
}
