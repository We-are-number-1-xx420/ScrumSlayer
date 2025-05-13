package io.github.wearenumberone.scrumslayer;

import java.io.InputStream;
import java.util.Scanner;

public class Game {
    private InputStream in;
    private boolean running;

    public Game(InputStream in){
        this.in = in;
    }

    public void start(){
        final Scanner sc = new Scanner(in);
        while(running){
            processInput(sc.next());
        }
    }

    public void processInput(String input){
        //Call needed methods
    }

    public void stop(){
        this.running = false;
    }
}