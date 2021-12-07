package com.njuwhc.screen;

import com.njuwhc.asciiPanel.AsciiPanel;

/**
 *
 * @author Aeranythe Echosong
 */
public class WinScreen extends RestartScreen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("You won! Press enter to go again.", 0, 0);
    }

}

