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
    private  Timer timer;
    private boolean gameOver;
    private boolean paused;
    private long score;
    private long boost;
    private JButton start;
    private JButton restart;
    private JButton quit;
    private JButton help;

    //coordinates of all the initial asteroids
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

    //things to be done at the start of the game
    private void startGame(){
        score = 0;
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

        helpScreen.setValid(false);
        startScreen.setValid(true);
    }

    //when restarting the game thus function clears the buttons and key listener
    //otherwise we would end up adding more buttons every time.
    //if keylistner is not removed but just overwritten it won't read in the keys
    //properly and give unexpected errors
    private void clearGame() {

        remove(quit);
        remove(help);
        remove(restart);
        remove(start);
        removeKeyListener(this);
    }

    //play again sequence
    private  void reStartGame(){
        clearGame();
        startGame();
    }

    //initializes the window/pannel and initializes all the objects to be drawn
    //at the moment or later
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

    //range based loop to dynamically create new asteroids at runtime
    private void initAsteroid(){

        int i = 0;
        asteroids = new ArrayList<>();
        for (int[] p : pos){
            asteroids.add(new Asteroid(p[0], p[1]));
            if(i % 3 == 0) {
                asteroids.get(i).upSpeed();
                if(i % 6 == 0)
                    asteroids.get(i).upSpeed();
            }
            i = i+1;
        }

    }

    private void initStartScreen() { startScreen = new StartScreen(); }

    private void initSpaceship(){
        ship = new SpaceShip();
    }

    private void initBackground(){
        background = new Background();
    }

    private void initMainMenu(){helpScreen = new HelpScreen(); }

    //create a timer, the delay means redraw after that much time(nanoseconds. i think?)
    private void initTimer() {
        timer = new Timer(1, this);
        timer.start();
    }

    //adds the buttons to the panel and sets their properties
    //defines the action to be performed on click of the button
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

        //defines what to draw and what not to in all teh buttons
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpScreen.setValid(false);
                startScreen.setValid(false);
                resume();
                start.setVisible(false);
                help.setVisible(false);
                quit.setVisible(false);

            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (helpScreen.isValid())
                    helpScreen.setValid(false);
                else {
                    helpScreen.setValid(true);
                    score -= 1;
                }
                repaint(); //calls the paintcomponent function
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
                helpScreen.setValid(false);
                repaint();
                restart.setVisible(false);
                help.setVisible(false);
                quit.setVisible(false);
                reStartGame();
                start.setVisible(false);
                help.setVisible(false);
                quit.setVisible(false);
                startScreen.setValid(false);
                resume();
            }
        });

    }

    private void pause() { timer.stop(); }

    private void resume()
    {
        timer.start();
    }

    //this function is called ever time we call the repaint command
    //it defines what to draw and what not to based on the state of
    //the game.
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        if(startScreen.isValid()) {
            if(!helpScreen.isValid())
                draw(graphics, startScreen);
            else
                draw(graphics, helpScreen);
        }
        else if(helpScreen.isValid())
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

    //this function speeds up the asteroids every 1000 points and updates the coordinates
    //by calling the move command
    private void updateAsteroid(){
        for (Asteroid asteroid : asteroids) {
            if (asteroid.isValid())
                asteroid.move();
            if (score % 1000 == 0) {
                asteroid.upSpeed();
            }
        }
    }

    //updates the ship coordinates
    private void updateShip(){
        if (ship.isValid())
            ship.move();
    }

    //when the bounding boxes intersect the following asteroid and
    //ship is destroyed. and hence not redrawn.
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

        graphics.setColor(Color.yellow);
        graphics.setFont(small);
        graphics.drawString(msg, x, y);
    }

    //this function is called based on the timer delay. if we do not set up the
    //timer the function wont be called.
    @Override
    public void actionPerformed(ActionEvent event){

        requestFocusInWindow();
        updateAsteroid();
        updateShip();

        collisionDetection();

        repaint();
    }

    //the following three functions listen to the interrupts by keys and
    //perform the appropriate action.
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
