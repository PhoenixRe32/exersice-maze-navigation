package uk.gov.dwp.mazeexplorer.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

class ExplorerTest {

    private final Maze maze = mock(Maze.class);

    private static Stream<Arguments> happyPathCombinationsForMovingForward() {
        return Stream.of(
                Arguments.of(new Coordinates(1, 1), new Position(new Coordinates(1,0), NORTH))
        );
    }

    @ParameterizedTest
    @MethodSource("happyPathCombinationsForMovingForward")
    void shouldMoveExplorerForwardByOneSpace(Coordinates startingCoordinates, Position expectedPosition) throws InvalidDirection {
        doReturn(startingCoordinates).when(maze).getEntranceCoordinates();
        Explorer explorer = new Explorer(maze);

        explorer.moveForward();

        assertEquals(expectedPosition, explorer.getCurrentPosition());
    }
}