package com.njuwhc.world;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.njuwhc.asciiPanel.AsciiPanel;

public class TileTest {
    @Test
    public void testColor() {
        assertEquals(Tile.WALL.color(),AsciiPanel.brightBlack);
        assertEquals(Tile.FLOOR.color(),AsciiPanel.black);
        assertEquals(Tile.BOUNDS.color(),AsciiPanel.magenta);
    }

    @Test
    public void testGlyph() {
        assertEquals(Tile.WALL.glyph(),(char)177);
        assertEquals(Tile.FLOOR.glyph(),(char)250);
        assertEquals(Tile.BOUNDS.glyph(),'x');
    }

    @Test
    public void testIsDiggable() {
        assertEquals(Tile.WALL.isDiggable(),true);
        assertEquals(Tile.FLOOR.isDiggable(),false);
        assertEquals(Tile.BOUNDS.isDiggable(),false);
    }

    @Test
    public void testIsGround() {
        assertEquals(Tile.WALL.isGround(),false);
        assertEquals(Tile.BOUNDS.isGround(),false);
        assertEquals(Tile.FLOOR.isGround(),true);
    }

    @Test
    public void testValueOf() {

    }

    @Test
    public void testValues() {

    }
}
