package io.github.wearenumberone.scrumslayer.entities;

public abstract class LivingEntity extends Entity {
    private int maxHealth;
    private int health;

    public LivingEntity(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    private void updateHealth() {
        if (this.health <= 0) this.markDead();
        else if (this.health > this.maxHealth) this.health = this.maxHealth;
    }

    public void changeHealth(int delta) {
        this.health += delta;
        this.updateHealth();
    }
    public void setHealth(int health) {
        this.health = health;
        this.updateHealth();
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.updateHealth();
    }
    public int getHealth() {
        return this.health;
    }
    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void takeDamage(int damage) {
        this.changeHealth(-damage);
    }

    public void kill() {
        this.setHealth(Integer.MIN_VALUE);
    }
}