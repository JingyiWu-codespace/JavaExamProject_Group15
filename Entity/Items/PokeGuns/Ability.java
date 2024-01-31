package JavaExamProject_Group15.Entity.Items.PokeGuns;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;
import JavaExamProject_Group15.StoryTeller.PokeGuns.PokeGunStory;

import static JavaExamProject_Group15.Player.currPlayer;

public abstract class Ability extends ItemBase {
    int cooldown;
    int currCooldown = 0;

    public Ability(String name, String description, int cooldown) {
        super(name, description, new String[]{}, true);
        this.cooldown = cooldown;
    }

    public void pickup() {
        System.out.println("thats now how abilities work!");
    }

    public boolean possibleAct() {
        if(!(Inventory.inRoom(this, currPlayer.getCurrentRoom()))) {
            System.out.println("you dont have this ability");
            return false;
        }
        if (this.currCooldown > 0) {
            System.out.println("this ability is on cooldown. " + this.currCooldown + " turns left");
            return false;
        }
        return true;
    }
}
