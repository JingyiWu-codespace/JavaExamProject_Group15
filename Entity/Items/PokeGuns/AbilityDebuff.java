package JavaExamProject_Group15.Entity.Items.PokeGuns;

import JavaExamProject_Group15.Entity.Rooms;
import JavaExamProject_Group15.Inventory;

public class AbilityDebuff extends Ability {
    private final int insuranceUpTick;
    public AbilityDebuff(String name, String description, int cooldown, int buff) {
        super(name, description, cooldown);
        if (buff > 0) {
            throw new IllegalArgumentException("buff must be negative");
        }
        this.insuranceUpTick = buff;
    }

    public void interaction() {
        Rooms rivalRoom = findRivalRoom();
        HP rivalHP = Inventory.getFromRoom(HP.class, rivalRoom);
        rivalHP.addToInsurance(insuranceUpTick);
    }
}
