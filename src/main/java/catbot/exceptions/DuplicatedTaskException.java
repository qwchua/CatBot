package catbot.exceptions;

public class DuplicatedTaskException extends Exception {
    public DuplicatedTaskException() {
    }

    public DuplicatedTaskException(String message) {
        super(message);
    }
}

