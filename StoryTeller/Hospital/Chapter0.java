package JavaExamProject_Group15.StoryTeller.Hospital;

import JavaExamProject_Group15.Entity.Items.Chapter0.ITEM_BANDAGE;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_ER;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_ER_STORAGE;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

public class Chapter0 extends BaseHospital {
    public void initiateWorld() {
        ROOM_ER.removeExit(ROOM_RECEPTION_DESK);
        ROOM_ER.getRoomInv().forcePlaceItem(ITEM_ER_PATIENT);
        ROOM_ER_STORAGE.getRoomInv().forcePlaceItem(ITEM_BANDAGE);
    }

    public boolean checkVictory() {
        if (Inventory.inBag(ITEM_BANDAGE.class))
            return false;
        for (RoomBase room : RoomBase.getAllRooms())
            if (Inventory.inRoom(ITEM_BANDAGE.class, room))
                return false;
        return true;
    }

    public void narrativePrint() {
        System.out.println(
                "\n============================== Game intro ==============================" +
                        "\n    You're an intern at a hospital that's seen better days. " +
                        "\n    The echoes of war rumble in the distance, " +
                        "\n    a stark backdrop to the daily battles within these walls. " +
                        "\n    Your job—to assist doctors with medical tools is vital." +
                        "\n    You steel yourself for the day ahead, " +
                        "\n    knowing that every second counts in saving lives."
        );
        System.out.println(
                "\n============================== tutorial ==============================" +
                        "\n    you are tasked with locating bandages in the storage " +
                        "\n    of the Emergency Room to aid an injured patient. " +
                        "\n\n cheat : pickup storage room key -> move to storage room -> pickup bandage -> move back er -> talk with patient"
        );
        System.out.println("\n type 'look around', 'look' or 'help' to see your surroundings");
    }
}
