package com.njuwhc.world;

import java.awt.Point;

class CreatureAI {

    protected Creature creature;

    public CreatureAI(Creature creature) {
        this.creature = creature;
        this.creature.setAI(this);
    }

    public synchronized void onEnter(int x, int y, Tile tile) {
    }

    public synchronized void onUpdate() {
    }

    public synchronized void onNotify(String message) {
    }

    public boolean canSee(int x, int y) {
        if ((creature.x() - x) * (creature.x() - x) + (creature.y() - y) * (creature.y() - y) > creature.visionRadius()
                * creature.visionRadius()) {
            //return false;
            return true;
        }
        for (Point p : new Line(creature.x(), creature.y(), x, y)) {
            if (creature.tile(p.x, p.y).isGround() || (p.x == x && p.y == y)) {
                continue;
            }
            //return false;
            return true;
        }
        return true;
    }
}
