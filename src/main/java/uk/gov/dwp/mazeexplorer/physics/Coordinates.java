package uk.gov.dwp.mazeexplorer.physics;

import java.util.List;
import java.util.Objects;

import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

public final class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates resetX() {
        return new Coordinates(0, this.y);
    }

    public Coordinates moveNorth() {
        return new Coordinates(this.x, this.y - 1);
    }

    public Coordinates moveSouth() {
        return new Coordinates(this.x, this.y + 1);
    }

    public Coordinates moveWest() {
        return new Coordinates(this.x - 1, this.y);
    }

    public Coordinates moveEast() {
        return new Coordinates(this.x + 1, this.y);
    }

    public Coordinates move(Direction direction) throws InvalidDirection {
        switch (direction) {
            case NORTH:
                return moveNorth();
            case EAST:
                return moveEast();
            case SOUTH:
                return moveSouth();
            case WEST:
                return moveWest();
            default:
                throw new InvalidDirection("The direction can't be null. It must be specified.");
        }
    }


    public List<Coordinates> getTheCoordinatesOfTheTilesAroundUs() {
        return List.of(
                moveNorth(),
                moveEast(),
                moveSouth(),
                moveWest());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinates)) {
            return false;
        }
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(x=" + x + ", y=" + y + ')';
    }
}

