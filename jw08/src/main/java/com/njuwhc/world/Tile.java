package com.njuwhc.world;

import com.njuwhc.asciiPanel.AsciiPanel;
import java.awt.Color;

public enum Tile {

    FLOOR((char) 250, AsciiPanel.black),

    WALL((char) 177, AsciiPanel.brightBlack),

    BOUNDS('x', AsciiPanel.magenta);

    private char glyph;

    public synchronized char glyph() {
        return glyph;
    }

    private Color color;

    public synchronized Color color() {
        return color;
    }

    public synchronized boolean isDiggable() {
        return this == Tile.WALL;
    }

    public synchronized boolean isGround() {
        return this != Tile.WALL && this != Tile.BOUNDS;
    }

    Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }
}
