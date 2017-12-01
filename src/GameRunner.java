import javax.swing.*;
import java.awt.*;

public class GameRunner extends JFrame{

    public GameRunner() {
        initGame();
    }

    private void initGame() {
        JFrame spaceFrame = new JFrame("Space");
        JFrame mainFrame = new JFrame("Main Screen");

        spaceFrame.add(new MainMenu());

        spaceFrame.add(new Space());

        spaceFrame.setResizable(true);
        spaceFrame.pack();
        spaceFrame.setTitle("Spaceship Game");
        spaceFrame.setLocationRelativeTo(null);
        spaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spaceFrame.setVisible(true);
    }

    public static void main(String[] args){

        EventQueue.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        //would work if we just put these 2 lines but is better
                        //practice to use a event queue
                        GameRunner runner = new GameRunner();
                    }
                }
        );

    }
}
