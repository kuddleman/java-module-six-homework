package sample;

public class ContainsBadCharacterException extends Exception {
    private static final String MESSAGE = "Invalid character.";

    public ContainsBadCharacterException() {
        super(MESSAGE);
    }

    public ContainsBadCharacterException(String message) {
        super(message);
    }

}
