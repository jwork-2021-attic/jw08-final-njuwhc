package com.njuwhc.world;

import static org.junit.Assert.assertEquals;

import com.njuwhc.asciiPanel.AsciiPanel;

import org.junit.Test;

public class WorldTest {

    @Test
    public void testAddAtEmptyLocation() {
        World world = new WorldBuilder(80, 70).makeCaves().build();
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        world.addAtEmptyLocation(creature);
    }

    @Test
    public void testAddBulletAt() {

    }

    @Test
    public void testColor() {
        World world = new WorldBuilder(80, 70).makeCaves().build();
        if(world.tile(20,20)==Tile.WALL)
        {
            assertEquals(world.color(20, 20),AsciiPanel.brightBlack);
        }
        else if(world.tile(20,20)==Tile.FLOOR)
        {
            assertEquals(world.color(20, 20),AsciiPanel.black);
        }

    }

    @Test
    public void testCreature() {
        World world = new WorldBuilder(80, 70).makeCaves().build();
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        world.addAtEmptyLocation(creature);
        assertEquals(world.creature(creature.x(),creature.y()),creature);
    }

    @Test
    public void testDig() {
        World world = new WorldBuilder(80, 70).makeCaves().build();
        int i=10;
        while(world.tile(i,20)!=Tile.WALL)
        {
            i++;
        }
        world.dig(i, 20);
        assertEquals(world.tile(i, 20),Tile.FLOOR);
    }

    @Test
    public void testGetBullets() {

    }

    @Test
    public void testGetCreatures() {

    }

    @Test
    public void testGlyph() {

    }

    @Test
    public void testHeight() {
        World world = new WorldBuilder(80, 70).makeCaves().build();
        assertEquals(world.height(),70);
    }

    @Test
    public void testRemove() {

    }

    @Test
    public void testRemove2() {

    }

    @Test
    public void testTile() {
        World world = new WorldBuilder(80, 70).makeCaves().build();
        //assertEquals(world.tile(20,20),Tile.FLOOR);
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testWidth() {
        World world = new WorldBuilder(80, 70).makeCaves().build();
        assertEquals(world.width(),80);
    }

    @Test
    public void testWorldOutput() {

    }
}
