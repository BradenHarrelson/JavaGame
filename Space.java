import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Space extends JPanel implements ActionListener{

  private final Integer WIDTH = 600;
  private final Integer HEIGHT = 400;
  private Asteroid asteroid;
  private Dimension dimension = new Dimension(WIDTH, HEIGHT);
  private Timer timer;

  public Space() {
    initSpace();
  }

  private void initSpace() {
    setFocusable(true);
    setBackground(Color.BLACK);
    setPreferredSize(dimension);

    initAsteroid();

    timer = new Timer(1, this);
    timer.start();
  }

  private void initAsteroid(){
    asteroid = new Asteroid(300, 0);
  }

  @Override
  public void paintComponent(Graphics graphics){
    super.paintComponent(graphics);

    graphics.drawImage(asteroid.getObject(), asteroid.getX(), asteroid.getY(), this);

    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent event){
    updateAsteroid();
    repaint();
  }

  public void updateAsteroid(){
    asteroid.move();
  }

}
