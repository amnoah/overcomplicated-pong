package main.am.noah.handler;

import main.am.noah.Pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {

    Pong pong;

    private final List<Integer> inputs;

    public InputHandler(Pong pong) {
        this.pong = pong;
        inputs = new ArrayList<>();
    }

    /**
     * Return a list of all the current held inputs.
     */
    public List<Integer> getInputs() {
        return inputs;
    }

    /**
     * This method does tell us when a key is pressed, but it has limitations.
     *
     * This method will continuously be triggered if a key is held down, but not synced with our desired framerate.
     * If this was used to move paddles, it could lead to them being moved a-sync from the game itself.
     *
     * This also does not support multiple keys being pressed.
     * If you do press multiple, you will only get inputs from the latest pressed.
     *
     * Thus, we don't use this for game inputs as there are better alternatives.
     * However, it is safe for Menu usage, such as Pause/Main screen inputs.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        pong.getStageHandler().handleInput(e.getKeyChar());
    }

    /**
     * This method allows us to know when a player starts pressing a key.
     * We can add the key code to a list of keys currently pressed, allowing us to know what keys are being pressed at any time.
     * We can then access this list on a frame, meaning that paddle movements will be synced with the game.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // I'm not sure if there are methods to double input (like alt tabbing?), so better safe than sorry.
        if (!inputs.contains(e.getKeyCode())) inputs.add(e.getKeyCode());
    }

    /**
     * In order for the list of inputs idea to work we also need to remove inputs when they're released.
     * This method allows us to know when a key is released, so we can remove it from the list.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        /*
         * By default lists use integers for removing an item at a certain position in the list, so this is how we
         * have to get around that.
         * By casting the keycode to an object it's removed instead of used to check for existence at a certain point in
         * the list.
         */
        inputs.remove((Object) e.getKeyCode());
    }
}
