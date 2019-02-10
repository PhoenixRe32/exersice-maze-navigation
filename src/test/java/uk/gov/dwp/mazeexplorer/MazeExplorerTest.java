package uk.gov.dwp.mazeexplorer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

class MazeExplorerTest {

    private static final String SOME_VALID_MAZE = "someValidMaze";

    MazeExplorer mazeExplorer = new MazeExplorer();

    @Test
    void shouldInitialiseAMazeWhenInitialising() throws IOException, URISyntaxException {
        mazeExplorer.initialise(SOME_VALID_MAZE);

        assertNotNull(mazeExplorer.getMaze());
    }
}