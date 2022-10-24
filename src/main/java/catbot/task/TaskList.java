package catbot.task;

import catbot.TaskType;
import catbot.Utils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

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
                this.addTask(new ToDo(isDone, arr[2]));
            } else if (arr[0].equals("D")) {
                this.addTask(new Deadline(isDone, arr[2], LocalDateTime.parse(arr[3])));
            } else if (arr[0].equals("E")) {
                tasks.add(new Event(isDone, arr[2], arr[3]));
            }
        }
    }

    public Integer getSize(){
        return tasks.size();
    }

    public void addTask(Task t){
        tasks.add(t);
    }

    private boolean checkForDuplicateTask(Task t) throws NoSuchAlgorithmException {
        String hash;
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
        } else if (t.getTaskType().equals(TaskType.TODO)) {
            ToDo td = (ToDo)t;

            TaskType type = td.getTaskType();
            String name = td.getTaskName();

            hash = Utils.hashString(type+name);
        }
        return true;
    }

    public void deleteTask(int taskNum){
        tasks.remove(taskNum);
    }

    public TaskList searchTask(String query){
        TaskList output = new TaskList();

        for(Task t : tasks){
            if(t.getTaskName().contains(query)){
                output.addTask(t);
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

    @Override
    public String toString() {
        String s ="";
        for(int i = 0; i < tasks.size(); i++){
            s += i + "." + tasks.get(i) + "\n";
        }
        return s;
    }
}
