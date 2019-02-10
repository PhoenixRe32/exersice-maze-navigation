package uk.gov.dwp.mazeexplorer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MazeExplorerTest {

    private static final String SOME_VALID_MAZE = "someValidMaze";

    private MazeExplorer mazeExplorer;

    @BeforeEach
    void setup() throws IOException, URISyntaxException {
        mazeExplorer = new MazeExplorer();
    }

    @Test
    void shouldInitialiseAMazesWhenCreated() {
        assertFalse(mazeExplorer.getMazes().isEmpty());
    }

    @Test
    void shouldCreateExplorerWithSpecificMazeMapWhenStartingGame() {
        mazeExplorer.start(SOME_VALID_MAZE);

        assertNotNull(mazeExplorer.getExplorer());
    }
}