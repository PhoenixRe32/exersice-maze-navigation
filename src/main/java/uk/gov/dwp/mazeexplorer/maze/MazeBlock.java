package uk.gov.dwp.mazeexplorer.maze;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

import uk.gov.dwp.mazeexplorer.maze.exceptions.InvalidMazeBlockRepresentation;

public enum MazeBlock {
    WALL('X'),
    SPACE(' '),
    ENTRY('S'),
    EXIT('F');

    private final Character representation;

    private static final Map<Character, MazeBlock> mappings = Arrays.stream(MazeBlock.values())
            .collect(toMap(MazeBlock::getRepresentation, mb -> mb));


    public Character getRepresentation() {
        return representation;
    }

    MazeBlock(Character representation) {
        this.representation = representation;
    }

    public static MazeBlock fromCharacter(Character c) throws InvalidMazeBlockRepresentation {
        MazeBlock mazeBlock = mappings.get(c);
        if (mazeBlock == null) {
            throw new InvalidMazeBlockRepresentation(c + " is not a valid maze block.");
        }
        return mazeBlock;
    }
}
