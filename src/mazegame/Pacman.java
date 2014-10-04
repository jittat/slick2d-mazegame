package mazegame;

import java.util.ArrayList;
import java.util.HashMap;

import mazegame.Pacman.Direction;

public class Pacman {
  public enum Direction {
    STILL, LEFT, RIGHT, UP, DOWN;
  }
  public static final int STEP_SIZE = 5;
  
  private int x;
  private int y;
  private Direction dir;
  private Maze maze;
  private Direction nextDir;

  private HashMap<Direction, Integer> dirMapX;
  private HashMap<Direction, Integer> dirMapY;

  private ArrayList<ScoreUpdateListener> scoreUpdateListeners;
  
  Pacman(int x, int y, Direction dir, Maze maze) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.maze = maze;

    nextDir = Direction.STILL;
    initDirMap();
    
    scoreUpdateListeners = new ArrayList<ScoreUpdateListener>();
  }

  protected void initDirMap() {
    dirMapX = new HashMap<Direction, Integer>();
    dirMapY = new HashMap<Direction, Integer>();
    Direction [] dindex = { 
        Direction.STILL, 
        Direction.LEFT,
        Direction.RIGHT,
        Direction.UP,
        Direction.DOWN 
    };
    int [] dx = { 0,-1, 1, 0, 0 };
    int [] dy = { 0, 0, 0, -1, 1 };
    for (int i = 0; i < dindex.length; i++) {
      dirMapX.put(dindex[i], dx[i]);
      dirMapY.put(dindex[i], dy[i]);
    }
  }
  
  public void update() {
    if (maze.isAtCellCenter(x, y)) {
      if (maze.hasDotAt(x,y)) {
        maze.eatDotAt(x,y);
        notifyScoreIncrease();
      }
      if (!directionMovable(nextDir)) {
        nextDir = Direction.STILL;
      }
      dir = nextDir;
    }
    updatePosition();
  }

  protected void notifyScoreIncrease() {
    for (ScoreUpdateListener listener : scoreUpdateListeners) {
      listener.increaseScore();
    }
  }
  
  private boolean directionMovable(Direction dir) {
    int nextX = x + Maze.BLOCK_SIZE * dirMapX.get(dir);
    int nextY = y + Maze.BLOCK_SIZE * dirMapY.get(dir);
    return maze.isEmpty(nextX, nextY);
  }

  private void updatePosition() {
    if (dir != Direction.STILL) {
      x += dirMapX.get(dir) * STEP_SIZE;
      y += dirMapY.get(dir) * STEP_SIZE;
    }
  }

  Renderable getRenderable() {
    return new PacmanImage(this);
  }
  
  public int getX() { 
    return x; 
  }
  
  public int getY() { 
    return y; 
  }
  
  public Direction getDirection() {
    return dir;
  }
  
  public void setDirection(Direction dir) {
    this.dir = dir;
  }
  
  public void setNextDirection(Direction dir) {
    nextDir = dir;
  }

  public Direction getNextDirection() {
    return nextDir;
  }
  
  public void addScoreUpdateListener(ScoreUpdateListener listener) {
    scoreUpdateListeners.add(listener);
  }
}
