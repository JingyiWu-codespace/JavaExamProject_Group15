package JavaExamProject_Group15.Entity.Items.PokeGuns;


import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.StoryTeller.PokeGuns.PokeGunStory.findRivalRoom;

public class AbilityAttack extends Ability {
    private final int damage;

    public AbilityAttack(String name, String description, int damage, int cooldown) {
        super(name, description, cooldown);
        this.damage = damage;
    }

    public boolean interaction() {
        if (!possibleAct()) return false;
        RoomBase rivalRoom = findRivalRoom();
        HP rivalHP = Inventory.getFromRoom(HP.class, rivalRoom);
        rivalHP.addToHP(damage);
        currCooldown++;
        return true;
    }
}
