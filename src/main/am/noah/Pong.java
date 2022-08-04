package main.am.noah;

import main.am.noah.handler.FrameHandler;
import main.am.noah.handler.InputHandler;
import main.am.noah.handler.StageHandler;
import main.am.noah.handler.WindowHandler;
import main.am.noah.object.Object;

import java.util.ArrayList;
import java.util.List;

public class Pong {

    private final static int BASE_WINDOW_WIDTH = 1600;
    private final static int BASE_WINDOW_HEIGHT = 800;

    private final FrameHandler frameHandler;
    private final InputHandler inputHandler;
    private final StageHandler stageHandler;
    private final WindowHandler windowHandler;

    private final List<Object> objects = new ArrayList<>();

    /**
     * Initialize all of our different handlers on the initialization of Pong.
     */
    public Pong() {
        frameHandler = new FrameHandler(this);
        inputHandler = new InputHandler(this);
        stageHandler = new StageHandler(this);
        windowHandler = new WindowHandler(this);

        frameHandler.timer.start();
    }

    /*
     * These are our Getters.
     * This allows us to access the information saved within this class in an easy and neat manner.
     */

    /**
     * Return the original height of the displayed window.
     */
    public int getBaseWindowHeight() {
        return BASE_WINDOW_HEIGHT;
    }

    /**
     * Return the original width of the displayed window.
     */
    public int getBaseWindowWidth() {
        return BASE_WINDOW_WIDTH;
    }

    /**
     * Return the FrameHandler object.
     */
    public FrameHandler getFrameHandler() {
        return frameHandler;
    }

    /**
     * Return the InputHandler object.
     */
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    /**
     * Return the StageHandler object.
     */
    public StageHandler getStageHandler() {
        return stageHandler;
    }

    /**
     * Return the WindowHandler object.
     */
    public WindowHandler getWindowHandler() {
        return windowHandler;
    }

    /**
     * Return the list of tracked Objects.
     */
    public List<Object> getObjects() {
        return objects;
    }

    /**
     * Run appropriate actions whenever the FrameHandler calls a frame.
     */
    public void handleFrame(double frameMultiplier) {
        if (stageHandler.getCurrentStage() == StageHandler.Stage.ACTIVE_GAME) {

            // Handle Ball and Paddle movement.
            for (Object object : objects) {
                object.handleFrame(frameMultiplier, this);
            }
        }

        // Display the final result
        windowHandler.repaint();
    }
}
