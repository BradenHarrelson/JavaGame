public class Asteroid extends Object {

   // private final Integer MAXYSIZE = 400;

    //initially draws it at the x,y coordinate
    public Asteroid(int x, int y) {
        super(x, y);
        initAsteroid();
    }

    private void initAsteroid() {

        loadObject("asteroid.png");
    }

    //moves it straight down
    public void move() {
        if (y > 400)
            y = 0;
         y = y+1;
    }
}
