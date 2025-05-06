package io.github.wearenumberone.scrumslayer;

import java.util.List;

public class Character {
    private String naam;
    private int inventorySpace;
    private List<Item> items;
    private boolean isDefeated = false;
//    private Room currentRoom;
    private int healthpoints;
    private int maxHealth;


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getInventorySpace() {
        return inventorySpace;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void hasBeenDefeated() {
        this.isDefeated = true;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealthpoints() {
        return healthpoints;
    }

    public void setHealthpoints(int healthpoints) {
        this.healthpoints = healthpoints;
    }

/*
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
*/


}
