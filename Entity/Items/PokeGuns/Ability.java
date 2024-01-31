package JavaExamProject_Group15.Entity.Items.PokeGuns;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.PokeGuns.Arena1;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import static JavaExamProject_Group15.Player.currPlayer;

public abstract class Ability extends ItemBase {
    int cooldown;

    public Ability(String name, String description, int cooldown) {
        super(name, description, new String[]{}, true);
        this.cooldown = cooldown;
    }

    public void pickup() {
        System.out.println("thats now how abilities work!");
    }

    public void interaction() {
        System.out.println("hummmmmmmmmmmmmmmmmmmmmmmmm, looooool!");
        return;
    }

    protected RoomBase findRivalRoom() {
        RoomBase rivalRoom;
        RoomBase a1 = RoomBase.getRoomObj(Arena1.class);
        RoomBase a2 = RoomBase.getRoomObj(Arena1.class);
        if (currPlayer.getCurrentRoom() == a1)
            rivalRoom = a2;
        else
            rivalRoom = a1;
        return rivalRoom;
    }
}
