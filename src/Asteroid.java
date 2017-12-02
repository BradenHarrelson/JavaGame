public class Asteroid extends Object {

    private int speed;
    private int initialY;
    private final Integer HEIGHT = 600;
    //initially draws it at the x,y coordinate

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
