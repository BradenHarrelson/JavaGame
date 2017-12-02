//This is a generalized object class that the spaceship and asteroids will
//inherit from and extend the functionality. Both the sub classes will have
//different move operations....

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class Object {

    //x and y coordinate represent the top left corner of the image
    protected Integer x;
    protected Integer y;
    protected Integer width;
    protected Integer height;
    private boolean valid; //used to check if object is still on the screen
    protected Image object;

    public Object(int x, int y) {
        this.x = x;
        this.y = y;
        valid = true;
    }

    //objectName will be the name of a picture stored in the folder
    //passed in by the subclass spaceship or asteroid. (PNG, JPEG or GIF only)
    protected void loadObject(String objectName){
        ImageIcon objectImage = new ImageIcon(objectName);
        object = objectImage.getImage();

        extractDimensions();
    }

    //function can not be called until the object has been loaded
    protected void extractDimensions() {
        width = object.getWidth(null);
        height = object.getHeight(null);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    //object is required every time we want to redraw
    public Image getObject() {
        return object;
    }

    //we will need this to check for collision
    //this function can not be called until the object
    // calls the extractDimensions function
    //rectangle.intersect(rectangle) to check for collision to be
    //defined in board class
    public Rectangle getBoundingBox() {
        return new Rectangle(x+5, y+5, width-10, height-10);
    }
}
