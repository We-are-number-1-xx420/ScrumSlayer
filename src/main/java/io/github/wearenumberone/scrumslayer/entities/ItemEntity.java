package io.github.wearenumberone.scrumslayer.entities;

import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.render.ConsoleStyle;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.item.Item;

public class ItemEntity extends Entity {
    private final Item item;

    @Override
    public Grid<StyledCharacter> render() {
        return Grid.ofSingle(this.item.getAppearance());
    }

    public ItemEntity(Item item) {
        this.item = item;
    }
    public Item getItem() {
        return this.item;
    }
    public Item takeItem() {
        this.markDead();
        return this.getItem();
    }
}