package uk.gov.dwp.mazeexplorer.physics;

import java.util.Objects;

public final class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates moveSouth() {
        return new Coordinates(this.x, this.y + 1);
    }

    public Coordinates resetX() {
        return new Coordinates(0, this.y);
    }

    public Coordinates moveEast() {
        return new Coordinates(this.x + 1, this.y);
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
        return "Coordinates{x=" + x + ", y=" + y + '}';
    }
}

