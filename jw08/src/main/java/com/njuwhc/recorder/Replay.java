package com.njuwhc.recorder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.njuwhc.world.Creature;
import com.njuwhc.world.Tile;
import com.njuwhc.world.World;

import java.awt.Color;

public class Replay implements Runnable {

    private static FileReader inputStream;
    private static BufferedReader input;
    private World world;
    private boolean flag=true;
    private boolean flag_creat=true;
    int count=0;

    public Replay(World world)
    {
        this.world=world;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int c=0;
        String line;
        String[] s;
        int x;
        int y;
        int glyph;
        int rgb;
        try {
            inputStream = new FileReader("output.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        input = new BufferedReader(inputStream);


try {
    
        while(flag)
        {
            for (int i=0;i<50;i++)
            {
                for(int j=0;j<50;j++)
                {
                    c = input.read();
                    while(c!=48 && c!=49)
                    {
                        c = input.read();
                    }
                //System.out.println(c);
                count++;

                if(c==48)
                    world.setTILE(i, j, Tile.FLOOR);
                else if(c==49)
                    world.setTILE(i, j, Tile.WALL);
                }
            }
                
                input.read();//换行
                flag_creat=true;
            
            while(flag_creat)
            {
                    line=input.readLine();
                    if(line.length()==0)
                        break;
                    //System.out.println(line);
                    s=line.split("\\+");

                    if(s[0].charAt(0)=='c') //Creature
                    {
                        x=Integer.valueOf(s[1]);  //x坐标
                        y=Integer.valueOf(s[2]); //y坐标
                        glyph=Integer.valueOf(s[3]); //glyph
                        rgb=Integer.valueOf(s[4]); //color

                        Creature creature = new Creature(this.world, (char)glyph, new Color(rgb), 100, 20, 5,9);
                        creature.setX(x);
                        creature.setY(y);

                        world.creatures.add(creature);
                        

                    }
                    else if(s[0].charAt(0)=='b')  //Bullet
                    {
                        x=Integer.valueOf(s[1]);  //x坐标
                        y=Integer.valueOf(s[2]); //y坐标
                        glyph=Integer.valueOf(s[3]); //glyph
                        rgb=Integer.valueOf(s[4]); //color

                        Creature creature = new Creature(this.world, (char)glyph, new Color(rgb), 100, 20, 5,9);
                        creature.setX(x);
                        creature.setY(y);
                        
                        world.creatures.add(creature);
                        
                    }
                    else flag_creat=false; //读到的是换行
                
            }


            TimeUnit.MILLISECONDS.sleep(50);
            
            world.creatures.clear(); 
                        
            //System.out.println(input.readLine());
        }
    
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        if (inputStream != null) {
        try {
            inputStream.close();
            input.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
        }
    }
    
}
