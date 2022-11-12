package catbot.task;

import catbot.exceptions.DuplicatedTaskException;
import catbot.utils.Utils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * TaskList acts as a manager to all the tasks
 * a TaskList has a ArrayList of tasks and a HashSet to check for duplicated Tasks
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Set<String> uniques = new HashSet<>();

    public TaskList(){

    }

    public TaskList(ArrayList<Task> tasks) throws DuplicatedTaskException {
        for(Task t : tasks){
            addTask(t);
        }
    }

    /**
     * Returns total number of tasks
     * @return total number of tasks
     */
    public Integer getSize(){
        return tasks.size();
    }

    /**
     * Adds a task to Tasklist
     * If the position is unset, NaN is returned.
     * If task already exists, Exception will be thown
     * @param t Task to be added
     * @throws DuplicatedTaskException If duplicated task.
     */

    public void addTask(Task t) throws DuplicatedTaskException {
        if(checkIfTaskExists(t)){
            throw new DuplicatedTaskException("Cat detected a duplicated task and did not add this task" + System.lineSeparator() + "pls try again!");
        }
        tasks.add(t);
        uniques.add(getTaskHash(t));
    }

    /**
     * Checks if task already exist in the tasklist
     *
     * @param t Task to be checked
     * @return boolean
     */
    private boolean checkIfTaskExists(Task t) {
        String hash = getTaskHash(t);
        if (uniques.contains(hash)) {
            return true;
        }
        return false;
    }

    /**
     * Deletes task from tasklist
     * Removes task hash from uniques
     * @param taskNum index of the tasklist to be deleted

     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
        Task t = getTask(taskNum);
        String hash = getTaskHash(t);
        uniques.remove(hash);
    }

    /**
     * Search for a task in the tasklist given a query
     * If it task name matches the query, task will be added into a new tasklist
     * will return a tasklist as a result
     *
     * @param query String to match
     * @return Tasklist
     */
    public TaskList searchTask(String query){
        TaskList output = new TaskList();

        for(Task t : tasks){
            if(t.getTaskName().contains(query)){
                try{
                    output.addTask(t);
                } catch (DuplicatedTaskException dte){
                }
            }
        }

        return output;
    }

    /**
     * Mark selected task as done
     *
     * @param taskNum index of the task list
     */
    public void markDone(Integer taskNum) {
        tasks.get(taskNum).setToDone();
    }

    /**
     * Unmark done for selected task
     *
     * @param taskNum index of the task list
     */
    public void unmarkDone(Integer taskNum) {
        tasks.get(taskNum).setToUndone();
    }

    /**
     * Returns a task from the tasklist, given the index of the task in tasklist
     * If the position is unset, NaN is returned.
     *
     * @param taskNum index of the task list
     * @return Task
     */
    public Task getTask(Integer taskNum){
        return tasks.get(taskNum);
    }

    /**
     * Returns all the task from the tasklist

     * @return ArayList of Tasks
     */
    public ArrayList<Task> getTasksList(){
        return tasks;
    }

    /**
     * Returns a hash of a selected task, hash function is SHA1
     * Example: if task is a deadline, we will hash tasktype+taskname+byDate
     * Example task: Deadline(Deadline, "Return book", "2019-12-02T19:01")
     * Example hash: hash("DeadlineReturn book2019-12-02T19:01")
     * Example returned hash of above Deadline: e38980cf6f048ed020c384cff164125287aa60b4
     *
     * @param t Task to be hashed
     * @return hash String of the task
     */
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
