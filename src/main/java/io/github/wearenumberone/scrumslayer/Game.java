package io.github.wearenumberone.scrumslayer;

import io.github.wearenumberone.scrumslayer.mapping.World;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Game {
    private InputStream in;
    private OutputStream out;
    private boolean running;
    private String input;
    private World map = new World();

    public Game(InputStream in, OutputStream out){
        this.in = in;
        this.out = out;
    }

    public void start(){
        final Scanner sc = new Scanner(this.in);
        this.running = true;
        while(this.running){
            input = sc.next();
            this.map.setInput(this.input);
        }
    }

    public void stop(){
        this.running = false;
    }
}