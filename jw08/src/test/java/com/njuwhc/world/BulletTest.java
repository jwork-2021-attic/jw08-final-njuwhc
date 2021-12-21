package com.njuwhc.world;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.njuwhc.asciiPanel.AsciiPanel;

public class BulletTest {
    World world = new WorldBuilder(80, 70).makeCaves().build();
    Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);

    @Test
    public void testAttack() {

    }

    @Test
    public void testAttackValue() {

    }

    @Test
    public void testColor() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.color(),AsciiPanel.brightBlack);
    }

    @Test
    public void testDig() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.tile(50, 24),Tile.WALL);
        bullet.dig(50, 24);
        assertEquals(bullet.tile(50, 24),Tile.FLOOR);
    }

    @Test
    public void testFlyDirection() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.flyDirection(),1);
    }

    @Test
    public void testGlyph() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.glyph(),(char)177);
    }

    @Test
    public void testMoveBy() {

    }

    @Test
    public void testOnEnter() {

    }

    @Test
    public void testOwnerDirection() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.ownerDirection(),creature.direction());
    }

    @Test
    public void testOwnerX() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.ownerX(),creature.x());
    }

    @Test
    public void testOwnerY() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.ownerY(),creature.y());
    }

    @Test
    public void testRun() {
        
    }

    @Test
    public void testSetX() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        bullet.setX(100);
        assertEquals(bullet.x(),100);
    }

    @Test
    public void testSetY() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        bullet.setY(200);
        assertEquals(bullet.y(),200);
    }

    @Test
    public void testTile() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        assertEquals(bullet.tile(50, 50),Tile.FLOOR);
    }

    @Test
    public void testX() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        bullet.setX(100);
        assertEquals(bullet.x(),100);
    }

    @Test
    public void testY() {
        Bullet bullet=new Bullet(creature,world,(char)177, AsciiPanel.brightBlack, 1,1);
        bullet.setY(200);
        assertEquals(bullet.y(),200);
    }
}
