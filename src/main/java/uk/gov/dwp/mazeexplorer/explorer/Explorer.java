package uk.gov.dwp.mazeexplorer.explorer;

import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;

import java.util.Objects;

import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.Direction;

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

    public static final class Position {

        private Coordinates coordinates;

        private Direction direction;

        public Position(Coordinates coordinates, Direction direction) {
            this.coordinates = coordinates;
            this.direction = direction;
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
    }
}
