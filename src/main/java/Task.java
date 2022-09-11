import java.util.ArrayList;
public class Task {
    static ArrayList<Task> tasks = new ArrayList<Task>();

    //static Task[] tasks = new Task[100];
    //static int numOfTasks = 0;
    private String name;
    private boolean isDone;

    static public void printAllTasks() {
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(i+1 + "." + tasks.get(i));
        }
    }
    public Task() {
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    static public void addTask(Task t){
        tasks.add(t);
        System.out.println("Got it. Cat added this task: \n" + t);
        System.out.println("Now human have " + tasks.size() + " tasks in the list");
    }

    static public void deleteTask(int taskNum){
        int taskIndex = taskNum-1;
        Task t = Task.tasks.get(taskIndex);
        tasks.remove(taskIndex);
        System.out.println("Got it. Cat removed this task: \n" + t);
        System.out.println("Now human have " + tasks.size() + " tasks in the list");
    }

    public String getTaskName() {
        return name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    static public void markDone(int taskNum) {
        int taskIndex = taskNum-1;
        tasks.get(taskIndex).isDone = true;
        System.out.println("Good job human, Cat marked your task as done");
        System.out.println("[X] " + tasks.get(taskIndex).getTaskName());
    }

    static public void unmarkDone(int taskNum) {
        int taskIndex = taskNum-1;
        tasks.get(taskIndex).isDone = false;
        System.out.println("Lmeow, Cat unmarked your task as done, pls make up your mind human");
        System.out.println("[ ] " + tasks.get(taskIndex).getTaskName());
    }

    @Override
    public String toString() {
        String isCompleted;

        if(this.getIsDone()){
            isCompleted = "X";
        }
        else {
            isCompleted = " ";
        }
        return ("[" + isCompleted + "] " + this.getTaskName() );
    }
}
