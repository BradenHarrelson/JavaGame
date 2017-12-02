import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRunner extends JFrame{

    private JList mylist;
    private DefaultListModel model;

    public GameRunner() {
        initGame();
    }

    private void initGame() {

        JPanel panel = new JPanel();

        JButton start_game = new JButton("Play");
        JButton quit = new JButton("Quit");;

        setTitle("Asteroid");
        setSize(200, 200);
        panel.setBackground(Color.black);

        panel.add(start_game);
        panel.add(quit);
        // And JPanel needs to be added to the JFrame itself!
        this.getContentPane().add(panel);

        start_game.setBackground(Color.green);
        quit.setBackground(Color.red);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        start_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() instanceof JButton) {
                    setVisible(false);
                    JFrame spaceFrame = new JFrame("Space");
                    spaceFrame.add(new Space());
                    spaceFrame.setResizable(true);
                    spaceFrame.pack();
                    spaceFrame.setTitle("Asteroid");
                    spaceFrame.setLocationRelativeTo(null);
                    spaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    spaceFrame.setVisible(true);
                }
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

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
