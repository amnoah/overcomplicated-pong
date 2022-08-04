package main.am.noah.object.player;

import main.am.noah.object.Object;

public class WallPlayer extends Object {

    /*
     * A WallPlayer never needs to move, so all we need to do is set the initial values.
     * You could just create a PongPlayer class for walls... but that would be very annoying to set up.
     */

    public WallPlayer(int x, int y, int height, int width) {
        setCoordinateX(x);
        setCoordinateY(y);
        setHeight(height);
        setWidth(width);
    }
}
