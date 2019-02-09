package uk.gov.dwp.mazeexplorer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import uk.gov.dwp.mazeexplorer.maze.Maze;

public class MazeExplorer {
    private Maze maze;

    public Maze getMaze() {
        return maze;
    }

    public void initialise(String mazeId) throws URISyntaxException, IOException {
        URL urlToMaze = getClass().getClassLoader().getResource(mazeId);
        Objects.requireNonNull(urlToMaze, "Maze with id:" + mazeId + " could not be located");

        Path pathToMaze = Paths.get(urlToMaze.toURI());
        maze = new Maze(pathToMaze);
    }
}
