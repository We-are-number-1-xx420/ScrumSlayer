package io.github.wearenumberone.scrumslayer.item;

import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;

public abstract class Item {
    public void onAdd(PlayerEntity player) {}
    public abstract StyledCharacter getAppearance();
}