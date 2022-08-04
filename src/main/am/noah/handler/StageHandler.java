package main.am.noah.handler;

import main.am.noah.Pong;
import main.am.noah.object.ball.Ball;
import main.am.noah.object.player.ComputerPlayer;
import main.am.noah.object.player.KeyboardPlayer;
import main.am.noah.object.player.WallPlayer;

public class StageHandler {

    Pong pong;

    /**
     * Declare a public enum titled "Stage".
     * This allows us to store information about what Stage is current active in a neat manner.
     */
    public enum Stage {
        MAIN_SCREEN,
        SELECTION_SCREEN_ONE,
        SELECTION_SCREEN_TWO,
        CONTROL_SCREEN,
        ACTIVE_GAME,
        PAUSE_MENU
    }

    private Stage currentStage = Stage.MAIN_SCREEN;

    // What are the Width and Height of any regular paddles?
    private static int PADDLE_HEIGHT = 0;
    private static int PADDLE_WIDTH = 0;

    // What is the X coordinate of any Left or Right paddles?
    private static int LEFT_PADDLE_X = 0;
    private static int RIGHT_PADDLE_X = 0;

    // What key codes move the Left paddle up/down?
    private static int LEFT_KEY_UP = 0;
    private static int LEFT_KEY_DOWN = 0;

    // What key codes move the Right paddle up/down?
    private static int RIGHT_KEY_UP = 0;
    private static int RIGHT_KEY_DOWN = 0;

    // What is the starting Y coordinate of any regular paddle?
    private static int PADDLE_Y = 0;

    // What is the amount of pixels per second the paddles should move?
    private static int PADDLE_MOVEMENT_SPEED = 0;

    // What are the parameters for the ball object?
    private static int BALL_DIAMETER = 0;
    private static int BALL_SPEED = 0;
    private static int BALL_SPEED_INCREASE = 0;

    // Track whether the left/right paddle is player operated.
    private boolean leftPlayer = false;
    private boolean rightPlayer = false;

    /**
     * Return the converted version of the Left Key Down keycode.
     */
    public String getLeftKeyDown() {
        return java.awt.event.KeyEvent.getKeyText(LEFT_KEY_DOWN);
    }

    /**
     * Return the converted version of the Left Key Up keycode.
     */
    public String getLeftKeyUp() {
        return java.awt.event.KeyEvent.getKeyText(LEFT_KEY_UP);
    }

    /**
     * Return the converted version of the Right Key Down keycode.
     */
    public String getRightKeyDown() {
        return java.awt.event.KeyEvent.getKeyText(RIGHT_KEY_DOWN);
    }

    /**
     * Return the converted version of the Right Key Up keycode.
     */
    public String getRightKeyUp() {
        return java.awt.event.KeyEvent.getKeyText(RIGHT_KEY_UP);
    }

    /**
     * Return if the left paddle is player operated.
     */
    public boolean isLeftPlayer() {
        return leftPlayer;
    }

    /**
     * Return if the right paddle is player operated.
     */
    public boolean isRightPlayer() {
        return rightPlayer;
    }

    /**
     * Upon initialization of this class set our Pong accessor and a bunch of necessary parameters (located here for
     * ease of editing).
     */
    public StageHandler(Pong pong) {
        this.pong = pong;

        PADDLE_HEIGHT = 100;
        PADDLE_WIDTH = 25;

        LEFT_PADDLE_X = 10;
        RIGHT_PADDLE_X = pong.getBaseWindowWidth() - 10 - PADDLE_WIDTH;

        LEFT_KEY_UP = 87;
        LEFT_KEY_DOWN = 83;

        RIGHT_KEY_UP = 38;
        RIGHT_KEY_DOWN = 40;

        PADDLE_Y = (pong.getBaseWindowHeight() / 2) - (PADDLE_HEIGHT / 2);

        PADDLE_MOVEMENT_SPEED = 625;

        BALL_DIAMETER = 50;
        BALL_SPEED = 200;
        BALL_SPEED_INCREASE = 15;
    }

    /**
     * Return the Stage that the game is currently in.
     */
    public Stage getCurrentStage() {
        return currentStage;
    }

