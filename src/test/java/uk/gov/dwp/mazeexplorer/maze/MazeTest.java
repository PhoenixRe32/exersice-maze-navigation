package uk.gov.dwp.mazeexplorer.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.dwp.mazeexplorer.maze.Maze.createFromFile;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.ENTRY;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.EXIT;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.SPACE;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.WALL;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;

class MazeTest {

    private static final String SOME_VALID_MAZE = "mazes/someValidMaze";
    private static final String SOME_TEMP_FILE = "mazes/someMazeTempFile";

    @Test
    void shouldInitialiseAMazeWithDetailsAboutNumberOfWallsAndEmptySpaces() throws URISyntaxException {
        URL urlToValidTestMazeFile = Objects.requireNonNull(getClass().getClassLoader().getResource(SOME_VALID_MAZE));

        Maze maze = createFromFile(Paths.get(urlToValidTestMazeFile.toURI()));

        assertTrue(maze.getNumberOfWalls() > 0);
        assertTrue(maze.getNumberOfEmptySpaces() > 0);
    }

    @Test
    void shouldBeAbleToTellWhatKindOfMazeBlockIsOnASpecificCoordinates() throws IOException, URISyntaxException {
        URL urlToTempMazeFile = Objects.requireNonNull(getClass().getClassLoader().getResource(SOME_TEMP_FILE));
        Path tempTestFilePath = Paths.get(urlToTempMazeFile.toURI());
        Files.write(tempTestFilePath, "S X\nX F".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        Maze maze = createFromFile(tempTestFilePath);

        assertEquals(ENTRY, maze.whatKindOfBlockIsIn(new Coordinates(0, 0)));
        assertEquals(SPACE, maze.whatKindOfBlockIsIn(new Coordinates(1, 0)));
        assertEquals(WALL, maze.whatKindOfBlockIsIn(new Coordinates(2, 0)));
        assertEquals(WALL, maze.whatKindOfBlockIsIn(new Coordinates(0, 1)));
        assertEquals(SPACE, maze.whatKindOfBlockIsIn(new Coordinates(1, 1)));
        assertEquals(EXIT, maze.whatKindOfBlockIsIn(new Coordinates(2, 1)));
        assertNull(maze.whatKindOfBlockIsIn(new Coordinates(4, 4)));
    }
}