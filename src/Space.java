import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Space extends JPanel implements ActionListener, KeyListener{

    private final Integer WIDTH = 800;
    private final Integer HEIGHT = 600;
    private ArrayList<Asteroid> asteroids;
    private SpaceShip ship;
    private Background background;
    private StartScreen startScreen;
    private HelpScreen helpScreen;
    private Dimension dimension;
    private Timer timer;
    private boolean gameOver;
    private boolean paused;
    private boolean displayHelp;
    private boolean isStartScreen;
    private long score;
    private long boost;
    private JButton start;
    private JButton restart;
    private JButton quit;
    private JButton help;

    private final int[][] pos = {
            {10, -304}, {50, -172}, {100, -29},
            {150, -109}, {200, -139}, {250, -139},
            {300, -59}, {350, -30}, {400, -200},
            {450, -259}, {500, -50}, {550, -90},
            {580, -220}, {175, -20}, {275, -80},
            {475, -80}, {420, -402}, {320, -500},
            {625, -280}, {650, -20}, {700, -115},
            {750, -320}, {778, -100}, {720, -30}
    };

    public Space() {
        startGame();
    }

    private void startGame(){
        score = 0;
        isStartScreen = true;
        displayHelp = false;
        gameOver = false;
        paused = false;
        dimension = new Dimension(WIDTH, HEIGHT);
        start = new JButton("PLAY");
        restart = new JButton("PLAY AGAIN");
        help = new JButton("HELP");
        quit = new JButton("QUIT");
        initSpace();
        initButtons();
        pause();
    }

    private void clearGame() {

        remove(quit);
        remove(help);
        remove(restart);
        remove(start);
        removeKeyListner(this);
    }

    private  void reStartGame(){
        clearGame();
        startGame();
    }

    private void initSpace() {

        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(dimension);
        addKeyListener(this);

        initStartScreen();
        initBackground();
        initMainMenu();
        initAsteroid();
        initSpaceship();
        initTimer();
    }

    private void initAsteroid(){

        asteroids = new ArrayList<>();
        for (int[] p : pos)
            asteroids.add(new Asteroid(p[0], p[1]));
    }

    private void initStartScreen() { startScreen = new StartScreen(); }

    private void initSpaceship(){
        ship = new SpaceShip();
    }

    private void initBackground(){
        background = new Background();
    }

    private void initMainMenu(){helpScreen = new HelpScreen(); }

    private void initTimer() {
        timer = new Timer(1, this);
        timer.start();
    }

    private void initButtons(){

        add(restart);
        add(start);
        add(help);
        add(quit);
        start.setBackground(Color.green);
        restart.setBackground(Color.green);
        help.setBackground(Color.yellow);
        quit.setBackground(Color.red);
        restart.setVisible(false);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHelp = false;
                isStartScreen = false;
                resume();
                start.setVisible(false);
                help.setVisible(false);
                quit.setVisible(false);

            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (displayHelp)
                    displayHelp = false;
                else {
                    displayHelp = true;
                    score -= 1;
                }
                repaint();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHelp = false;
                repaint();
                restart.setVisible(false);
                help.setVisible(false);
                quit.setVisible(false);
                reStartGame();
                start.setVisible(false);
                help.setVisible(false);
                quit.setVisible(false);
                isStartScreen = false;
                resume();
            }
        });

    }

    private void pause() { timer.stop(); }

    private void resume()
    {
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        if(isStartScreen) {
            if(!displayHelp)
                draw(graphics, startScreen);
            else
                draw(graphics, helpScreen);
        }
        else if(displayHelp)
            draw(graphics, helpScreen);
        else {
                draw(graphics, background);

                if (!gameOver) {
                    draw(graphics, ship);
                    drawAsteroids(graphics);
                    printScore(graphics, 5, 15, 15);
                    boostUp();
                } else {
                    pause();
                    restart.setVisible(true);
                    help.setVisible(true);
                    quit.setVisible(true);
                    printGameOver(graphics);
                }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void boostUp(){
        if(boost + 200 == score)
            ship.setSpeed(5);
        score += 1;
    }

    private void drawAsteroids(Graphics graphics) {
        for(Asteroid a : asteroids) {
            if (a.isValid())
                draw(graphics, a);
        }
    }

    private void draw(Graphics graphics, Object object){
        graphics.drawImage(object.getObject(), object.getX(), object.getY(), this);
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

    private void printScore(Graphics graphics, int x, int y, int size) {
        long msgL = score;
        String msg = "Score: " + String.valueOf(msgL);
        Font small = new Font("Helvetica", Font.BOLD, size);
        //FontMetrics fm = getFontMetrics(small);

        graphics.setColor(Color.yellow);
        graphics.setFont(small);
        graphics.drawString(msg, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent event){

        requestFocusInWindow();
        updateAsteroid();
        updateShip();

        collisionDetection();

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 66) {
            ship.setSpeed(10);
            boost = score;
        }
        if(e.getKeyCode() == 80) {
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
