package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    private String description;
    private String[] exits;
    private List<Item> items;

    public Room(String description, String[] exits) {
        this.description = description;
        this.exits = exits;
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(new Item(item));
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
        return description + ": " + Arrays.toString(exits) + "\n" + printRoomItems();
    }
}
