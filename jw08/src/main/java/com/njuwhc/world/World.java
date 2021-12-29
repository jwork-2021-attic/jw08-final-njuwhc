package com.njuwhc.world;

import java.awt.Color;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World {

    private Tile[][] tiles;
    private int width;
    private int height;
    private List<Creature> creatures;
    private List<Bullet> bullets;
    private FileWriter outputStream;
    private FileReader inputStream;

    public static final int TILE_TYPES = 2;

    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.creatures = new ArrayList<>();
        this.bullets=new ArrayList<>();
        this.outputStream=null;
        this.inputStream=null;
    }

    public synchronized Tile tile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.BOUNDS;
        } else {
            return tiles[x][y];
        }
    }

    public synchronized char glyph(int x, int y) {
        return tiles[x][y].glyph();
    }

    public synchronized Color color(int x, int y) {
        return tiles[x][y].color();
    }

    public synchronized int width() {
        return width;
    }

    public synchronized int height() {
        return height;
    }

    public synchronized void dig(int x, int y) {
        if (tile(x, y).isDiggable()) {
            tiles[x][y] = Tile.FLOOR;
        }
    }

    public synchronized void addAtEmptyLocation(Creature creature) {
        int x;
        int y;

        do {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
        } while (!tile(x, y).isGround() || this.creature(x, y) != null);

        creature.setX(x);
        creature.setY(y);
        creature.setDirection(-1);

        this.creatures.add(creature);
    }
    public synchronized void addBulletAt(Bullet bullet){
        int mx=0,my=0;
        int x=bullet.ownerX();
        int y=bullet.ownerY();
        switch(bullet.flyDirection())
        {
            case(1):
                mx=-1;
                my=0;
                break;
            case(2):
                mx=1;
                my=0;
                break;
            case(3):
                mx=0;
                my=-1;
                break;
            case(4):
                mx=0;
                my=1;
                break;

        }

        while (!tile(x+mx, y+my).isGround() || this.creature(x+mx, y+my) != null)
        {
            if(!tile(x+mx, y+my).isGround()){this.dig(x+mx,y+my);}
            if(this.creature(x+mx, y+my) != null){
                //while(this.creature(x+mx, y+my) != null)
                {
                    //bullet.attack(this.creature(x+mx,y+my));
                    this.creature(x+mx, y+my).notify("%s is killed!",this.creature(x+mx, y+my).glyph());
                    this.creature(x+mx, y+my).setFlag(false);
                    this.remove(this.creature(x+mx, y+my));
                    //this.creature(x+mx, y+my).setHp(0);
                }
                //mx=mx+mx;my=my+my;
            }
        }

        bullet.setX(x+mx);
        bullet.setY(y+my);

        this.bullets.add(bullet);
    }


    public synchronized Creature creature(int x, int y) {
        for (Creature c : this.creatures) {
            if (c.x() == x && c.y() == y) {
                return c;
            }
        }
        return null;
    }

    public synchronized List<Creature> getCreatures() {
        return this.creatures;
    }

    public synchronized List<Bullet> getBullets() {
        return this.bullets;
    }

    public synchronized void remove(Creature target) {
        this.creatures.remove(target);
        return;
    }

    public synchronized void remove(Bullet target) {
        this.bullets.remove(target);
        return;
    }

    public void update() {
        ArrayList<Creature> toUpdate = new ArrayList<>(this.creatures);

        for (Creature creature : toUpdate) {
            creature.update();
        }
    }

    public void worldOutput() throws IOException
    {
        
        try {
            outputStream = new FileWriter("worldoutput.txt");
            char c='0';
            for (int i=0;i<width;i++)
            {
                for(int j=0;j<height;j++)
            {
                if(tiles[i][j]==Tile.FLOOR)
                    c='0';
                else if(tiles[i][j]==Tile.WALL)
                    c='1';
                outputStream.write(c);
            }
            outputStream.write('\n');
            }

            } finally {
            if (outputStream != null) {
            outputStream.close();
            }
            }
        
    }

    public void output() throws IOException
    {
        
        try {
            outputStream = new FileWriter("output.txt");
            char c='0';
            for (int i=0;i<width;i++)
            {
                for(int j=0;j<height;j++)
            {
                if(tiles[i][j]==Tile.FLOOR)
                    c='0';
                else if(tiles[i][j]==Tile.WALL)
                    c='1';
                outputStream.write(c);
            }
            outputStream.write('\n');
            }

            

            for(Creature creature:creatures)
            {
                String string="";
                string+="c+";
                string+=creature.x();
                string+="+";
                string+=creature.y();
                string+="+";
                string+=(int)creature.glyph();
                string+="+";
                string+=creature.color().getRGB();
                outputStream.write(string);
                outputStream.write('\n');
            }

            for(Bullet bullet:bullets)
            {
                String string="";
                string+="b+";
                string+=bullet.x();
                string+="+";
                string+=bullet.y();
                string+="+";
                string+=(int)bullet.glyph();
                string+="+";
                string+=bullet.color().getRGB();
                outputStream.write(string);
                outputStream.write('\n');
            }

            outputStream.write('\n');

            } finally {
            if (outputStream != null) {
            outputStream.close();
            }
            }
        
    }





}
