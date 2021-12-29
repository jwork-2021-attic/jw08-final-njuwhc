package com.njuwhc.recorder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.njuwhc.world.Bullet;
import com.njuwhc.world.Creature;
import com.njuwhc.world.Tile;
import com.njuwhc.world.World;

public class Record implements Runnable{

    private World world;
    private FileWriter outputStream;
    public Record(World world)
    {
        this.world = world;
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            outputStream = new FileWriter("output.txt");
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        while(true)
        {
            try {
                char c='0';
                for (int i=0;i<world.width();i++)
                {
                    for(int j=0;j<world.height();j++)
                {
                    if(world.tile(i, j)==Tile.FLOOR)
                        c='0';
                    else if(world.tile(i,j)==Tile.WALL)
                        c='1';
                    outputStream.write(c);
                }
                outputStream.write('\n');
                }
    
                for(Creature creature:world.getCreatures())
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
    
                
                for(Bullet bullet:world.getBullets())
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

                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } 





        }
    }
    
}
