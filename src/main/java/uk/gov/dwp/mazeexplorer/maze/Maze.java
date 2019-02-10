package uk.gov.dwp.mazeexplorer.maze;

import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.SPACE;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.WALL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.dwp.mazeexplorer.physics.Coordinates;

public class Maze {

    private int numberOfWalls;

    private int numberOfEmptySpaces;

    private Map<Coordinates, MazeBlock> mazeMap = new HashMap<>();

    public Maze(Path pathToMazeFile) throws IOException {
        List<String> lines = Files.readAllLines(pathToMazeFile);
        analyseMazeFileContents(lines);
    }

    private void analyseMazeFileContents(List<String> lines) {
        Coordinates position = new Coordinates(0, 0);
        for (String line : lines) {
            for (Character character : line.toCharArray()) {
                MazeBlock mazeBlock = MazeBlock.fromCharacter(character);
                updateMazeDetails(position, mazeBlock);
                position = position.moveEast();
            }
            position = position.moveSouth().resetX();
        }
    }

    private void updateMazeDetails(Coordinates position, MazeBlock mazeBlock) {
        mazeMap.put(position, mazeBlock);
        if (mazeBlock == WALL) {
            numberOfWalls++;
        } else if (mazeBlock == SPACE) {
            numberOfEmptySpaces++;
        }
    }

    public int getNumberOfWalls() {
        return numberOfWalls;
    }

    public int getNumberOfEmptySpaces() {
        return numberOfEmptySpaces;
    }

    public MazeBlock whatKindOfBlockIsIn(Coordinates coordinates) {
        return mazeMap.get(coordinates);
    }

}
