package io.github.wearenumberone.scrumslayer.entities;

import io.github.wearenumberone.scrumslayer.util.Vec2i;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.render.Renderable;

public abstract class Entity implements Renderable {
    private Vec2i position;
    private boolean dead;

    public Vec2i getPosition() {
        return this.position;
    }
    public void setPosition(Vec2i position) {
        this.position = position;
    }

    public void markDead() {
        this.dead = true;
    }
    public boolean isDead() {
        return this.dead;
    }

    public abstract Grid<StyledCharacter> render();
}