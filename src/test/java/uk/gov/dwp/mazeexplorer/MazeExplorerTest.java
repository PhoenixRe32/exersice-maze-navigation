package uk.gov.dwp.mazeexplorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;

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

    @Test
    void shouldCreateExplorerAndPlaceHimAtEntranceOfMazeLookingNorth() {
        mazeExplorer.start(SOME_VALID_MAZE);
        assertEquals(new Position(new Coordinates(0, 1), NORTH), mazeExplorer.getExplorer().getCurrentPosition());
    }
}