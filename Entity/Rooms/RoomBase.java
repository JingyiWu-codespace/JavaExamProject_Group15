package JavaExamProject_Group15.Entity.Rooms;

import JavaExamProject_Group15.Entity.BasicDataHolder;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

import java.util.ArrayList;
import java.util.List;

public class RoomBase extends BasicDataHolder {
    private static final List<RoomBase> allRooms = new ArrayList<>();
    private final Inventory inventory;
    private List<RoomBase> exits = new ArrayList<>();

    protected RoomBase(String name, String description, String[] alias, ItemBase[] item_list) {
        super(name, description, alias);
        this.inventory = new Inventory(item_list);

        for (RoomBase r : allRooms)
            if (r.getClass() == this.getClass())
                throw new IllegalArgumentException("room already exists");
        allRooms.add(this);
    }

    public static RoomBase[] getAllRooms() {
        return allRooms.toArray(new RoomBase[0]);
    }

    public static <T extends RoomBase> RoomBase getRoomObj(Class<T> room) {
        for (RoomBase r : allRooms)
            if (r.getClass() == room) return r;
        throw new IllegalArgumentException("room not found");
    }

    public static void bidirPassage(RoomBase roomA, RoomBase roomB) {
        RoomBase.oneWayPass(roomA, roomB);
        RoomBase.oneWayPass(roomB, roomA);
    }

    public static void bidirPassage(Class<? extends RoomBase> roomA, Class<? extends RoomBase> roomB) {
        RoomBase.bidirPassage(RoomBase.getRoomObj(roomA), RoomBase.getRoomObj(roomB));
    }

    protected static void oneWayPass(RoomBase roomA, RoomBase roomB) {
        if (roomA.exits.contains(roomB)) return;
        roomA.exits.add(roomB);
    }

    public static void oneWayPass(Class<? extends RoomBase> roomA, Class<? extends RoomBase> roomB) {
        RoomBase.oneWayPass(RoomBase.getRoomObj(roomA), RoomBase.getRoomObj(roomB));
    }

    protected static void removeAllExits(RoomBase room) {
        room.exits = new ArrayList<RoomBase>();
    }

    public static <T extends RoomBase> void removeAllExits(Class<T> roomClass) {
        RoomBase.removeAllExits(RoomBase.getRoomObj(roomClass));
    }

    public static List<RoomBase> getExits(RoomBase room) {
        return room.exits;
    }

    public static Inventory getRoomInv(RoomBase room) {
        return room.inventory;
    }

    public void playerEnter() {
        System.out.println("-> You move to the " + this.getName());
        System.out.println("        description:  " + this.getDescription());
    }
}


//static {
//        RoomBase.bidirPassage(ROOM_RECEPTION_DESK, ROOM_ER);
//        RoomBase.bidirPassage(ROOM_RECEPTION_DESK, ROOM_PHARMACY);
//        RoomBase.bidirPassage(ROOM_RECEPTION_DESK, ROOM_OFFICE);
//        RoomBase.bidirPassage(ROOM_RECEPTION_DESK, ROOM_ELEVATOR);
//
//        RoomBase.bidirPassage(ROOM_HALLWAY, ROOM_WARD);
//        RoomBase.bidirPassage(ROOM_HALLWAY, ROOM_LABORATORY);
//        RoomBase.bidirPassage(ROOM_HALLWAY, ROOM_ULTRASOUND);
//
//        ROOM_ER_STORAGE.addExits(ROOM_ER);
//    }