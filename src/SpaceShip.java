import java.awt.event.KeyEvent;

public class SpaceShip extends Object {

    private Integer dx;
    private Integer dy;
    private Integer speed;

    //constructs a ship at the given coordinates
    //dx and dy are the change in x and y
    public SpaceShip(){
        super(378, 500);
        speed = 4;
        dx = 0;
        dy = 0;
        initSpaceShip();

    }

    //decides how fast the ship will go on the press of the arrow keys
    public void setSpeed(int speed){ this.speed = speed; }

    private void initSpaceShip() {
        loadObject("ship2.png");
    }

    //moves the object
    public void move() {

        //moves the ship top left coordinate
        x += dx;
        y += dy;

        //the if conditions make sure the ship doesn't go out of the screen
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
        if (x > 764) {
            x = 764;
        }

        if (y > 548) {
            y = 548;
        }

    }

    //logic based on when key is pressed
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -speed;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = speed;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -speed;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = speed;
        }
    }

    //reset speed to 0 when key is released
    public void keyReleased(KeyEvent e) {
        dx = 0;
        dy = 0;
    }

}