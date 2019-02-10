package uk.gov.dwp.mazeexplorer;

import java.io.IOException;
import java.net.URISyntaxException;

import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

public class Application {
    public static void main(String... arguments) throws IOException, URISyntaxException, InvalidDirection {
        MazeExplorer mazeExplorer = new MazeExplorer();
        mazeExplorer.start();
    }
}
