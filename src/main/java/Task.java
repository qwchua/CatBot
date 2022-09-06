public class Task {
    static Task[] tasks = new Task[100];
    static int numOfTasks = 0;
    private String name;
    private boolean isDone;

    static public void printAllTasks() {
        for(int i = 0; i < numOfTasks; i++){
            String printXorSpace;
            if(tasks[i].getIsDone() == true){
                printXorSpace = "X";
            }
            else {
                printXorSpace = " ";
            }
            System.out.println(i+1 + ".[" + printXorSpace + "] " + tasks[i].getTaskName());
        }
    }

    static public void addTask(Task t){
        tasks[numOfTasks] = t;
        numOfTasks++;
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getTaskName() {
        return name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    static public void markDone(int taskNum) {
        int taskIndex = taskNum-1;
        tasks[taskIndex].isDone = true;
        System.out.println("Good job human, Cat marked your task as done");
        System.out.println("[X] " + tasks[taskIndex].getTaskName());
    }

    static public void unmarkDone(int taskNum) {
        int taskIndex = taskNum-1;
        tasks[taskIndex].isDone = false;
        System.out.println("Lmeow, Cat unmarked your task as done, pls make up your mind human");
        System.out.println("[ ] " + tasks[taskIndex].getTaskName());
    }
}
