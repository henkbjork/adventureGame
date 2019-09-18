package com.company;

public class Weapon {
    private String name;
    private int harm;
    private int weight;

    public Weapon(String name, int harm, int weight) {
        this.name = name;
        this.harm = harm;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHarm() {
        return harm;
    }

    public void setHarm(int harm) {
        this.harm = harm;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + ", harm: " + harm;
    }
}
