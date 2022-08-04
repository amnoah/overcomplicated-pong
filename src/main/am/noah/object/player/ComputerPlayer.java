package main.am.noah.object.player;

import main.am.noah.Pong;
import main.am.noah.object.Object;
import main.am.noah.object.ball.Ball;

public class ComputerPlayer extends Object {

    private static int MOVEMENT_SPEED = 0;
    private static int MINIMUM_Y = 0;
    private static int MAXIMUM_Y = 0;

    public ComputerPlayer(int x, int y, int height, int width, int movementSpeed, int minimumY, int maximumY) {
        setCoordinateX(x);
        setCoordinateY(y);
        setHeight(height);
        setWidth(width);

        MOVEMENT_SPEED = movementSpeed;
        MINIMUM_Y = minimumY;
        MAXIMUM_Y = maximumY;
    }

    /**
     * Tell the ComputerPlayer object a frame has started.
     */
    @Override
    public void handleFrame(double frameMultiplier, Pong pong) {
        int x = Integer.MAX_VALUE;
        int y = 0;

        // O(n) time complexity method for finding the closest ball.
        for (Object pongBall : pong.getObjects()) {

            // Only check the position of Balls.
            if (pongBall instanceof Ball) {

                /*
                 * Because of the fact that we don't know whether the computer operated paddle is on the left or right we
                 * should try to find the smallest distance to the ball, whether that be to the ball's right edge or left edge.
                 */
                final int distanceToLeftEdge = Math.abs(getRightX() - pongBall.getLeftX());
                final int distanceToRightEdge = Math.abs(getLeftX() - pongBall.getRightX());

                // Use the smaller of the two distances for our actual process.
                final int distance = Math.min(distanceToLeftEdge, distanceToRightEdge);

                /*
                 * If the distance is shorter than our current recorded X, update the X and Y integers as this is our new
                 * closest ball.
                 */
                if (distance < x) {
                    x = distance;
                    // Make the paddle attempt to get the ball to hit it in its center.
                    y = pongBall.getCoordinateY() - ((getHeight() - pongBall.getHeight()) / 2);
                }
            }
        }

        // If the ball is above the paddle, move the paddle up. If the ball is below the paddle, move the paddle down.
        if (y < getCoordinateY())
            setCoordinateY((int) Math.max(getCoordinateY() - (MOVEMENT_SPEED * frameMultiplier), MINIMUM_Y));
        else
            setCoordinateY((int) Math.min(getCoordinateY() + (MOVEMENT_SPEED * frameMultiplier), MAXIMUM_Y));
    }
}
