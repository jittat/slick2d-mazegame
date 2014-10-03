package mazegame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PacmanImage implements Renderable {
  
  private Pacman pacman;
  private Image image;
  private int width;
  private int height;

  PacmanImage(Pacman pacman) {
    this.pacman = pacman;
    try {
      this.image = new Image("res/pacman.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
    width = image.getWidth();
    height = image.getHeight();
  }
  
  @Override
  public void render(Graphics g) {
    image.draw(pacman.getX() - width/2, 
        pacman.getY() - height/2);
  }

}
