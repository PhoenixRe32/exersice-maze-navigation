package uk.gov.dwp.mazeexplorer.explorer;

import static java.util.stream.Collectors.toList;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.isEntrance;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.isExit;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.isSpace;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.isWall;
import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;

import java.util.LinkedList;
import java.util.List;

import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.maze.MazeBlock;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

public class Explorer {

    private final Maze map;

    private Position currentPosition;

    private final List<Position> trail = new LinkedList<>();

    public Explorer(Maze map) {
        this.map = map;
        this.currentPosition = new Position(map.getEntranceCoordinates(), NORTH);
        trail.add(this.currentPosition);
    }

    public void moveForward() throws InvalidDirection {
        MazeBlock whatIsAhead = peekAhead();

        if (whatIsAhead == null) {
            System.out.println("Trying to go over the edge of the map.");
            return;
        }

        if (isWall.test(whatIsAhead)){
            System.out.println("There is a wall ahead. Can't move.");
            return;
        }


        this.currentPosition = new Position(
                currentPosition.getCoordinates().move(currentPosition.getDirection()),
                currentPosition.getDirection());
        this.trail.add(this.currentPosition);
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

    public MazeBlock peekAhead() throws InvalidDirection {
        Coordinates upAhead = currentPosition.getCoordinates().move(currentPosition.getDirection());
        return map.peekAt(upAhead);
    }

    /***
     * Performs a search clockwise starting from the North direction and returns a list of
     * coordinates where the maze has free spaces (not walls or nulls - edges).
     * @return list of coordinates
     */
    public List<Coordinates> findFreeSpacesInTheSurroundingAreaToMoveTo() {
        return currentPosition.getCoordinates().getTheCoordinatesOfTheTilesAroundUs()
                .stream()
                .filter(coordinates -> isSpace.or(isEntrance).or(isExit).test(map.peekAt(coordinates)))
                .collect(toList());
    }

    void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public List<Position> getTrail() {
        return trail;
    }
}
