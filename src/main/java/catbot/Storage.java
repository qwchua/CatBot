package catbot;

import catbot.exceptions.FileCorruptedException;
import catbot.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a Storage object, main function is to save and load tasks into hard drive
 */
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
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a tasklist loaded from harddisk
     * @return a list of tasks
     * @throws FileCorruptedException If file is corrupted
     * @throws IOException If I/O error occurs
     */
    public ArrayList<Task> load() throws FileCorruptedException, IOException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] arr = currentLine.split(" \\| ");

            for(String column : arr){
                if (column.equals("null")) {
                    throw new FileCorruptedException("tasks.txt is corrupted");
                }
                assert column != null : "Null values should not be in Tasklist";
            }

            boolean isDone;

            if(arr[1].equals("1")){
                isDone = true;
            } else {
                isDone = false;
            }

            if(arr[0].equals("T")){
                listOfTasks.add(new ToDo(isDone, arr[2]));
            } else if (arr[0].equals("D")) {
                listOfTasks.add(new Deadline(isDone, arr[2], LocalDateTime.parse(arr[3])));
            } else if (arr[0].equals("E")) {
                listOfTasks.add(new Event(isDone, arr[2], LocalDateTime.parse(arr[3]), LocalDateTime.parse(arr[4])));
            }
        }
        return listOfTasks;
    }

    /**
     * Saves tasklist to harddisk
     * @param tl tasklist to be saved
     * @throws IOException If I/O error occurs
     */
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
                assert d.getTaskName() != null :"Deadline Name should not be null";
                assert d.getBy() != null :"Deadline by Date should not be null";

                fw.write("D | " + isDone + " | " + d.getTaskName() + " | " + d.getBy());
            } else if (t.getTaskType() == TaskType.EVENT) {
                Event e = (Event)t;
                assert e.getTaskName() != null :"Event Name should not be null";
                assert e.getFromDateTime() != null :"Event By Date should not be null";
                assert e.getToDateTime() != null :"Event To Date should not be null";

                fw.write("E | " + isDone + " | " + e.getTaskName() + " | " + e.getFromDateTime() + " | " + e.getToDateTime());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
