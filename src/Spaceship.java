import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Spaceship extends Object implements KeyListener {
	private int dx, dy;
	public Spaceship(){
		super(300, 380);
		initSpaceShip();
	}
	private void initSpaceShip() {
		loadObject("changethis.png");
	}
	public void move() {
		x += dx;
		y += dy;
	}
	@Override
	public void keyPressed(KeyEvent e) {

	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	        dx = -1;
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        dx = 1;
	    }

	    if (key == KeyEvent.VK_UP) {
	        dy = -1;
	    }

	    if (key == KeyEvent.VK_DOWN) {
	        dy = 1;
	    }
	}
	@Override
	public void keyReleased(KeyEvent e) {
		dx = 0;
		dy = 0;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
