package io.github.wearenumberone.scrumslayer.item;

import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.render.ConsoleStyle;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;

public class FoodItem extends Item {
    public void onAdd(PlayerEntity player) {
        player.changeHealth(2);
        player.removeFromInventory(this);
    }

    public StyledCharacter getAppearance() {
        return new StyledCharacter('\uF8FF', new ConsoleStyle().setForeground(ConsoleStyle.Color.RED));
    }
}