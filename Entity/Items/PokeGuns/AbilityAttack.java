package JavaExamProject_Group15.Entity.Items.PokeGuns;


import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

public class AbilityAttack extends Ability {
    private final int damage;

    public AbilityAttack(String name, String description, int cooldown, int damage) {
        super(name, description, cooldown);
        this.damage = damage;
    }

    public void interaction() {
        RoomBase rivalRoom = findRivalRoom();
        HP rivalHP = Inventory.getFromRoom(HP.class, rivalRoom);
        rivalHP.addToHP(damage);
    }
}
