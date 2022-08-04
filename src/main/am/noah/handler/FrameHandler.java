package main.am.noah.handler;

import main.am.noah.Pong;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameHandler {
    // Whatever number is put into here will be the attempted frame rate.
    final static int FRAME_RATE = 60;

    public Timer timer;
    private long lastFrame;

    public FrameHandler(Pong pong) {
        lastFrame = System.currentTimeMillis();

        // This timer will run at whatever our framerate integer is set to.
        timer = new Timer(1000 / FRAME_RATE, new ActionListener() {

            // Whatever is inside this void will be run once a frame.
            @Override
            public void actionPerformed(ActionEvent arg0) {

                /*
                 * In order to make the game frame rate independent we can use movement multipliers like this.
                 *
                 * Say you want an object to move 1 unit over the course of 1 second.
                 * If you multiply 1 by this number each frame it will equal out, to 1 unit after 1 second.
                 *
                 * Using this system also helps us protect against lag spikes (although this program uses less than 0.1%
                 * of my CPU, so very unlikely to be a problem) as it will increase the amount of movement
                 */

                final double frameMultiplier = ((System.currentTimeMillis() - lastFrame) / 1000D);
                lastFrame = System.currentTimeMillis();

                pong.handleFrame(frameMultiplier);
            }
        });
    }
}
