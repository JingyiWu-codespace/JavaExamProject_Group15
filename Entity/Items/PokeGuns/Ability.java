package JavaExamProject_Group15.Entity.Items.PokeGuns;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms;

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

    protected Rooms findRivalRoom() {
        Rooms rivalRoom;
        if (currPlayer.getCurrentRoom() == Rooms.Arena1)
            rivalRoom = Rooms.Arena2;
        else
            rivalRoom = Rooms.Arena1;
        return rivalRoom;
    }
}
