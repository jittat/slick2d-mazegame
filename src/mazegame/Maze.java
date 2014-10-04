package mazegame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Maze {
  static public int ROWS = 10;
  static public int COLS = 16;
  static public int BLOCK_SIZE = 40;
  static private String[] MAP = {
    "################",
    "#..............#",
    "#.#.###..###.#.#",
    "#...#......#...#",
    "#.#...#..#...#.#",
    "#.#...#..#...#.#",
    "#...#......#...#",
    "#.#.###..###.#.#",
    "#..............#",
    "################"
  };
  
  private Image wallImage = null;
  private int topY;
  private int leftX;
  
  public Maze() {
    topY = (MazeGame.GAME_HEIGHT - ROWS*BLOCK_SIZE)/2;
    leftX = (MazeGame.GAME_WIDTH - COLS*BLOCK_SIZE)/2;
  }
  
  public void init() {
    try {
      wallImage = new Image("res/wall.png");
    } catch (SlickException e) {
      wallImage = null;
    }
  }

  public void render() {
    for (int r = 0; r < ROWS; r++) {
      for (int c = 0; c < COLS; c++) {
        if (MAP[r].charAt(c) == '#') {
          wallImage.draw(leftX + (c * BLOCK_SIZE), 
              topY + (r * BLOCK_SIZE));
        }
      }
    }
  }
  
  public int getCellCenterX(int r, int c) {
    return leftX + c * BLOCK_SIZE + BLOCK_SIZE/2;
  }

  public int getCellCenterY(int r, int c) {
    return topY + r * BLOCK_SIZE + BLOCK_SIZE/2;    
  }

  public boolean isAtCellCenter(int x, int y) {
    return ((x - leftX) % BLOCK_SIZE == BLOCK_SIZE/2) &&
        ((y - topY) % BLOCK_SIZE == BLOCK_SIZE/2);
  }

  public boolean isEmpty(int x, int y) {
    int r = (y - topY) / BLOCK_SIZE;
    int c = (x - leftX) / BLOCK_SIZE;
    return MAP[r].charAt(c) == '.';
  }
}
