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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import uk.gov.dwp.mazeexplorer.explorer.Explorer;
import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

public class MazeExplorer {

    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final String MAZES_DIRECTORY = "mazes";

    private static final String EXIT = "EXIT";
    private static final String STEP = "STEP";
    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";
    private static final String PEEK = "PEEK";
    private static final String CHECK = "CHECK";
    private static final String TRAIL = "TRAIL";

    private final Map<String, Maze> mazes;

    Map<String, Maze> getMazes() {
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

    Explorer createExplorer(String someValidMaze) {
        Maze maze = mazes.get(someValidMaze);
        if (maze == null) {
            System.out.println("The maze map doesn't exist. Exiting...");
            return null;
        }
        return new Explorer(maze);
    }

    public void start() throws InvalidDirection {
        Scanner scanner = new Scanner(System.in);
        System.out.format("Welcome to maze explorer.").format(NEW_LINE)
                .format("You have the following mazes:").format(NEW_LINE);
        mazes.keySet().forEach(mazeName -> System.out.format("\t* %s", mazeName).format(NEW_LINE));

        System.out.format("What maze do you want to explore?").format(NEW_LINE);
        String mazeName = scanner.nextLine();

        Explorer explorer = createExplorer(mazeName);
        if (explorer == null) {
            return;
        }

        loop(explorer);
    }

    private void loop(Explorer explorer) throws InvalidDirection {
        Scanner scanner = new Scanner(System.in);
        String instruction = "";
        while (!instruction.equalsIgnoreCase(EXIT)) {
            System.out.format(NEW_LINE)
                    .format("You are at %s", explorer.getCurrentPosition()).format(NEW_LINE)
                    .format("You are allowed to:").format(NEW_LINE)
                    .format("\tStep: step forward").format(NEW_LINE)
                    .format("\tLeft: turn left").format(NEW_LINE)
                    .format("\tRight: turn right").format(NEW_LINE)
                    .format("\tPeek: see what's ahead of you").format(NEW_LINE)
                    .format("\tCheck: see where you can move").format(NEW_LINE)
                    .format("\tTrail: print your trail so far").format(NEW_LINE)
                    .format("\tExit: end it").format(NEW_LINE).format(NEW_LINE)
                    .format("What would you like to do?").format(NEW_LINE);
            instruction = scanner.nextLine().toUpperCase();
            switch (instruction) {
                case STEP:
                    explorer.moveForward();
                    break;
                case RIGHT:
                    explorer.turnRight();
                    break;
                case LEFT:
                    explorer.turnLeft();
                    break;
                case PEEK:
                    System.out.format("%s up ahead.", explorer.peekAhead());
                    break;
                case TRAIL:
                    List<Position> trail = explorer.getTrail();
                    trail.forEach(position -> System.out.format("\t* %s", position).format(NEW_LINE));
                    break;
                case CHECK:
                    List<Coordinates> freeSpace = explorer.findFreeSpacesInTheSurroundingAreaToMoveTo();
                    System.out.format("You can move to the following tiles:").format(NEW_LINE);
                    freeSpace.forEach(coordinates -> System.out.format("\t* %s", coordinates).format(NEW_LINE));
                default:
                    break;
            }
        }
    }
}
