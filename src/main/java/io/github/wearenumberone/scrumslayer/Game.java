package io.github.wearenumberone.scrumslayer;

import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.mapping.World;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {
    private InputStream in;
    private PrintStream out;
    private boolean running;
    private World map;
    private PlayerEntity player;

    public Game(InputStream in, OutputStream out) {
        this.in = in;
        this.out = new PrintStream(out);

        this.map = DataSeeder.THE_WORLD;

        this.map.setCurrentRoom(this.map.getRoomGrid().get(this.map.getRoomGrid().find(room -> room != null && room.getEntities().stream().anyMatch(PlayerEntity.class::isInstance))));
    }

    public void start() {
        final Scanner sc = new Scanner(this.in);
        this.running = true;
        while (this.running) {
            System.out.println(this.map.render().toString());
            this.processInput(sc.next());
        }
    }

    private void processInput(String input) {
        String regex = "\\d+";
        if(input.matches(regex)){
            this.map.tryAnswer(input);
        }else{
            this.map.tryMove(input);
        }
    }

    public void stop(){
        this.running = false;
    }
}