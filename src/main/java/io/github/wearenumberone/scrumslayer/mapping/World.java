package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.Question;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

// Keep Grid of all rooms, Perform actions on rooms based on user input (answer question, move next room)
public class World implements Renderable {
    private Grid<Room> roomGrid;
    private Room currentRoom;

    public Grid<StyledCharacter> render(){
        return this.currentRoom.render();

    }

    public void tryAnswer(String input){
        if ((this.currentRoom instanceof QuestionRoom) && (!this.currentRoom.isCleared)) {
            if(((QuestionRoom) this.currentRoom).tryAnswer(input)){
                this.currentRoom.setCleared(true);
            }else{
                ((QuestionRoom) this.currentRoom).summonMonster();
            }
        }
    }

    public void tryMove(String input){
    }

    public boolean canAccess(Vec2i position){
        return roomGrid.exists(position) && this.currentRoom.isCleared;
    }

    public Grid<Room> getRoomGrid() {
        return roomGrid;
    }

    public void setRoomGrid(Grid<Room> roomGrid) {
        this.roomGrid = roomGrid;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
