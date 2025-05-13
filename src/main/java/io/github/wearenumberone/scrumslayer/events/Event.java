package io.github.wearenumberone.scrumslayer.events;

import io.github.wearenumberone.scrumslayer.mapping.Room;

public class Event {
    private EventType event;
    private Room room;

    public Event(EventType eventType, Room room){
        this.event = eventType;
        this.room = room;
    }

    public void executeEvent(){
        this.event.executeEvent(this.room);
    }
}