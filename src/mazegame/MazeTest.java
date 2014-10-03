package mazegame;

import static org.junit.Assert.*;

import org.junit.Test;

public class MazeTest {

  @Test
  public void testGetCellCenter() {
    Maze maze = new Maze();
    assertEquals(20,maze.getCellCenterX(0, 0));
    assertEquals(60,maze.getCellCenterY(0, 0));
  }

}
