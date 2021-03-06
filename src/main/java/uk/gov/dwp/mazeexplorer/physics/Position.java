package uk.gov.dwp.mazeexplorer.physics;

import java.util.Objects;

public final class Position {

    private Coordinates coordinates;

    private Direction direction;

    public Position(Coordinates coordinates, Direction direction) {
        Objects.requireNonNull(coordinates);
        Objects.requireNonNull(direction);
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(coordinates, position.coordinates) &&
                direction == position.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, direction);
    }

    @Override
    public String toString() {
        return coordinates + " looking " + direction;
    }
}
