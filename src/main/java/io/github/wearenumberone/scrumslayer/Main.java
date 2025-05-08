package io.github.wearenumberone.scrumslayer;

import io.github.wearenumberone.scrumslayer.mapping.Room;
import io.github.wearenumberone.scrumslayer.mapping.World;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println(new Room(new World()).render());
		new Game(System.in, System.out).start();
	}
}