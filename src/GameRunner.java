import javax.swing.*;
import java.awt.*;

public class GameRunner extends JFrame{

    public GameRunner() {
        initGame();
    }

    private void initGame() {

        //adds the space panel to the frame
        //sets variables of the frame as appropriate
        add(new Space());
        setResizable(false);
        pack();
        setTitle("Asteroid Runner");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
