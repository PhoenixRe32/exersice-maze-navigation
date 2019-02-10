package uk.gov.dwp.mazeexplorer.maze.exceptions;

public class InvalidMazeFileException extends RuntimeException {

    public InvalidMazeFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMazeFileException(String message) {
        super(message);
    }
}
