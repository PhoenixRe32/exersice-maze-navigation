package uk.gov.dwp.mazeexplorer.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.EXIT;
import static uk.gov.dwp.mazeexplorer.physics.Direction.EAST;
import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;
import static uk.gov.dwp.mazeexplorer.physics.Direction.SOUTH;
import static uk.gov.dwp.mazeexplorer.physics.Direction.WEST;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.maze.MazeBlock;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

class ExplorerTest {

    private final Maze maze = mock(Maze.class);

    private Explorer explorer;

    @BeforeEach
    void setup() {
        explorer = new Explorer(maze);
    }

    private static Stream<Arguments> happyPathCombinationsForMovingForward() {
        Coordinates coordinates = new Coordinates(1, 1);
        return Stream.of(
                Arguments.of(new Position(coordinates, NORTH), new Position(new Coordinates(1, 0), NORTH)),
                Arguments.of(new Position(coordinates, SOUTH), new Position(new Coordinates(1, 2), SOUTH)),
                Arguments.of(new Position(coordinates, EAST), new Position(new Coordinates(2, 1), EAST)),
                Arguments.of(new Position(coordinates, WEST), new Position(new Coordinates(0, 1), WEST))
        );
    }

    @ParameterizedTest
    @MethodSource("happyPathCombinationsForMovingForward")
    void shouldMoveExplorerForwardByOneSpace(Position initialPosition, Position expectedPosition) throws InvalidDirection {
        explorer.setCurrentPosition(initialPosition);

        explorer.moveForward();

        assertEquals(expectedPosition, explorer.getCurrentPosition());
    }

    private static Stream<Arguments> combinationsOfInitialPositionAndExpectedPositionAfterTurningRight() {
        Coordinates coordinates = new Coordinates(1, 1);
        return Stream.of(
                Arguments.of(new Position(coordinates, NORTH), new Position(coordinates, EAST)),
                Arguments.of(new Position(coordinates, EAST), new Position(coordinates, SOUTH)),
                Arguments.of(new Position(coordinates, SOUTH), new Position(coordinates, WEST)),
                Arguments.of(new Position(coordinates, WEST), new Position(coordinates, NORTH))
        );
    }

    @ParameterizedTest
    @MethodSource("combinationsOfInitialPositionAndExpectedPositionAfterTurningRight")
    void shouldTurnExplorerToFaceNextDirectionToTheRight(Position initialPosition, Position expectedPosition) {
        explorer.setCurrentPosition(initialPosition);

        explorer.turnRight();
        assertEquals(expectedPosition, explorer.getCurrentPosition());
    }

    private static Stream<Arguments> combinationsOfInitialPositionAndExpectedPositionAfterTurningLeft() {
        Coordinates coordinates = new Coordinates(1, 1);
        return Stream.of(
                Arguments.of(new Position(coordinates, NORTH), new Position(coordinates, WEST)),
                Arguments.of(new Position(coordinates, WEST), new Position(coordinates, SOUTH)),
                Arguments.of(new Position(coordinates, SOUTH), new Position(coordinates, EAST)),
                Arguments.of(new Position(coordinates, EAST), new Position(coordinates, NORTH))
        );
    }

    @ParameterizedTest
    @MethodSource("combinationsOfInitialPositionAndExpectedPositionAfterTurningLeft")
    void shouldTurnExplorerToFaceNextDirectionToTheLeft(Position initialPosition, Position expectedPosition) {
        explorer.setCurrentPosition(initialPosition);

        explorer.turnLeft();
        assertEquals(expectedPosition, explorer.getCurrentPosition());
    }

    @Test
    void shouldReportWhatIsInFrontOfTheExplorer() throws InvalidDirection {
        Position currentPosition = new Position(new Coordinates(1, 1), EAST);
        Coordinates coordinatesAhead = new Coordinates(2, 1);
        explorer.setCurrentPosition(currentPosition);
        MazeBlock mazeBlockMockReturned = EXIT;
        doReturn(mazeBlockMockReturned).when(maze).peekAt(eq(coordinatesAhead));

        MazeBlock mazeBlockAhead = explorer.peekAhead();

        verify(maze).peekAt(eq(coordinatesAhead));
        assertEquals(mazeBlockMockReturned, mazeBlockAhead);
    }
}