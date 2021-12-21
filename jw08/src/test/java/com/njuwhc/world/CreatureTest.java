package com.njuwhc.world;

import static org.junit.Assert.assertEquals;

import com.njuwhc.asciiPanel.AsciiPanel;

import org.junit.Test;

public class CreatureTest {
    World world = new WorldBuilder(80, 70).makeCaves().build();

    @Test
    public void testAttack() {

    }

    @Test
    public void testAttackValue() {

    }

    @Test
    public void testCanEnter() {

    }

    @Test
    public void testCanSee() {

    }

    @Test
    public void testColor() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        assertEquals(creature.color(),AsciiPanel.red);
    }

    @Test
    public void testDefenseValue() {

    }

    @Test
    public void testDig() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        assertEquals(creature.tile(20, 24),Tile.WALL);
        creature.dig(50, 24);
        assertEquals(creature.tile(50, 24),Tile.FLOOR);
    }

    @Test
    public void testDirection() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        assertEquals(creature.direction(),0);
    }

    @Test
    public void testGlyph() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        assertEquals(creature.glyph(),(char)3);
    }

    @Test
    public void testHp() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 10, 1, 4);
        assertEquals(creature.hp(),100);
    }

    @Test
    public void testMaxHP() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        assertEquals(creature.maxHP(),100);
    }

    @Test
    public void testModifyHP() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 10, 1, 4);
        creature.modifyHP(-20);
        assertEquals(creature.hp(),80);
    }

    @Test
    public void testMoveBy() {

    }

    @Test
    public void testNotify() {

    }

    @Test
    public void testSetAI() {

    }

    @Test
    public void testSetColor() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        creature.setColor(AsciiPanel.green);
        assertEquals(creature.color(),AsciiPanel.green);
    }

    @Test
    public void testSetDirection() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        assertEquals(creature.direction(),0);
        creature.setDirection(3);
        assertEquals(creature.direction(),3);
    }

    @Test
    public void testSetFlag() {

    }

    @Test
    public void testSetX() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        creature.setX(30);
        assertEquals(creature.x(),30);
    }

    @Test
    public void testSetY() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        creature.setY(40);
        assertEquals(creature.y(),40);
    }

    @Test
    public void testTile() {

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testVisionRadius() {

    }

    @Test
    public void testX() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        creature.setX(30);
        assertEquals(creature.x(),30);
    }

    @Test
    public void testY() {
        Creature creature=new Creature(world, (char)3, AsciiPanel.red, 100, 5, 1, 4);
        creature.setY(40);
        assertEquals(creature.y(),40);
    }
}
