package io.github.wearenumberone.scrumslayer.entities;

import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;
import io.github.wearenumberone.scrumslayer.item.Item;

import java.util.List;

public class PlayerEntity extends LivingEntity {
    private List<Item> inventory;
    private boolean isDefeated = false;

    @Override
    public Grid<StyledCharacter> render() {
        //later
        return null;
    }

    public PlayerEntity(Vec2i position){
        super(3);
        this.setPosition(position)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Entity> getInventory() {
        return inventory;
    }

    public void setInventory(List<Entity> inventory) {
        this.inventory = inventory;
    }

    public void addToInventory(Entity entity){
        this.inventory.add(entity);
    }

    public void removeFromInventory(Entity entity){
        this.inventory.remove(entity);
    }

    public void clearInv(){
        this.inventory.clear();
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}