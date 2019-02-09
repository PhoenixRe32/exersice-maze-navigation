package uk.gov.dwp.mazeexplorer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MazeExplorerTest {

    MazeExplorer mazeExplorer = new MazeExplorer();

    @Test
    void shouldInitialiseAMazeWhenInitialising() {
        mazeExplorer.initialise();

        assertNotNull(mazeExplorer.getMaze());
    }

    @Test
    void shouldInitialiseAMazeWithDetailsAboutNumberOfWallsAndEmptySpaces() {
        mazeExplorer.initialise();

        assertTrue(mazeExplorer.getMaze().getNumberOfWalls() > 0);
        assertTrue(mazeExplorer.getMaze().getNumberOfEmptySpaces() > 0);
    }

}