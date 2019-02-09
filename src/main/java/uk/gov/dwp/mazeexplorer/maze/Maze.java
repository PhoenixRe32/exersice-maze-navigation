package uk.gov.dwp.mazeexplorer.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Maze {
    private int numberOfWalls;

    private int numberOfEmptySpaces;

    public Maze(Path pathToMaze) throws IOException {
        Files.lines(pathToMaze)
                .flatMap(line -> line.chars().mapToObj(i -> (char) i))
                .forEach(c -> {
                    MazeBlock mazeBlock = MazeBlock.fromCharacter(c);
                    switch (mazeBlock) {
                        case WALL:
                            numberOfWalls++;
                            break;
                        case SPACE:
                            numberOfEmptySpaces++;
                            break;
                        case ENTRY:
                            break;
                        case EXIT:
                            break;
                    }
                });
    }

    public int getNumberOfWalls() {
        return numberOfWalls;
    }

    public int getNumberOfEmptySpaces() {
        return numberOfEmptySpaces;
    }
}
