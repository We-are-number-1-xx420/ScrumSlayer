package io.github.wearenumberone.scrumslayer.item;

import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.render.ConsoleStyle;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;

public class ShieldItem extends Item {
    public void onAdd(PlayerEntity player) {
        player.setMaxHealth(player.getMaxHealth() + 1);
        player.changeHealth(1);
        player.removeFromInventory(this);
    }

    public StyledCharacter getAppearance() {
        return new StyledCharacter('ᗢ', new ConsoleStyle().setForeground(ConsoleStyle.Color.CYAN));
    }
}