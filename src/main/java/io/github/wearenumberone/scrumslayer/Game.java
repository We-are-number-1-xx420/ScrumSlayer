package io.github.wearenumberone.scrumslayer;

import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.mapping.QuestionRoom;
import io.github.wearenumberone.scrumslayer.mapping.Room;
import io.github.wearenumberone.scrumslayer.mapping.World;
import io.github.wearenumberone.scrumslayer.util.CardinalDirection;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {
    private final InputStream in;
    private final PrintStream out;
    private boolean running;
    private World map;
    private PlayerEntity player;

    public Game(InputStream in, OutputStream out) {
        this.in = in;
        this.out = new PrintStream(out);
    }

    public void start() {
        final Scanner scanner = new Scanner(this.in);
        this.running = true;
        while (this.running) {
            this.out.println(this.map.render().toString());
            String line = scanner.nextLine();
            if (line.length() == 1) this.processInput(line.charAt(0));
        }
    }

    public void tryMove(Character input) {
        Vec2i oldPos = this.map.getCurrentPosition();

        Vec2i targetOffset = switch (Character.toLowerCase(input)) {
            case 'w' -> CardinalDirection.NORTH;
            case 'a' -> CardinalDirection.WEST;
            case 's' -> CardinalDirection.SOUTH;
            case 'd' -> CardinalDirection.EAST;
            default -> Vec2i.ZERO;
        };

        Vec2i newPos = oldPos.add(targetOffset);

        if (!this.map.canAccess(newPos)) return; // TODO: Print failiure?

        if (this.map.canAccess(newPos)) {
            Room oldRoom = this.map.getCurrentRoom();
            this.map.setCurrentPosition(newPos);
            Room newRoom = this.map.getCurrentRoom();

            //oldRoom.moveEntities(entity -> entity instanceof PlayerEntity, newRoom); // TODO: Add this method
        }
    }

    private void processInput(char input) {
        int hashCode = Character.valueOf(input).hashCode();
        if (48 <= hashCode && hashCode <= 57) {
            if (this.map.getCurrentRoom() instanceof QuestionRoom room) room.tryAnswer(String.valueOf(input));
        } else {
            this.tryMove(input);
        }
    }

    public void stop(){
        this.running = false;
    }
}