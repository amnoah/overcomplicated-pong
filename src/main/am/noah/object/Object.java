package main.am.noah.object;

import main.am.noah.Pong;

public abstract class Object {

    /*
     * These are the main parameters which we are tracking in this parent class.
     * These are things which can be accessed by other classes by the design of the system.
     */

    // This is the Width and Height of the Object as it will be displayed/used with HitBoxes.
    private int width;
    private int height;

    // This is the position at which the top left corner of the object will be located.
    private int coordinateX;
    private int coordinateY;

    /*
     * These are our Getters.
     * This allows us to access the information saved within this class in an easy and neat manner.
     */

    /**
     * Return the Width of the object.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return the Height of the object.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the CoordinateX of the object.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Return the CoordinateY of the object.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /*
     * These are also Getters, but for a different topic.
     *
     * These coordinate-Getters are based off of the way our objects are rendered, allowing us to grab the coordinates
     * of certain sides of the object.
     *
     * While this can be easily recreated, this makes related code look much cleaner.
     */

    /**
     * Return the Left coordinate X of the object.
     */
    public int getLeftX() {
        return coordinateX;
    }

    /**
     * Return the Right coordinate X of the object.
     */
    public int getRightX() {
        return (coordinateX + width);
    }

    /**
     * Return the Top coordinate Y of the object.
     */
    public int getTopY() {
        return coordinateY;
    }

    /**
     * Return the Bottom coordinate Y of the object.
     */
    public int getBottomY() {
        return (coordinateY + height);
    }

    /*
     * These are our Setters.
     * This allows us to set the information saved within this class in an easy and neat manner.
     */

    /**
     * Set the Width of the object.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set the Height of the object.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Set the CoordinateX of the object.
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Set the CoordinateY of the object.
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /*
     * This function is meant to be overridden by any child class.
     * This will communicate data down the line.
     */

    public void handleFrame(double frameMultiplier, Pong pong) {
        // Empty
    }
}