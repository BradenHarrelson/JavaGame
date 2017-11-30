public class Asteroid extends Objects {

  private final Integer MAXYSIZE = 300;

  //initially draws it at the x,y coordinate
  public Asteroid(int x, int y) {
    super(x, y);
    initAsteroid();
  }

  private void initAsteroid() {
    loadObject("nameoffile.png");
  }

  //moves it straight down
  public void move() {
    y = y-1;
  }
}
