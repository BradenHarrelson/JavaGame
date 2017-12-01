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

    private final int[][] pos = {
            {238, 29}, {25, 59}, {580, 89},
            {80, 109}, {58, 139}, {80, 139},
            {440, 59}, {299, 30}, {220, 200},
            {539, 259}, {66, 50}, {54, 90},
            {281, 220}, {186, 20}, {274, 80}
    };

    public Space() {

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

}
