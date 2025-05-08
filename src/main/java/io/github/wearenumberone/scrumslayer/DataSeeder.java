package io.github.wearenumberone.scrumslayer;

import io.github.wearenumberone.scrumslayer.entities.BossEntity;
import io.github.wearenumberone.scrumslayer.entities.ItemEntity;
import io.github.wearenumberone.scrumslayer.entities.MonsterEntity;
import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.item.FoodItem;
import io.github.wearenumberone.scrumslayer.item.Item;
import io.github.wearenumberone.scrumslayer.item.KeyItem;
import io.github.wearenumberone.scrumslayer.item.ShieldItem;
import io.github.wearenumberone.scrumslayer.mapping.Room;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.mapping.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class DataSeeder {
    private static final Random RANDOM = new Random();
    public static final World THE_WORLD = new World();
    public List<Room> roomList = new ArrayList<>();

    public static Grid<Character> sourceMap = Grid.fromCharGrid("""
    __I___B
    I#M#M#M
    _#_M_#_
    _M___MI
    _##M##_
    ___#__I
    S##M###
    """);

    private static Question[] questions = new Question[] {
        new Question("What is 1+1?", new String[] {"2", "two", "twee"}, 0),
        new Question("What is the answer to life?", new String[] {"42", "fourtytwo", "fourty-two", "tweeenveertig", "tweeënveertig"}, 0),
        new Question("Why are you gay", new String[] {"what", "no u", "who says i'm gay"}, 3),
        new Question("Who prays to jesus", new String[] {"Gabriel", "not Judas", "me"}, 2),
        new Question("The answer to the question is five", new String[] {"5", "five", "f, ei, v"}, 3),
        new Question("What happened to the dinosaurs?", new String[] {"Meteor impact", "They left on space ships", "They were eaten by cavemen", "me"}, 4),
        new Question("Can jesus do a kickflip", new String[] {"Of course", "no he doesn't know what a skateboard is", "punish the non-believers", "yuh"}, 4)
    };

    private static Item[] items = new Item[] {
        new FoodItem(),
        new ShieldItem()
    };

    private static Question getRandomQuestion() {
        return questions[RANDOM.nextInt(0, questions.length)];
    }
    private static Item getRandomItem() {
        return items[RANDOM.nextInt(0, items.length)];
    }

    public static void seed() {
        PlayerEntity player = new PlayerEntity();

        THE_WORLD.setRoomGrid(sourceMap.map(character -> switch(character) {
            case 'M' -> new Room(THE_WORLD).setQuestion(getRandomQuestion());
            case 'I' -> new Room(THE_WORLD).addEntity(new ItemEntity(getRandomItem()));
            case '#' -> new Room(THE_WORLD);
            case 'S' -> new Room(THE_WORLD).addEntity(player);
            case 'B' -> new Room(THE_WORLD).addEntity(new BossEntity());
            case '_' -> new Room(THE_WORLD);
            default -> new Room(THE_WORLD);
        }));
    }
}