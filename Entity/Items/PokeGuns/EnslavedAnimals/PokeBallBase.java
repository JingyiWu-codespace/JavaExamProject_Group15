package JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Items.PokeGuns.*;
import JavaExamProject_Group15.Entity.Rooms;
import JavaExamProject_Group15.Inventory;

import java.util.List;

import static JavaExamProject_Group15.Player.currPlayer;

public abstract class PokeBallBase extends ItemBase {
    protected List<Ability> abilities;
    public PokeBallBase(String name, String description, String[] alias, boolean canBePickedUp) {
        super(name, description, alias, canBePickedUp);
    }

    public void pickup() {
        System.out.println("You have the "+ this.getName() +" PokeBall now! use the enslaved animal in it for great powers!");

        Rooms currentRoom = currPlayer.getCurrentRoom();
        for (Ability ability : abilities) {
            Inventory.moveThisToRoom(ability, currentRoom);
        }
    }

    public void interaction() {
        if (!Inventory.inBag(this)) {
            System.out.println("first choose the poor animal you want to enslave");
            return;
        }
    }

    protected void addPowerAttack(String name, String desc, int dmg, int cooldown) {
        abilities.add(new AbilityAttack(name, desc, dmg, cooldown));
    }

    protected void addBuff(String name, String desc, int def, int cooldown) {
        abilities.add(new AbilityBuff(name, desc, def, cooldown));
    }

    protected void addDebuff(String name, String desc, int lowDef, int cooldown) {
        abilities.add(new AbilityDebuff(name, desc, -lowDef, cooldown));
    }
}
