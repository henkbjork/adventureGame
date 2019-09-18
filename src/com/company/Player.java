package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int posX;
    private int posY;
    private int life;
    private List<Item> inventory;

    public Player() {
        this.posX = 0;
        this.posY = 1;
        this.life = 100;
        this.inventory = new ArrayList<>();
    }

    public void pickUpItem(String item) {
        inventory.add(new Item(item));
        System.out.println("You just picked up " + item);
    }

    public boolean dropItem(String item) {
        for(Item x : inventory) {
            if(x.getName().equals(item)) {
                inventory.remove(x);
                System.out.println("You just dropped: " + item);
                return true;
            }
        }
        return false;
    }

    public void playerItem() {
        if(inventory.isEmpty()) {
            System.out.println("You have no items");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(Item x : inventory) {
            sb.append(x);
            sb.append(", ");
        }
        System.out.println("You currently have the following items: " + sb);
    }

    public void move(String direction) {
        if(direction.equalsIgnoreCase("N")) {
            posY--;
        } else if(direction.equalsIgnoreCase("E")) {
            posX++;
        } else if(direction.equalsIgnoreCase("S")) {
            posY++;
        } else if(direction.equalsIgnoreCase("W")) {
            posX--;
        }
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getLife() {
        return life;
    }

    @Override
    public String toString() {
        return "Player{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
