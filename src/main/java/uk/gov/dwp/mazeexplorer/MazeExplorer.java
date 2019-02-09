package uk.gov.dwp.mazeexplorer;

import uk.gov.dwp.mazeexplorer.maze.Maze;

public class MazeExplorer {
    private Maze maze;

    public Maze getMaze() {
        return maze;
    }

    public void initialise() {
        maze = new Maze();
    }
}
