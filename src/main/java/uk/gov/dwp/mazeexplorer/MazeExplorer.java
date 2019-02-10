package uk.gov.dwp.mazeexplorer;

import static java.nio.file.Files.isRegularFile;
import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import uk.gov.dwp.mazeexplorer.explorer.Explorer;
import uk.gov.dwp.mazeexplorer.maze.Maze;

public class MazeExplorer {

    private static final String MAZES_DIRECTORY = "mazes";

    private final Map<String, Maze> mazes;

    private Explorer explorer;

    public Map<String, Maze> getMazes() {
        return mazes;
    }

    public MazeExplorer() throws URISyntaxException, IOException {
        URL urlToMazesDirectory = getClass().getClassLoader().getResource(MAZES_DIRECTORY);
        Objects.requireNonNull(urlToMazesDirectory, "Maze directory (" + urlToMazesDirectory + ") could not be located");
        Path pathToMazes = Paths.get(urlToMazesDirectory.toURI());

        Map<String, Maze> mazes = Files.list(pathToMazes)
                .filter(pathToFile -> isRegularFile(pathToFile))
                .map(Maze::createFromFile)
                .collect(toMap(Maze::getMazeName, maze -> maze));
        this.mazes = Collections.unmodifiableMap(mazes);
    }

    public void start(String someValidMaze) {
        Maze maze = mazes.get(someValidMaze);
        if (maze == null) {
            System.out.println("The maze map doesn't exist. Exiting...");
            return;
        }

        this.explorer = new Explorer(maze);
    }

    public Explorer getExplorer() {
        return explorer;
    }
}
