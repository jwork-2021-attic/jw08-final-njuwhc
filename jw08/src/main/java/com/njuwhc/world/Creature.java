package com.njuwhc.world;

import java.awt.Color;

public class Creature {

    protected World world;

    private int movedirection;

    public BulletFactory bulletFactory;

    //移动方向，同时发射子弹  1左 2右 3上 4下
    public synchronized void setDirection(int direction) {this.movedirection = direction;}
    public synchronized int direction(){return movedirection;}

    protected boolean flag;

    public void setFlag(boolean result)
    {
        this.flag=result;
    }

    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public int x() {
        return x;
    }

    private int y;

    public void setY(int y) {
        this.y = y;
    }

    public int y() {
        return y;
    }

    private char glyph;

    public char glyph() {
        return this.glyph;
    }

    private Color color;

    public Color color() {
        return this.color;
    }

    public void setColor(Color color) {this.color = color;}

    private CreatureAI ai;

    public void setAI(CreatureAI ai) {
        this.ai = ai;
    }

    private int maxHP;

    public int maxHP() {
        return this.maxHP;
    }

    private int hp;

    public int hp() {
        return this.hp;
    }


    public synchronized void modifyHP(int amount) {
        this.hp += amount;

        if (this.hp < 1) {
            world.remove(this);
        }
    }

    private int attackValue;

    public int attackValue() {
        return this.attackValue;
    }

    private int defenseValue;

    public int defenseValue() {
        return this.defenseValue;
    }

    private int visionRadius;

    public int visionRadius() {
        return this.visionRadius;
    }

    public boolean canSee(int wx, int wy) {
        return ai.canSee(wx, wy);
    }

    public synchronized Tile tile(int wx, int wy) {
        return world.tile(wx, wy);
    }

    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }

    public synchronized void moveBy(int mx, int my) {

        Creature other = world.creature(x + mx, y + my);

        if (other == null){
            ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my));
        } else {
            attack(other);
            //return;
        }
    }

    public synchronized void attack(Creature other) {
        //if(this.color()!=other.color())
        //    return;
        int damage = Math.max(0, this.attackValue() - other.defenseValue());
        damage = (int) (Math.random() * damage) + 1;

        other.modifyHP(-damage);

        this.notify("%s attack the '%s' for %d damage.",this.glyph, other.glyph, damage);
        //other.notify("The '%s' attacks Player for %d damage.", other.glyph, damage);
    }

    public void update() {
        this.ai.onUpdate();
    }

    public synchronized boolean canEnter(int x, int y) {
        return world.tile(x, y).isGround();
    }

    public synchronized void notify(String message, Object... params) {
        ai.onNotify(String.format(message, params));
    }

    public Creature(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.attackValue = attack;
        this.defenseValue = defense;
        this.visionRadius = visionRadius;
        this.flag=true;
    }
}
