package uk.gov.dwp.mazeexplorer;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

class MazeExplorerTest {

    @Test
    void shouldInitialiseAMazeWhenInitialising() throws IOException, URISyntaxException {
        MazeExplorer mazeExplorer = new MazeExplorer();

        assertFalse(mazeExplorer.getMazes().isEmpty());
    }

//    void shouldInitialiseAnExplorerWithAMazeWhenInitialising() throws IOException, URISyntaxException {
//        mazeExplorer.initialise(SOME_VALID_MAZE);
//
//        assertNotNull(mazeExplorer.getExplorer());
//    }
}