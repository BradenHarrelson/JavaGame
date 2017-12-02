import java.awt.event.KeyEvent;

public class SpaceShip extends Object {

    private Integer dx;
    private Integer dy;
    private Integer speed;

    public SpaceShip(){
        super(378, 500);
        speed = 4;
        dx = 0;
        dy = 0;
        initSpaceShip();

    }

    public void setSpeed(int speed){ this.speed = speed; }

    private void initSpaceShip() {
        loadObject("ship2.png");
    }

    public void move() {

        x += dx;
        y += dy;

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

    public void keyReleased(KeyEvent e) {
        dx = 0;
        dy = 0;
    }

}