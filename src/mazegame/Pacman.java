package mazegame;

public class Pacman {
  public enum Direction {
    LEFT, RIGHT, UP, DOWN, STILL
  }
  public static final int STEP_SIZE = 5;
  
  private int x;
  private int y;
  private Direction dir;
  private MazeGame game;
  private Maze maze;

  private Direction nextDir;

  Pacman(int x, int y, Direction dir, Maze maze) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.maze = maze;
    nextDir = Direction.STILL;
  }

  public void update() {
    if (maze.isAtCellCenter(x,y)) {
      dir = nextDir;
    }
    updatePosition();
  }
  
  private void updatePosition() {
    switch (dir) {
    case LEFT: 
      x -= STEP_SIZE;
      break;
    case RIGHT: 
      x += STEP_SIZE;
      break;
    case UP: 
      y -= STEP_SIZE;
      break;
    case DOWN: 
      y += STEP_SIZE;
      break;
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
}
