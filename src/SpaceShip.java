import java.awt.event.KeyEvent;

public class SpaceShip extends Object {

    private Integer dx;
    private Integer dy;

    public SpaceShip(){
        super(300, 320);
        dx = 0;
        dy = 0;
        initSpaceShip();

    }

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

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        dx = 0;
        dy = 0;
    }

}