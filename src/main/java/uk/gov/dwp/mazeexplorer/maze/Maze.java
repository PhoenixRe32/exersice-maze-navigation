package uk.gov.dwp.mazeexplorer.maze;

import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.SPACE;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.WALL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.dwp.mazeexplorer.maze.exceptions.InvalidMazeBlockRepresentation;
import uk.gov.dwp.mazeexplorer.maze.exceptions.MazeFileException;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;

public class Maze {

    private final String mazeName;

    private final Map<Coordinates, MazeBlock> mazeMap;

    private final long numberOfWalls;

    private final long numberOfEmptySpaces;

    public static Maze createFromFile(Path pathToMazeFile) {
        try {
            return new Maze(pathToMazeFile);
        } catch (IOException | InvalidMazeBlockRepresentation e) {
            throw new MazeFileException("Creation of maze from " + pathToMazeFile + " encountered some problems", e);
        }
    }

    private Maze(Path pathToMazeFile) throws IOException, InvalidMazeBlockRepresentation {
        List<String> lines = Files.readAllLines(pathToMazeFile);
        this.mazeMap = buildMapOfMaze(lines);
        this.numberOfWalls = this.mazeMap.values().stream().filter(mazeBlock -> mazeBlock == WALL).count();
        this.numberOfEmptySpaces = this.mazeMap.values().stream().filter(mazeBlock -> mazeBlock == SPACE).count();
        this.mazeName = pathToMazeFile.getFileName().toString();
    }

    private Map<Coordinates, MazeBlock> buildMapOfMaze(List<String> lines) throws InvalidMazeBlockRepresentation {
        Map<Coordinates, MazeBlock> mazeMap = new HashMap<>();

        Coordinates position = new Coordinates(0, 0);
        for (String line : lines) {
            for (Character character : line.toCharArray()) {
                mazeMap.put(position, MazeBlock.fromCharacter(character));
                position = position.moveEast();
            }
            position = position.moveSouth().resetX();
        }

        return Collections.unmodifiableMap(mazeMap);
    }

    public long getNumberOfWalls() {
        return numberOfWalls;
    }

    public long getNumberOfEmptySpaces() {
        return numberOfEmptySpaces;
    }

    public MazeBlock whatKindOfBlockIsIn(Coordinates coordinates) {
        return mazeMap.get(coordinates);
    }

    public String getMazeName() {
        return mazeName;
    }
}
