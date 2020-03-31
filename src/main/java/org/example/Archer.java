package org.example;

public class Archer extends Fighter {
    static int HEALTH = 10;
    static int STRENGTH = 3;
    static int SPEED = 3;
    static int REFLEXES = 7;
    String name;
    public Archer(String name){
        super();
        this.name = name;
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getReflexes() {
        return REFLEXES;
    }

    @Override
    public String toString() {
        return "Archer";
    }

    @Override
    void setName(String name) {
        this.name = name;
    }

    @Override
    String getName() {
        return name;
    }
}
