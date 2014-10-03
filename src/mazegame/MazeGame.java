package mazegame;

import java.util.HashMap;

import mazegame.Pacman.Direction;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MazeGame extends BasicGame {

  public static final int GAME_WIDTH = 640;
  public static final int GAME_HEIGHT = 480;
  private Maze maze;
  private Pacman pacman;
  private Renderable pacmanImage;
  private HashMap<Integer, Direction> keyMap;
  
  
  public MazeGame(String title) {
    super(title);
    initKeyMap();
  }
  
  private void initKeyMap() {
    keyMap = new HashMap<Integer, Pacman.Direction>();
    keyMap.put(Input.KEY_LEFT, Pacman.Direction.LEFT);
    keyMap.put(Input.KEY_RIGHT, Pacman.Direction.RIGHT);
    keyMap.put(Input.KEY_UP, Pacman.Direction.UP);
    keyMap.put(Input.KEY_DOWN, Pacman.Direction.DOWN);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    maze = new Maze();
    maze.init();
    pacman = new Pacman(maze.getCellCenterX(1, 1),
        maze.getCellCenterY(1, 1),
        Pacman.Direction.STILL,
        maze);
    pacmanImage = pacman.getRenderable();
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    maze.render();
    pacmanImage.render(g);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    pacman.update();
  }

  @Override
  public void keyPressed(int key, char c) {
    if (keyMap.containsKey(key)) {
      pacman.setNextDirection(keyMap.get(key));
    }
  }
  
  @Override
  public void keyReleased(int key, char c) {
    if (keyMap.containsKey(key)) {
      Pacman.Direction keyDirection = keyMap.get(key);
      if (pacman.getNextDirection() == keyDirection) {
        pacman.setNextDirection(Pacman.Direction.STILL);
      }
    }
  }
  
  public static void main(String[] args) {
    try {
      MazeGame game = new MazeGame("Bullet Game");
      AppGameContainer container = new AppGameContainer(game);
      container.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
      container.setMinimumLogicUpdateInterval(1000 / 60);
      container.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

}
