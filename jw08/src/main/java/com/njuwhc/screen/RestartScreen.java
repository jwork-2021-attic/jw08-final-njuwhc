package com.njuwhc.screen;

import com.njuwhc.asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 *
 * @author Aeranythe Echosong
 */
public abstract class RestartScreen implements Screen {

    @Override
    public abstract void displayOutput(AsciiPanel terminal);

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                try {
                    return new PlayScreen();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            
            case KeyEvent.VK_B:  //回放
                try {
                    return new RePlayScreen();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            case KeyEvent.VK_F:    //指定生成新地图 remake
                try {
                    return new InPlayScreen();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            
            default:
                return this;
        }
    }

}

