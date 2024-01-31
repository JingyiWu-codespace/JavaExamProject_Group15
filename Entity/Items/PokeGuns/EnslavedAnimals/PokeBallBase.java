package JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Items.PokeGuns.Ability;
import JavaExamProject_Group15.Entity.Items.PokeGuns.AbilityAttack;
import JavaExamProject_Group15.Entity.Items.PokeGuns.AbilityBuff;
import JavaExamProject_Group15.Entity.Items.PokeGuns.AbilityDebuff;
import JavaExamProject_Group15.Entity.Rooms.PokeGuns.Arena1;
import JavaExamProject_Group15.Entity.Rooms.PokeGuns.Arena2;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

import java.util.ArrayList;
import java.util.List;

import static JavaExamProject_Group15.Player.currPlayer;
import static JavaExamProject_Group15.StoryTeller.PokeGuns.PokeGunStory.findRivalRoom;

public abstract class PokeBallBase extends ItemBase {
    protected List<Ability> abilities = new ArrayList<>();

    public PokeBallBase(String name, String description, String[] alias, boolean canBePickedUp) {
        super(name, description, alias, canBePickedUp);
    }

    public void pickup() {
        System.out.println("You have the " + this.getName() + " PokeBall now! use the enslaved animal for great powers!");

        Inventory.removeFromAllInvs(this);

        if (findRivalRoom() instanceof Arena1)
            currPlayer.setCurrentRoom(new Arena2());
        else
            currPlayer.setCurrentRoom(new Arena1());
        RoomBase currentRoom = currPlayer.getCurrentRoom();
        for (Ability ability : abilities)
            Inventory.moveThisToRoom(ability, currentRoom);
    }

    public boolean interaction() {
        pickup();
        return true;
    }

    protected void addPowerAttack(String name, String desc, int dmg, int cooldown) {
        abilities.add(new AbilityAttack(name, desc, dmg, cooldown));
    }

    protected void addBuff(String name, String desc, int def, int cooldown) {
        abilities.add(new AbilityBuff(name, desc, def, cooldown));
    }

    protected void addDebuff(String name, String desc, int lowDef, int cooldown) {
        abilities.add(new AbilityDebuff(name, desc, lowDef, cooldown));
    }
}
