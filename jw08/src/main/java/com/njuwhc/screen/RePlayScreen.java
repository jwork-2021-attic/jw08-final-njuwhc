package com.njuwhc.screen;

import com.njuwhc.world.*;
import com.njuwhc.asciiPanel.AsciiPanel;
import com.njuwhc.recorder.Record;
import com.njuwhc.recorder.Replay;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Aeranythe Echosong
 */
public class RePlayScreen implements Screen {

    private World world;
    private Creature player;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;
    private List<String> oldMessages;

    public RePlayScreen() throws IOException {
        this.screenWidth = 50;
        this.screenHeight = 50;
        createWorld();
        this.messages = new ArrayList<String>();
        this.oldMessages = new ArrayList<String>();

        new Thread(new Replay(world)).start();
    }

    private void createWorld() throws IOException {
        //world = new WorldBuilder(90, 31).makeCaves().build();
        world = new WorldBuilder(50, 50).makeCaves().build();
        //world = new WorldBuilder(50, 50).worldInput().build();
        //world.worldOutput();
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        // Show terrain
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;
                terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
                
            }
        }
        // Show creatures
        synchronized(world.getCreatures()){
        for (Creature creature : world.getCreatures()) {
            if (creature.x() >= left && creature.x() < left + screenWidth && creature.y() >= top
                    && creature.y() < top + screenHeight) {
               // if (player.canSee(creature.x(), creature.y())) {
                    terminal.write(creature.glyph(), creature.x() - left, creature.y() - top, creature.color());
               // }
            }
        }
    }



        // Creatures can choose their next action now
        //world.update();
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = this.screenHeight - messages.size();
        for (int i = 0; i < messages.size(); i++) {
            terminal.write(messages.get(i), 51, top + i + 1);
        }
        this.oldMessages.addAll(messages);
        //messages.clear();
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        // Terrain and creatures
        displayTiles(terminal, 0, 0);
        // Player
        //terminal.write(player.glyph(), player.x() - getScrollX(), player.y() - getScrollY(), player.color());
        // Stats
        //String stats = String.format("%3d/%3d hp", player.hp(), player.maxHP());
        //terminal.write(stats, 1, 50);
        // Messages
        displayMessages(terminal, this.messages);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if(this.player.hp()<1)
            return new LoseScreen();
        switch (key.getKeyCode()) {
            case KeyEvent.VK_R:    //随机生成新地图 remake
                try {
                    return new PlayScreen();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            case KeyEvent.VK_W:  //直接胜利
                return new WinScreen();



            case KeyEvent.VK_F:    //随机生成新地图 remake
                try {
                    return new InPlayScreen();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                
        }
        return this;
    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.x() - screenWidth / 2, world.width() - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y() - screenHeight / 2, world.height() - screenHeight));
    }

}
