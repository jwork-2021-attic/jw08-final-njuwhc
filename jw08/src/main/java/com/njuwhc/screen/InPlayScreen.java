package com.njuwhc.screen;

import com.njuwhc.world.*;
import com.njuwhc.asciiPanel.AsciiPanel;
import com.njuwhc.recorder.Record;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class InPlayScreen implements Screen {

    private World world;
    private Creature player;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;
    private List<String> oldMessages;

    public InPlayScreen() throws IOException {
        //this.screenWidth = 80;
        //this.screenHeight = 24;
        this.screenWidth = 50;
        this.screenHeight = 50;
        createWorld();
        this.messages = new ArrayList<String>();
        this.oldMessages = new ArrayList<String>();

        CreatureFactory creatureFactory = new CreatureFactory(this.world);
        createCreatures(creatureFactory);
        
    }

    private void createCreatures(CreatureFactory creatureFactory) {
        this.player = creatureFactory.newPlayer(this.messages);  //创造一个玩家

        int num=225;
        Color[] colors={AsciiPanel.brightRed,AsciiPanel.brightYellow,AsciiPanel.brightGreen,AsciiPanel.brightBlue,AsciiPanel.brightMagenta};
        ExecutorService exec=Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {exec.submit(creatureFactory.newMonster(this.messages,(char)num++,colors[i]));}//创造若干怪物，线程池管理线程
        exec.shutdown();
    }

    private void createWorld() throws IOException {
        //world = new WorldBuilder(90, 31).makeCaves().build();
        //world = new WorldBuilder(50, 50).makeCaves().build();
        world = new WorldBuilder(50, 50).worldInput().build();
        
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        // Show terrain
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;

                if (player.canSee(wx, wy)) {
                    terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
                } else {
                    terminal.write(world.glyph(wx, wy), x, y, Color.DARK_GRAY);
                }
            }
        }
        // Show creatures
        for (Creature creature : world.getCreatures()) {
            if (creature.x() >= left && creature.x() < left + screenWidth && creature.y() >= top
                    && creature.y() < top + screenHeight) {
                if (player.canSee(creature.x(), creature.y())) {
                    terminal.write(creature.glyph(), creature.x() - left, creature.y() - top, creature.color());
                }
            }
        }

        // Show bullets
        for (Bullet bullet : world.getBullets()) {
            if (bullet.x() >= left && bullet.x() < left + screenWidth && bullet.y() >= top
                    && bullet.y() < top + screenHeight) {
                if (player.canSee(bullet.x(), bullet.y())) {
                    terminal.write(bullet.glyph(), bullet.x() - left, bullet.y() - top,bullet.color());
                }
            }
        }

        // Creatures can choose their next action now
        world.update();
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
        displayTiles(terminal, getScrollX(), getScrollY());
        // Player
        terminal.write(player.glyph(), player.x() - getScrollX(), player.y() - getScrollY(), player.color());
        // Stats
        String stats = String.format("%3d/%3d hp", player.hp(), player.maxHP());
        terminal.write(stats, 1, 50);
        // Messages
        displayMessages(terminal, this.messages);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if(this.player.hp()<1)
            return new LoseScreen();
        switch (key.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            if(player.x()>0)
            {
                player.setDirection(1);
                new Thread(player.bulletFactory.newBullet((char) 27,player.color())).start();
                player.moveBy(-1, 0);
            }
                break;
            case KeyEvent.VK_RIGHT:
            if(player.x()<this.world.width()-1)
            {
                player.setDirection(2);
                new Thread(player.bulletFactory.newBullet((char) 26,player.color())).start();
                player.moveBy(1, 0);
            }
                break;
            case KeyEvent.VK_UP:
            if(player.y()>0)
            {
                player.setDirection(3);
                new Thread(player.bulletFactory.newBullet((char) 24,player.color())).start();
                player.moveBy(0, -1);
            }
                break;
            case KeyEvent.VK_DOWN:
            if(player.y()<this.world.height()-1)
            {
                player.setDirection(4);
                new Thread(player.bulletFactory.newBullet((char) 25,player.color())).start();
                player.moveBy(0, 1);
            }
                break;
            case KeyEvent.VK_R:    //随机生成新地图 remake
                try {
                    return new PlayScreen();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            case KeyEvent.VK_W:  //直接胜利
                return new WinScreen();

            case KeyEvent.VK_1: 
                player.setColor(AsciiPanel.brightRed);
                break;
            case KeyEvent.VK_2: 
                player.setColor(AsciiPanel.brightYellow);
                break;
            case KeyEvent.VK_3: 
                player.setColor(AsciiPanel.brightGreen);
                break;
            case KeyEvent.VK_4: 
                player.setColor(AsciiPanel.brightBlue);
                break;
            case KeyEvent.VK_5: 
                player.setColor(AsciiPanel.brightMagenta);
                break;

                
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
