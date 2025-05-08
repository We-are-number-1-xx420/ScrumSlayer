package io.github.wearenumberone.scrumslayer.entities;

import io.github.wearenumberone.scrumslayer.Question;
import io.github.wearenumberone.scrumslayer.render.ConsoleStyle;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;

public class MonsterEntity extends Entity {
    private final Question question;

    public MonsterEntity(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return this.question;
    }

    @Override
    public Grid<StyledCharacter> render() {
        return Grid.ofSingle(new StyledCharacter('☠', new ConsoleStyle().setForeground(ConsoleStyle.Color.RED)));
    }
}