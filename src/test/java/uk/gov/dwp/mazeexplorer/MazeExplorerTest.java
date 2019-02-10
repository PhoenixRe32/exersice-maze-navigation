package uk.gov.dwp.mazeexplorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.mazeexplorer.explorer.Explorer;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.Position;

class MazeExplorerTest {

    private static final String SOME_VALID_MAZE = "someValidMaze";
    /* The maze we use.
        XXXX
        S  F
        X XX
    */

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
        assertNotNull(mazeExplorer.createExplorer(SOME_VALID_MAZE));
    }

    @Test
    void shouldCreateExplorerAndPlaceHimAtEntranceOfMazeLookingNorth() {
        Explorer explorer = mazeExplorer.createExplorer(SOME_VALID_MAZE);
        assertEquals(new Position(new Coordinates(0, 1), NORTH), explorer.getCurrentPosition());
    }
}