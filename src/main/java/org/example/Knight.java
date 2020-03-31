package org.example;

public class Knight extends Fighter {
    static int HEALTH = 15;
    static int STRENGTH = 5;
    static int SPEED = 2;
    static int REFLEXES = 5;
    String name;

    public Knight(String name) {
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
    void setName(String name) {
        this.name = name;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
