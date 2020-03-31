package org.example;

public class Tank extends Fighter {
    static int HEALTH = 20;
    static int STRENGTH = 7;
    static int SPEED = 1;
    static int REFLEXES = 3;
    String name;

    public Tank(String name) {
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
        return "Tank";
    }
}
