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

  private boolean [][] hasDot;
  
  private Image wallImage = null;
  private int topY;
  private int leftX;
  private Image dotImage;
  
  public Maze() {
    topY = (MazeGame.GAME_HEIGHT - ROWS*BLOCK_SIZE)/2;
    leftX = (MazeGame.GAME_WIDTH - COLS*BLOCK_SIZE)/2;
    initDotCheckArray();
  }
  
  private void initDotCheckArray() {
    hasDot = new boolean[ROWS][COLS];
    for (int r = 0; r < ROWS; r++) {
      for (int c = 0; c < COLS; c++) {
        hasDot[r][c] = (mapAt(r,c) == '.');
      }
    }
  }

  public void init() {
    try {
      wallImage = new Image("res/wall.png");
      dotImage = new Image("res/dot.png");
    } catch (SlickException e) {
      wallImage = null;
      dotImage = null;
    }
  }

  public void render() {
    for (int r = 0; r < ROWS; r++) {
      for (int c = 0; c < COLS; c++) {
        char mchar = mapAt(r,c);
        if (mchar == '#') {
          wallImage.draw(getCellX(r,c), getCellY(r,c)); 
        } else if (hasDot[r][c]) {
          dotImage.draw(getCellX(r,c), getCellY(r,c));
        }
      }
    }
  }

  private char mapAt(int r, int c) {
    return MAP[r].charAt(c);
  }
  
  public int getCellX(int r, int c) {
    return leftX + c * BLOCK_SIZE;
  }
  
  public int getCellY(int r, int c) {
    return topY + r * BLOCK_SIZE;
  }

  public int getCellCenterX(int r, int c) {
    return getCellX(r,c) + BLOCK_SIZE/2;
  }

  public int getCellCenterY(int r, int c) {
    return getCellY(r,c) + BLOCK_SIZE/2;    
  }

  public boolean isAtCellCenter(int x, int y) {
    return ((x - leftX) % BLOCK_SIZE == BLOCK_SIZE/2) &&
        ((y - topY) % BLOCK_SIZE == BLOCK_SIZE/2);
  }

  public int getRow(int x, int y) {
    return (y - topY) / BLOCK_SIZE;
  }

  public int getCol(int x, int y) {
    return (x - leftX) / BLOCK_SIZE;
  }
  
  public char getMap(int x, int y) {
    int r = getRow(x, y);
    int c = getCol(x, y);
    return mapAt(r,c);
  }
  
  public boolean isEmpty(int x, int y) {
    return getMap(x,y) == '.';
  }

  public boolean hasDotAt(int x, int y) {
    int r = getRow(x, y);
    int c = getCol(x, y);
    return hasDot[r][c];
  }

  public void eatDotAt(int x, int y) {
    int r = getRow(x, y);
    int c = getCol(x, y);
    hasDot[r][c] = false;
  }
}
