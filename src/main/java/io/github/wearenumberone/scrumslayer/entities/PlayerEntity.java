package io.github.wearenumberone.scrumslayer.entities;

import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.item.Item;

import java.util.List;

public class PlayerEntity extends LivingEntity {
    private List<Item> inventory;
    private boolean isDefeated = false;

    @Override
    public Grid<StyledCharacter> render() {
        return Grid.ofSingle(new StyledCharacter('*'));
    }

    public PlayerEntity(){
        super(3);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addToInventory(Item item) {
        this.inventory.add(item);
        item.onAdd(this);
    }

    public void removeFromInventory(Item item){
        this.inventory.remove(item);
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
}