package catbot.task;

import catbot.CatBotException;
import catbot.TaskType;
import catbot.utils.Utils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Set<String> uniques = new HashSet<>();

    public TaskList(){

    }

    public TaskList(ArrayList<String> list) {
        for(String s : list){
            String[] arr = s.split(" \\| ");
            boolean isDone;
            if(arr[1].equals("1")){
                isDone = true;
            } else {
                isDone = false;
            }
            if(arr[0].equals("T")){
                tasks.add(new ToDo(isDone, arr[2]));
            } else if (arr[0].equals("D")) {
                tasks.add(new Deadline(isDone, arr[2], LocalDateTime.parse(arr[3])));
            } else if (arr[0].equals("E")) {
                tasks.add(new Event(isDone, arr[2], LocalDateTime.parse(arr[3]), LocalDateTime.parse(arr[4])));
            }
        }
    }

    public Integer getSize(){
        return tasks.size();
    }

    public void addTask(Task t) throws CatBotException {
        if(checkIfTaskExists(t)){
            throw new CatBotException("Cat detected a duplicated task and did not add this task" + System.lineSeparator() + "pls try again!");
        }
        tasks.add(t);
        uniques.add(getTaskHash(t));
    }

    private boolean checkIfTaskExists(Task t) {
        String hash = getTaskHash(t);
        if (uniques.contains(hash)) {
            return true;
        }
        return false;
    }

    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
        Task t = getTask(taskNum);
        String hash = getTaskHash(t);
        uniques.remove(hash);
    }

    public TaskList searchTask(String query){
        TaskList output = new TaskList();

        for(Task t : tasks){
            if(t.getTaskName().contains(query)){
                try{
                    output.addTask(t);
                } catch (CatBotException cbe){
                }
            }
        }

        return output;
    }

    public void markDone(Integer taskNum) {
        tasks.get(taskNum).setToDone();
    }

    public void unmarkDone(Integer taskNum) {
        tasks.get(taskNum).setToUndone();
    }

    public Task getTask(Integer taskNum){
        return tasks.get(taskNum);
    }

    public String getTaskHash(Task t){
        String hash;

        try {
            if(t.getTaskType().equals(TaskType.DEADLINE)){
                Deadline d = (Deadline)t;

                TaskType type = d.getTaskType();
                String name = d.getTaskName();
                LocalDateTime by = d.getBy();

                hash = Utils.hashString(type+name+by);

            } else if (t.getTaskType().equals(TaskType.EVENT)) {
                Event e = (Event)t;

                TaskType type = e.getTaskType();
                String name = e.getTaskName();
                LocalDateTime from = e.getFromDateTime();
                LocalDateTime to = e.getToDateTime();

                hash = Utils.hashString(type+name+from+to);
            } else {
                ToDo td = (ToDo)t;

                TaskType type = td.getTaskType();
                String name = td.getTaskName();

                hash = Utils.hashString(type+name);
            }
            return hash;
        } catch (NoSuchAlgorithmException nsae) {
            return null;
        }
    }

    @Override
    public String toString() {
        String s ="";
        for(int i = 0; i < tasks.size(); i++){
            s += i + "." + tasks.get(i) + "\n";
        }
        return s;
    }
}
