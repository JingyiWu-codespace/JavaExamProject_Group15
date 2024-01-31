package JavaExamProject_Group15.Entity.Items.PokeGuns;


import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Player.currPlayer;

public class AbilityBuff extends Ability {
    private final int insuranceUpTick;

    public AbilityBuff(String name, String description, int cooldown, int buff) {
        super(name, description, cooldown);
        if (buff < 0) {
            throw new IllegalArgumentException("buff must be positive");
        }
        this.insuranceUpTick = buff;
    }

    public void interaction() {
        RoomBase myRoom = currPlayer.getCurrentRoom();
        HP myHP = Inventory.getFromRoom(HP.class, myRoom);
        myHP.addToInsurance(insuranceUpTick);
    }
}