    public void handleInput(char keyChar) {
        switch (currentStage) {
            case MAIN_SCREEN:
                currentStage = Stage.SELECTION_SCREEN_ONE;

                break;
            case SELECTION_SCREEN_ONE:

                /*
                 * Within the SELECTION_SCREEN_ONE stage the player can use the "1", "2", or "3" keys in order to set
                 * the Left paddle as a certain type.
                 *
                 * "1" - A Computer operated paddle.
                 * "2" - A Player operated paddle.
                 * "3" - A Wall paddle (just a wall to practice against).
                 */

                switch (keyChar) {
                    case '1':

                        /*
                         * If the player inputs "1" we progress to the SELECTION_SCREEN_TWO stage and spawn a
                         * ComputerPlayer on the Left side.
                         */

                        currentStage = Stage.SELECTION_SCREEN_TWO;

                        pong.getObjects().add(new ComputerPlayer(
                                LEFT_PADDLE_X,
                                PADDLE_Y,
                                PADDLE_HEIGHT,
                                PADDLE_WIDTH,
                                PADDLE_MOVEMENT_SPEED,
                                0,
                                pong.getBaseWindowHeight() - PADDLE_HEIGHT
                        ));

                        break;
                    case '2':

                        /*
                         * If the player inputs "2" we progress to the SELECTION_SCREEN_TWO stage and spawn a
                         * KeyboardPlayer on the Left side.
                         */

                        currentStage = Stage.SELECTION_SCREEN_TWO;

                        pong.getObjects().add(new KeyboardPlayer(
                                LEFT_PADDLE_X,
                                PADDLE_Y,
                                PADDLE_HEIGHT,
                                PADDLE_WIDTH,
                                LEFT_KEY_UP,
                                LEFT_KEY_DOWN,
                                PADDLE_MOVEMENT_SPEED,
                                0,
                                pong.getBaseWindowHeight() - PADDLE_HEIGHT
                        ));

                        leftPlayer = true;

                        break;
                    case '3':

                        /*
                         * If the player inputs "3" we progress to the SELECTION_SCREEN_TWO stage and spawn a
                         * WallPlayer on the Left side.
                         */

                        currentStage = Stage.SELECTION_SCREEN_TWO;

                        pong.getObjects().add(new WallPlayer(
                                LEFT_PADDLE_X,
                                0,
                                pong.getBaseWindowHeight(),
                                PADDLE_WIDTH
                        ));

                        break;
                }

                break;
            case SELECTION_SCREEN_TWO:

                /*
                 * Within the SELECTION_SCREEN_TWO stage the player can use the "1", "2", or "3" keys in order to set
                 * the Right paddle as a certain type.
                 *
                 * "1" - A Computer operated paddle.
                 * "2" - A Player operated paddle.
                 * "3" - A Wall paddle (just a wall to practice against).
                 */

                switch (keyChar) {
                    case '1':

                        /*
                         * If the player inputs "1" we progress to the CONTROL_SCREEN stage and spawn a ComputerPlayer on
                         * the right side.
                         */

                        currentStage = Stage.CONTROL_SCREEN;

                        pong.getObjects().add(new ComputerPlayer(
                                RIGHT_PADDLE_X,
                                PADDLE_Y,
                                PADDLE_HEIGHT,
                                PADDLE_WIDTH,
                                PADDLE_MOVEMENT_SPEED,
                                0,
                                pong.getBaseWindowHeight() - PADDLE_HEIGHT
                        ));

                        break;
                    case '2':

                        /*
                         * If the player inputs "2" we progress to the CONTROL_SCREEN stage and spawn a KeyboardPlayer on
                         * the right side.
                         */

                        currentStage = Stage.CONTROL_SCREEN;

                        pong.getObjects().add(new KeyboardPlayer(
                                RIGHT_PADDLE_X,
                                PADDLE_Y,
                                PADDLE_HEIGHT,
                                PADDLE_WIDTH,
                                RIGHT_KEY_UP,
                                RIGHT_KEY_DOWN,
                                PADDLE_MOVEMENT_SPEED,
                                0,
                                pong.getBaseWindowHeight() - PADDLE_HEIGHT
                        ));

                        rightPlayer = true;

                        break;
                    case '3':

                        /*
                         * If the player inputs "3" we progress to the CONTROL_SCREEN stage and spawn a WallPlayer on
                         * the right side.
                         */

                        currentStage = Stage.CONTROL_SCREEN;

                        pong.getObjects().add(new WallPlayer(
                                RIGHT_PADDLE_X,
                                0,
                                pong.getBaseWindowHeight(),
                                PADDLE_WIDTH
                        ));

                        break;
                }

                break;
            case CONTROL_SCREEN:
                /*
                 * When we receive any input on the CONTROL_SCREEN the player progresses to the ACTIVE_GAME stage and
                 * the Ball object is spawned.
                 */
                currentStage = Stage.ACTIVE_GAME;

                // Create a ball object with all of the proper inputs.
                pong.getObjects().add(new Ball(
                        (pong.getBaseWindowWidth() / 2) - (BALL_DIAMETER / 2),
                        (pong.getBaseWindowHeight() / 2) - (BALL_DIAMETER / 2),
                        BALL_DIAMETER,
                        BALL_SPEED,
                        BALL_SPEED_INCREASE,
                        15,
                        pong.getBaseWindowHeight(),
                        pong.getBaseWindowWidth()
                ));

                break;

            /*
             * We can press the "p" key in order to swap between the ACTIVE_GAME and PAUSE_MENU stage.
             */

            case ACTIVE_GAME:
                if (keyChar == 'p') {
                    currentStage = Stage.PAUSE_MENU;
                }

                break;
            case PAUSE_MENU:
                if (keyChar == 'p') {
                    currentStage = Stage.ACTIVE_GAME;
                }

                break;
        }
    }
}
