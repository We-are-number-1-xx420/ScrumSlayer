package io.github.wearenumberone.scrumslayer.events;

import io.github.wearenumberone.scrumslayer.Question;
import io.github.wearenumberone.scrumslayer.mapping.Room;

public class QuestionEvent implements EventType{
    private Question question;

    @Override
    public void executeEvent(Room room) {

    }
}