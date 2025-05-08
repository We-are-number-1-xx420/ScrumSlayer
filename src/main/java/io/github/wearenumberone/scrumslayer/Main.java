package io.github.wearenumberone.scrumslayer;

public class Main {
	public static void main(String[] args) throws Exception {
		DataSeeder.seed();
		new Game(System.in, System.out).start();
	}
}