package com.njuwhc.world;

import java.util.List;

import com.njuwhc.asciiPanel.AsciiPanel;

import java.awt.Color;

/**
 *
 * @author Aeranythe Echosong
 */
public class CreatureFactory {

    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public synchronized Creature newPlayer(List<String> messages) {
        Creature player = new Creature(this.world, (char)2, AsciiPanel.brightWhite, 100, 20, 5, 9);
        BulletFactory bulletFactory = new BulletFactory(this.world,player);
        player.bulletFactory=bulletFactory;
        world.addAtEmptyLocation(player);
        new PlayerAI(player, messages);
        return player;
    }

    public synchronized Creature newFungus() {
        Creature fungus = new Creature(this.world, (char)3, AsciiPanel.green, 10, 4, 0, 0);
        world.addAtEmptyLocation(fungus);
        new FungusAI(fungus, this);
        return fungus;
    }

    public synchronized Monster newMonster(List<String> messages,char style,Color color){
        Monster monster = new Monster(this.world,style,color, 20, 20, 1, 0);
        world.addAtEmptyLocation(monster);
        BulletFactory bulletFactory = new BulletFactory(this.world,monster);
        monster.bulletFactory=bulletFactory;
        new MonsterAI(monster,messages);
        return monster;
    }

}
