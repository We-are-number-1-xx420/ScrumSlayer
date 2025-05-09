package io.github.wearenumberone.scrumslayer.entities;

import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.render.Renderable;

public abstract class Entity implements Renderable {
    private boolean dead;

    public void markDead() {
        this.dead = true;
    }
    public boolean isDead() {
        return this.dead;
    }

    public abstract Grid<StyledCharacter> render();
}