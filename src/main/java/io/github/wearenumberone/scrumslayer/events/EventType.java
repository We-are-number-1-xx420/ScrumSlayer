package io.github.wearenumberone.scrumslayer.events;

import io.github.wearenumberone.scrumslayer.mapping.Room;

import java.util.function.Predicate;

public interface EventType {
    void executeEvent(Room room);
}
