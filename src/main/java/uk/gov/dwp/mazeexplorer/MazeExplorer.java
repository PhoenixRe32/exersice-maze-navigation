package uk.gov.dwp.mazeexplorer;

import static java.nio.file.Files.isRegularFile;
import static java.util.stream.Collectors.toMap;

import java.io.Console;
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

    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final String MAZES_DIRECTORY = "mazes";
    
    private static final String EXIT = "exit";

    private final Map<String, Maze> mazes;
    Map<String, Maze> getMazes() {
        return mazes;
    }

    private Explorer explorer;
    Explorer getExplorer() {
        return explorer;
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

    Explorer createExplorer(String someValidMaze) {
        Maze maze = mazes.get(someValidMaze);
        if (maze == null) {
            System.out.println("The maze map doesn't exist. Exiting...");
            return null;
        }
        return new Explorer(maze);
    }

    public void start() {
        Console console = System.console();
        console.format("Welcome to maze explorer.").format(NEW_LINE)
                .format("You have the following mazes:").format(NEW_LINE);
        mazes.keySet().forEach(mazeName -> console.format(mazeName).format(NEW_LINE));

        String mazeName = console.readLine("What maze do you want to explore? ");

        Explorer explorer = createExplorer(mazeName);
        if (this.explorer == null) {
            return;
        }

        loop(explorer);
    }

    private void loop(Explorer explorer) {
        Console console = System.console();
        String instruction = "";
        while (!instruction.equalsIgnoreCase(EXIT)) {
            instruction = console
                    .format("You are at %s", explorer.getCurrentPosition()).format(NEW_LINE)
                    .format("You are allowed to:").format(NEW_LINE)
                    .format("\tStep: step forward").format(NEW_LINE)
                    .format("\tLeft: turn left").format(NEW_LINE)
                    .format("\tRight: turn right").format(NEW_LINE)
                    .format("\tPeek: see what's ahead of you").format(NEW_LINE)
                    .format("\tCheck: see where you can move").format(NEW_LINE)
                    .format("\tTrail: print your trail so far").format(NEW_LINE)
                    .format("\tExit: end it").format(NEW_LINE).format(NEW_LINE)
                    .readLine("What would you like to do? ")
                    .toUpperCase();
            console.format(instruction);
        }
    }
}
