package catbot.task;

import catbot.exceptions.DuplicatedTaskException;
import catbot.utils.Utils;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void testAddingDuplicatedTaskToTaskList() {
        TaskList tl = new TaskList();

        Task task1 = new Deadline("Return Book", LocalDateTime.of(2022,11,11,5,30));
        Task task2 = new Deadline("Return Book", LocalDateTime.of(2022,11,11,5,30));

        try{
            tl.addTask(task1);
            tl.addTask(task2);
        } catch (Exception e){
            assertEquals("Cat detected a duplicated task and did not add this task" + System.lineSeparator() +
                    "pls try again!", e.getMessage());
        }
    }

    @Test
    public void testTaskHashMatch() {
        TaskList tl = new TaskList();

        Task task1 = new Deadline("Return Book", LocalDateTime.of(2022,11,11,5,30));

        String hash = tl.getTaskHash(task1);

        assertEquals(hash, "75956d3efca4a20f80ce8445d54c0adb28d3ba6f");
    }
}
