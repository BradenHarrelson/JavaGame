public class Asteroid extends Object {

   private int speed;

    //initially draws it at the x,y coordinate
    public Asteroid(int x, int y) {
        super(x, y);
        speed = 0;
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
        if (y > 400)
            y = 0;
         y = y + speed;
    }
}
