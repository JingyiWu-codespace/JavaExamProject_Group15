package JavaExamProject_Group15.Entity.Items.PokeGuns;


import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.StoryTeller.PokeGuns.PokeGunStory.findRivalRoom;

public class AbilityDebuff extends Ability {
    private final int insuranceUpTick;

    public AbilityDebuff(String name, String description, int buff, int cooldown) {
        super(name, description, cooldown);
        if (buff > 0)
            throw new IllegalArgumentException("buff must be negative");
        this.insuranceUpTick = buff;
    }

    public boolean interaction() {
        if (!possibleAct()) return false;
        RoomBase rivalRoom = findRivalRoom();
        HP rivalHP = Inventory.getFromRoom(HP.class, rivalRoom);
        rivalHP.addToInsurance(insuranceUpTick);
        currCooldown++;
        return true;
    }
}
