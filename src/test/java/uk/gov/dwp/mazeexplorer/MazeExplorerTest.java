package uk.gov.dwp.mazeexplorer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

class MazeExplorerTest {

    private static final String BARELY_VALID_MAZE = "barelyValidMaze";

    MazeExplorer mazeExplorer = new MazeExplorer();

    @Test
    void shouldInitialiseAMazeWhenInitialising() throws IOException, URISyntaxException {
        mazeExplorer.initialise(BARELY_VALID_MAZE);

        assertNotNull(mazeExplorer.getMaze());
    }

    @Test
    void shouldInitialiseAMazeWithDetailsAboutNumberOfWallsAndEmptySpaces() throws IOException, URISyntaxException {
        mazeExplorer.initialise(BARELY_VALID_MAZE);

        assertTrue(mazeExplorer.getMaze().getNumberOfWalls() > 0);
        assertTrue(mazeExplorer.getMaze().getNumberOfEmptySpaces() > 0);
    }

}