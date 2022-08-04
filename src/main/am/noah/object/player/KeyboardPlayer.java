package main.am.noah.object.player;

import main.am.noah.Pong;
import main.am.noah.object.Object;

import java.util.List;

public class KeyboardPlayer extends Object {

    private static int MOVEMENT_SPEED = 0;
    private static int MINIMUM_Y = 0;
    private static int MAXIMUM_Y = 0;

    // What keycode should be pressed to move the paddle up?
    private final int inputUp;
    // What keycode should be pressed to move the paddle down?
    private final int inputDown;

    public KeyboardPlayer(int x, int y, int height, int width, int inputUp, int inputDown, int movementSpeed, int minimumY, int maximumY) {
        setCoordinateX(x);
        setCoordinateY(y);
        setHeight(height);
        setWidth(width);

        this.inputUp = inputUp;
        this.inputDown = inputDown;

        MOVEMENT_SPEED = movementSpeed;
        MINIMUM_Y = minimumY;
        MAXIMUM_Y = maximumY;

        System.out.println(getHeight());
    }

    /**
     * Tell the KeyboardPlayer object a frame has started.
     */
    @Override
    public void handleFrame(double frameMultiplier, Pong pong) {
        List<Integer> inputs = pong.getInputHandler().getInputs();

        if (inputs.contains(inputUp)) setCoordinateY((int) Math.max(getCoordinateY() - (MOVEMENT_SPEED * frameMultiplier), MINIMUM_Y));
        if (inputs.contains(inputDown)) setCoordinateY((int) Math.min(getCoordinateY() + (MOVEMENT_SPEED * frameMultiplier), MAXIMUM_Y));
    }
}
