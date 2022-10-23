package catbot;

import catbot.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    static private File f;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        String directoryName = "data/";
        File directory = new File(directoryName);

        if(! directory.exists()){
            directory.mkdir();
        }

        f = new File(filePath);

        try{
            if(!f.exists()){
                f.createNewFile();
            }else{
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> load() throws CatBotException, IOException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        return list;
    }

    public void save(TaskList tl) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int size = tl.getSize();
        for(int i = 0; i < size; i++){
            Task t = tl.getTask(i);
            int isDone;
            if(t.getIsDone()){
                isDone = 1;
            } else {
                isDone = 0;
            }

            if(t.getTaskType() == TaskType.TODO){
                ToDo td = (ToDo) t;
                fw.write("T | " + isDone + " | " + td.getTaskName());
            } else if (t.getTaskType() == TaskType.DEADLINE) {
                Deadline d = (Deadline)t;
                fw.write("D | " + isDone + " | " + d.getTaskName() + " | " + d.getBy());
            } else if (t.getTaskType() == TaskType.EVENT) {
                Event e = (Event)t;
                fw.write("E | " + isDone + " | " + e.getTaskName() + " | " + e.getAt());
                System.out.println("E | " + isDone + " | " + e.getTaskName() + " | " + e.getAt());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
