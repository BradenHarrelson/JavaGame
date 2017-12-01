import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Space extends JPanel implements ActionListener, KeyListener{

    private final Integer WIDTH = 600;
    private final Integer HEIGHT = 400;
    private ArrayList<Asteroid> asteroids;
    private SpaceShip ship;
    private Background background;
    private Dimension dimension;
    private Timer timer;
    private boolean gameOver;
    private boolean paused;
    private long score;

    private final int[][] pos = {
            {10, -304}, {50, -172}, {100, -29},
            {150, -109}, {200, -139}, {250, -139},
            {300, -59}, {350, -30}, {400, -200},
            {450, -259}, {500, -50}, {550, -90},
            {580, -220}, {175, -20}, {275, -80},
            {475, -80}, {420, -402}, {320, -500}
    };

    public Space() {
    	score = 0;
        gameOver = false;
        paused = false;
        dimension = new Dimension(WIDTH, HEIGHT);
        initSpace();
    }

        private void initSpace() {

        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(dimension);
        addKeyListener(this);

        initBackground();

            initAsteroid();
            initSpaceship();

            timer = new Timer(1, this);
            timer.start();
    }

    private void initAsteroid(){

        asteroids = new ArrayList<>();
        for (int[] p : pos)
            asteroids.add(new Asteroid(p[0], p[1]));
    }

    private void initSpaceship(){
        ship = new SpaceShip();
    }

    private void initBackground(){
        background = new Background();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        graphics.drawImage(background.getObject(), background.getX(), background.getY(), this);

        if (!gameOver) {
            graphics.drawImage(ship.getObject(), ship.getX(), ship.getY(), this);
            drawAsteroids(graphics);
            printScore(graphics, 5, 15, 15);
            score += 1;
        }
        else {
            pause();
            printGameOver(graphics);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    public boolean isGameOver(){
        return gameOver;
    }

    private void printGameOver(Graphics graphics) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 34);
        FontMetrics fm = getFontMetrics(small);

        graphics.setColor(Color.white);
        graphics.setFont(small);
        graphics.drawString(msg, (WIDTH - fm.stringWidth(msg)) / 2,
                HEIGHT / 2);
        printScore(graphics, (WIDTH - fm.stringWidth(msg)) / 2 + 20,
        HEIGHT / 2 + 40, 25);
    }

    private void drawAsteroids(Graphics graphics) {
        for(Asteroid a : asteroids) {
            if (a.isValid())
                graphics.drawImage(a.getObject(), a.getX(), a.getY(), this);
        }
    }

    private void pause()
    {
        timer.stop();
    }

    private void resume()
    {
        timer.restart();
    }

    @Override
    public void actionPerformed(ActionEvent event){

        updateAsteroid();
        updateShip();

        collisionDetection();

        repaint();
    }

    private void updateAsteroid(){
        for (Asteroid asteroid : asteroids) {
            if (asteroid.isValid())
            	asteroid.move();
            	if (score % 1000 == 0) {
            		asteroid.upSpeed();
            	}
        }
    }

    private void updateShip(){
        if (ship.isValid())
            ship.move();
    }

    private void collisionDetection()
    {
        for (Asteroid asteroid : asteroids) {
            if (ship.getBoundingBox().intersects(asteroid.getBoundingBox())) {
                ship.setValid(false);
                asteroid.setValid(false);
                gameOver = true;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
           if(!paused) {
               pause();
               paused = true;
           }
           else {
               resume();
               paused = false;
           }
        }

        ship.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ship.keyReleased(e);
    }
    
    public void printScore(Graphics graphics, int x, int y, int size) {
        long msgL = score;
        String msg = "Score: " + String.valueOf(msgL);
        Font small = new Font("Helvetica", Font.BOLD, size);
        FontMetrics fm = getFontMetrics(small);

        graphics.setColor(Color.yellow);
        graphics.setFont(small);
        graphics.drawString(msg, x, y);
    }

}
