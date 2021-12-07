package com.njuwhc.world;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WorldBuilder {

    private int width;
    private int height;
    private Tile[][] tiles;
    private FileReader inputStream;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        this.inputStream=null;
    }

    public World build() {
        return new World(tiles);
    }



    private WorldBuilder randomizeTiles() {
        for (int width = 0; width < this.width; width++) {
            for (int height = 0; height < this.height; height++) {
                Random rand = new Random();
                switch (rand.nextInt(World.TILE_TYPES)) {
                    case 0:
                        tiles[width][height] = Tile.FLOOR;
                        break;
                    case 1:
                        tiles[width][height] = Tile.WALL;
                        break;
                }
            }
        }
        return this;
    }

    private WorldBuilder smooth(int factor) {
        Tile[][] newtemp = new Tile[width][height];
        if (factor > 1) {
            smooth(factor - 1);
        }
        for (int width = 0; width < this.width; width++) {
            for (int height = 0; height < this.height; height++) {
                // Surrounding walls and floor
                int surrwalls = 0;
                int surrfloor = 0;

                // Check the tiles in a 3x3 area around center tile
                for (int dwidth = -1; dwidth < 2; dwidth++) {
                    for (int dheight = -1; dheight < 2; dheight++) {
                        if (width + dwidth < 0 || width + dwidth >= this.width || height + dheight < 0
                                || height + dheight >= this.height) {
                            continue;
                        } else if (tiles[width + dwidth][height + dheight] == Tile.FLOOR) {
                            surrfloor++;
                        } else if (tiles[width + dwidth][height + dheight] == Tile.WALL) {
                            surrwalls++;
                        }
                    }
                }
                Tile replacement;
                if (surrwalls > surrfloor) {
                    replacement = Tile.WALL;
                } else {
                    replacement = Tile.FLOOR;
                }
                newtemp[width][height] = replacement;
            }
        }
        tiles = newtemp;
        return this;
    }

    public WorldBuilder makeCaves() {
        return randomizeTiles().smooth(8);
    }


    public WorldBuilder worldInput() throws IOException
    {
        
        try {
            inputStream = new FileReader("worldinput.txt");
            int c=0;
            for (int i=0;i<width;i++)
            {
                for(int j=0;j<height;j++)
            {
                c = inputStream.read();
                while(c!=48 && c!=49)
                {
                    c = inputStream.read(); 
                }
                
                
                if(c==48)
                    tiles[i][j]=Tile.FLOOR;
                else if(c==49)
                    tiles[i][j]=Tile.WALL;
            }
            }
            

            } finally {
            if (inputStream != null) {
            inputStream.close();
            }
            }
            return this;
        
    }



}
