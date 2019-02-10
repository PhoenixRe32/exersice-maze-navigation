package uk.gov.dwp.mazeexplorer.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.mazeexplorer.maze.Maze.createFromFile;
import static uk.gov.dwp.mazeexplorer.maze.MazeBlock.EXIT;
import static uk.gov.dwp.mazeexplorer.physics.Direction.EAST;
import static uk.gov.dwp.mazeexplorer.physics.Direction.NORTH;
import static uk.gov.dwp.mazeexplorer.physics.Direction.SOUTH;
import static uk.gov.dwp.mazeexplorer.physics.Direction.WEST;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.mazeexplorer.maze.Maze;
import uk.gov.dwp.mazeexplorer.maze.MazeBlock;
import uk.gov.dwp.mazeexplorer.physics.Coordinates;
import uk.gov.dwp.mazeexplorer.physics.Position;
import uk.gov.dwp.mazeexplorer.physics.exceptions.InvalidDirection;

class ExplorerTest {

    private static final String TEST_MAZE_FOR_EXPLORER = "mazes/testMazeForCheckingArea";

    private Explorer explorer;

    @BeforeEach
    void setup() throws URISyntaxException {
        /* Maze we use
            XXX
            S F
            X X
        */
        URL urlToValidTestMazeFile = Objects.requireNonNull(getClass().getClassLoader().getResource(TEST_MAZE_FOR_EXPLORER));
        Maze maze = createFromFile(Paths.get(urlToValidTestMazeFile.toURI()));
        explorer = new Explorer(maze);
    }

    @Test
    void shouldHaveStartingPositionWhenInitialisedInTheExplorersPath() {
        assertEquals(1, explorer.getTrail().size());
        assertEquals(new Position(new Coordinates(0, 1), NORTH), explorer.getTrail().get(0));
    }

    private static Stream<Arguments> happyPathCombinationsForMovingForward() {
        Coordinates coordinates = new Coordinates(1, 1);
        return Stream.of(
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
        assertEquals(2, explorer.getTrail().size());
        assertEquals(expectedPosition, explorer.getTrail().get(1));
    }

    @Test
    void shouldNotMoveExplorerWhenThereIsAWallAhead() throws InvalidDirection {
        Position startingPosition = explorer.getCurrentPosition();
        explorer.moveForward();

        assertEquals(startingPosition, explorer.getCurrentPosition());
        assertEquals(1, explorer.getTrail().size());
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
        explorer.setCurrentPosition(currentPosition);

        MazeBlock mazeBlockAhead = explorer.peekAhead();

        assertEquals(EXIT, mazeBlockAhead);
    }

    @Test
    void shouldReturnCoordinatesAroundPositionThatAreNotWallsAndWeCanMoveTo() {
        explorer.setCurrentPosition(new Position(new Coordinates(1, 1), EAST));

        List<Coordinates> freeSpaces = explorer.findFreeSpacesInTheSurroundingAreaToMoveTo();

        List<Coordinates> expectedCoordinates = List.of(
                new Coordinates(2, 1),
                new Coordinates(1, 2),
                new Coordinates(0, 1));
        assertEquals(expectedCoordinates, freeSpaces);
    }
}