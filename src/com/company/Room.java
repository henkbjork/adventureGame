package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    private String description;
    private String[] exits;
    private List<Item> items;
    private List<Trap> traps;
    private List<Enemy> enemies;

    public Room(String description, String[] exits) {
        this.description = description;
        this.exits = exits;
        this.items = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addItem(String item) {
        items.add(new Item(item));
    }

    public void addTrap(Trap trap) {
        traps.add(trap);
    }

    public boolean deleteItem(String item) {
        for(Item x : items) {
            if(x.getName().equals(item)) {
                items.remove(x);
                return true;
            }
        }
        return false;
    }

    public String printRoomItems() {
        if(items.size() == 0) {
            return "This room has no items.";
        }
        StringBuilder sb = new StringBuilder();
        for(Item item : items) {
            sb.append(item.getName());
            sb.append(", ");
        }
        return "This room has the following items: \n" + sb.subSequence(0, sb.length()-2);
    }

    public String printRoomEnemies() {
        if(enemies.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(Enemy enemy : enemies) {
            sb.append(enemy.getName()).append(", damage: ").append(enemy.getDamage());
            sb.append(", ");
        }
        return "You just waled in an enemy... " + sb.subSequence(0, sb.length()-2);
    }

    public String printRoomTraps() {
        if(traps.size() == 0) {
            return "This room has no traps";
        }
        StringBuilder sb = new StringBuilder();
        for(Trap trap : traps) {
            sb.append(trap.getName()).append(", damage: ").append(trap.getDamage());
            sb.append(", ");
        }
        return "To bad, this room has a trap... You just stepped on a: " + sb.subSequence(0, sb.length()-2);
    }

    public Trap getTrap() {
        for(Trap trap : traps) {
            return trap;
        }
        return null;
    }

    public void deleteTrap() {
        if(getTrap() != null) {
            traps.remove(getTrap());
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }

    public String[] getExits() {
        return exits;
    }

    public boolean possibleExit(String direction) {
        return Arrays.asList(exits).contains(direction);
    }

    @Override
    public String toString() {
        return "> " + description + ": " + Arrays.toString(exits) + "\n" + "> " + printRoomItems() + "\n" + "> " + printRoomTraps();
    }
}
