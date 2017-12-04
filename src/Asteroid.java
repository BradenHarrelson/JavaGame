public class Asteroid extends Object {

    private int speed;
    private int initialY; //used to redraw once it does out of the screen at the bottom
    private final Integer HEIGHT = 600;

    //initial coordinates are fed in from the space class
    public Asteroid(int x, int y) {
        super(x, y);
        speed = 1;
        initialY = y;
        initAsteroid();
    }

    private void initAsteroid() {

        loadObject("asteroid.png");
    }

    public void upSpeed() {
        speed += 1;
    }

    //moves it straight down
    public void move() {
        if (y > HEIGHT)
            y = initialY;
        y = y + speed;
    }
}
