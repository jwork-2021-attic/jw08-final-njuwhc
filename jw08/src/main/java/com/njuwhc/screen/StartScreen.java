package com.njuwhc.screen;

import com.njuwhc.asciiPanel.AsciiPanel;

/**
 *
 * @author Aeranythe Echosong
 */
public class StartScreen extends RestartScreen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("This is the start screen.", 0, 0);
        terminal.write("Press ENTER to continue...", 0, 1);
        terminal.write("Press B to replay the lastest game", 0, 2);
        terminal.write("Press F to make the given map...", 0, 3);
        terminal.write("After,press W to win directly", 0, 4);
        terminal.write("After,press R to remake", 0, 5);

        

    }

}

