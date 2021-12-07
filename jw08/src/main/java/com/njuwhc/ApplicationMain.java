package com.njuwhc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.njuwhc.asciiPanel.AsciiFont;
import com.njuwhc.asciiPanel.AsciiPanel;
import com.njuwhc.control.RefreshGUI;
import com.njuwhc.screen.Screen;
import com.njuwhc.screen.StartScreen;

/**
 *
 * @author Aeranythe Echosong
 */
public class ApplicationMain extends JFrame implements KeyListener {

    private AsciiPanel terminal;
    private Screen screen;
  

    public ApplicationMain() {
        super();
        //terminal = new AsciiPanel(80, 32, AsciiFont.TALRYTH_15_15);
        terminal = new AsciiPanel(90, 60, AsciiFont.TALRYTH_15_15);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
        

    }

    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    /**
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        //repaint();
    }

    /**
     *
     * @param e
     */
    
     public void keyReleased(KeyEvent e) {
    }

    /**
     *
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    }




    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        RefreshGUI fresh=new RefreshGUI(app);
        Thread freshthread=new Thread(fresh);
        freshthread.start();

    }
}

