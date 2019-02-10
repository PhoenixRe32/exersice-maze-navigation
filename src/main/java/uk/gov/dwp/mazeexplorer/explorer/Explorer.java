package uk.gov.dwp.mazeexplorer.explorer;

import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;

import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.Direction;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

public class Explorer {

    private final Maze map;

    private Position currentPosition;

    public Explorer(Maze map) {
        this.map = map;
        this.currentPosition = new Position(map.getEntranceCoordinates(), NORTH);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void moveForward() throws InvalidDirection {
        Coordinates coordinates = currentPosition.getCoordinates();
        Direction direction = currentPosition.getDirection();
        this.currentPosition = new Position(coordinates.move(direction), direction);
    }

}
