package JavaExamProject_Group15.StoryTeller.Hospital;

import JavaExamProject_Group15.Entity.Actions.Hospital.*;
import JavaExamProject_Group15.Entity.Items.Chapter0.ITEM_BANDAGE;
import JavaExamProject_Group15.Entity.Rooms.Hospital.Chapter0.*;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;
import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.StoryTeller.StoryTeller;

import static JavaExamProject_Group15.Player.currPlayer;

public class Chapter0 extends BaseHospital {
    public Chapter0() {
        super();
        this.deadlineTimer = 10;
    }

    public StoryTeller nextChapter() {
        return new Chapter1();
    }

    public void roomInitiate() {
        RoomBase er = new ROOM_ER();
        RoomBase erStorage = new ROOM_ER_STORAGE();

        RoomBase.oneWayPass(erStorage, er);
    }

    public void actionInitiate() {
        new ACTION_GO();
        new ACTION_EXITS();
        new ACTION_DROP();
        new ACTION_GRAB();
        new ACTION_INVENTORY_CHECK();
        new ACTION_MAP();
        new ACTION_ROOM_CHECK();
    }

    public void playerInitiate() {
        currPlayer = new Player(RoomBase.getRoomObj(ROOM_ER.class));
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
