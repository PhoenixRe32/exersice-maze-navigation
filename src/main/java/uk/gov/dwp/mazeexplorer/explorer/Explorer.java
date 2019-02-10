package uk.gov.dwp.mazeexplorer.explorer;

import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;

import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

public class Explorer {

    private final Maze map;

    private Position currentPosition;

    public Explorer(Maze map) {
        this.map = map;
        this.currentPosition = new Position(map.getEntranceCoordinates(), NORTH);
    }

    public void moveForward() throws InvalidDirection {
        this.currentPosition = new Position(
                currentPosition.getCoordinates().move(currentPosition.getDirection()),
                currentPosition.getDirection());
    }

    public void turnRight() {
        this.currentPosition = new Position(
                currentPosition.getCoordinates(),
                currentPosition.getDirection().turnRight());
    }

    public void turnLeft() {
        this.currentPosition = new Position(
                currentPosition.getCoordinates(),
                currentPosition.getDirection().turnLeft());
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
}
