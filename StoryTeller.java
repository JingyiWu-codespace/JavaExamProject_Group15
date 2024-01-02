package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import static JavaExamProject_Group15.Player.player;

public class StoryTeller {
    public int gameTimeout = 100;

    public boolean checkVictory() {
        return Items.BANDAGE.getOwningInventory() == null;

//        return Items.MEDICINE.getOwningInventory(Items.MEDICINE) == null;
//
//        if (Rooms.ROOM_SURGERY.getInventory().itemList.contains(Items.ER_PATIENT3))
//            return true;
//        return false;
    }
}