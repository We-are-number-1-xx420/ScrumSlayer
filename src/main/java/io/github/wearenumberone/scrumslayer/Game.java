package io.github.wearenumberone.scrumslayer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {
    private InputStream in;
    private PrintStream out;
    private boolean running;

    public Game(InputStream in, OutputStream out) {
        this.in = in;
        this.out = new PrintStream(out);
    }

    public void start() {
        final Scanner sc = new Scanner(in);
        while(running) {
            processInput(sc.next());
        }
    }

    public void processInput(String input) {
        //Call needed methods
    }

    public void stop() {
        this.running = false;
    }
}