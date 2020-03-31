package org.example;

import java.util.Random;

public abstract class Fighter {
    int health;
    int strength;
    int speed;
    int reflexes;

    public Fighter() {
        this.health = getHealth();
        this.strength = getStrength();
        this.speed = getSpeed();
        this.reflexes = getReflexes();
    }

    public abstract int getHealth();

    public abstract int getStrength();

    public abstract int getSpeed();

    public abstract int getReflexes();

    abstract void setName(String name);

    abstract String getName();

    boolean attack(Fighter fighter) {
        if (!fighter.attemptDodge()) {
            fighter.takeDamage(getStrength());
            return true;
        }
        return false;
    }

    boolean attemptDodge() {
        Random rand = new Random(11);
        return rand.nextInt() <= getReflexes();
    }

    void takeDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    void setHealth(int newHealth) {
        this.health = newHealth;
    }

    int specialAbility() {
        if (this instanceof Archer)
            return 0;
        if (this instanceof Knight)
            return 1;
        return 2;
    }
}
