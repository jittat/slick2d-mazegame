package mazegame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MazeGame extends BasicGame {

  public static final int GAME_WIDTH = 640;
  public static final int GAME_HEIGHT = 480;
  private Maze maze;
  private Pacman pacman;
  private Renderable pacmanImage;
  
  public MazeGame(String title) {
    super(title);
  }
  
  @Override
  public void init(GameContainer container) throws SlickException {
    maze = new Maze();
    pacman = new Pacman(maze.getCellCenterX(1, 1),
        maze.getCellCenterY(1, 1),
        Pacman.Direction.STILL);
    pacmanImage = pacman.getRenderable();
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    maze.render();
    pacmanImage.render(g);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
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
