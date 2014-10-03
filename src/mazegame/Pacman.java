package mazegame;

public class Pacman {
  public enum Direction {
    LEFT, RIGHT, UP, DOWN, STILL
  }
  
  private int x;
  private int y;
  private Direction dir;

  Pacman(int x, int y, Direction dir) {
    this.x = x;
    this.y = y;
    this.dir = dir;
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
}
