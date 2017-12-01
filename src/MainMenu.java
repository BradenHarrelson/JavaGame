import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainMenu extends JPanel implements MouseListener{
	private static final int DEFAULT_VERTICAL_PADDING = 20;
	// The background color
    public MainMenu() {
		setBackground(Color.BLACK);
		
		// Create the image header for the MainMenu
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout());
		headerPanel.setBackground(Color.BLACK);

		JLabel mainIcon = new JLabel("");
		try {
			mainIcon.setIcon(new ImageIcon(ImageIO.read(new File("MainMenu.png"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		headerPanel.add(mainIcon);
		
		// Create the panel which will contain the central elements and absorb any vertical stretch
		JPanel stretchPanel = new JPanel();
		stretchPanel.setLayout(new BorderLayout());
		
		// Create the panel containing the controls
		JPanel buttonsPanel = new JPanel();
		GridLayout buttonsLayout = new GridLayout(0,1);
		buttonsLayout.setVgap(5);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);
		stretchPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Create the start button
		JButton playE = new JButton("Start Game!");
		//playE.addActionListener(new PlayGameListener(EASY_SPAWN_RATE));
		playE.setBackground(Color.GREEN);
		buttonsPanel.add(playE);
		
		// Create the help button
		JButton help = new JButton("Help");
		//help.addActionListener(new PlayGameListener(EASY_SPAWN_RATE));
		help.setBackground(Color.RED);
		buttonsPanel.add(help);
		
		// Create the credits button
		JButton credits = new JButton("Credits");
		//credits.addActionListener(new PlayGameListener(EASY_SPAWN_RATE));
		credits.setBackground(Color.YELLOW);
		buttonsPanel.add(credits);
		
		// Create the padding below the play game buttons
		JPanel basePadding = new JPanel();
		basePadding.setBackground(Color.BLACK);
		basePadding.setPreferredSize(new Dimension(DEFAULT_VERTICAL_PADDING, DEFAULT_VERTICAL_PADDING));
		stretchPanel.add(basePadding, BorderLayout.CENTER);
		
		add(stretchPanel, BorderLayout.CENTER);
		
		// Create the quit button
		JButton quit = new JButton("Quit Game");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Quit the program
				System.exit(0);
			}
		});
		quit.setBackground(Color.LIGHT_GRAY);
		add(quit, BorderLayout.SOUTH);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
