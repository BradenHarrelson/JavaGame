import javax.swing.*;
import java.awt.*;

public class GameRunner extends JFrame{

  public GameRunner() {
    initGame();
  }

  private void initGame() {
    add(new Space());

    setResizable(true);
    pack();
    setTitle("Spaceship Game");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args){

    EventQueue.invokeLater(
        new Runnable() {
          @Override
          public void run() {
            GameRunner runner = new GameRunner();
            runner.setVisible(true);
          }
        }
        );
  }
}
