package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static JavaExamProject_Group15.Player.currPlayer;

/**
 * The Inventory class represents a collection of items in the game.
 * It provides methods to manage items such as adding, moving, or removing them.
 */
public class Inventory {
    protected List<ItemBase> itemList;

    /**
     * Constructs an Inventory with an initial set of items.
     *
     * @param item_list An array of Items to be initially added to the inventory.
     */
    public Inventory(ItemBase[] item_list) {
        this.itemList = new ArrayList<>(Arrays.asList(item_list));
    }

    // *****************************************************
    /**
     * Checks if a specific item is available in the inventory.
     *
     * @param item The item to check for availability.
     * @return True if the item is found in the inventory, false otherwise.
     */
    private Boolean checkForItem(ItemBase item) {
        for (ItemBase i : itemList)
            if (i == item) return true;
        return false;
    }

    private void removeItem(ItemBase item) {
        if (this.checkForItem(item)) {
            this.itemList.remove(item);
            return;
        }
        this.itemList.remove(item);
    }

    public List<ItemBase> getItemList() {
        return itemList;
    }

    // *****************************************************
    private static List<Inventory> getOwningInvs(ItemBase item) {
        List<Inventory> allInvs = new ArrayList<>();
        if (inBag(item))
            allInvs.add(currPlayer.getInventory());
        for (Rooms r : Rooms.values())
            if (r.getRoomInv().checkForItem(item))
                allInvs.add(r.getRoomInv());
        return allInvs;
    }

    public static void removeFromAllInvs(ItemBase item) {
        List<Inventory> ownInv = getOwningInvs(item);
        if (ownInv.isEmpty()) return;
        ownInv.forEach(inv -> inv.removeItem(item));
    }

    // *****************************************************
    // Movement related functions
    private static void moveItemTo(ItemBase item, Inventory targetInv) {
        removeFromAllInvs(item);
        targetInv.itemList.add(item);
    }

    public static void moveThisToRoom(ItemBase item, Rooms room) {
        moveItemTo(item, room.getRoomInv());
    }

    public static void moveThisToBag(ItemBase item) {
        moveItemTo(item, currPlayer.getInventory());
    }

    // *****************************************************
    // check for accessibility
    private static <T extends ItemBase> Boolean isInInv(Class<T> clazz, Inventory inv) {
        for (ItemBase i : inv.itemList)
            if (i.getClass() == clazz) return true;
        return false;
    }

    private static <T extends ItemBase> Boolean inRoom(Class<T> clazz, Rooms room) {
        return isInInv(clazz, room.getRoomInv());
    }

    public static <T extends ItemBase> Boolean inBag(Class<T> clazz) {
        return isInInv(clazz, currPlayer.getInventory());
    }

    public static Boolean inBag(ItemBase item) {
        return inBag(item.getClass());
    }

    public static Boolean inRoom(ItemBase item, Rooms room) {
        return inRoom(item.getClass(), room);
    }

    // *****************************************************
    public static <T extends ItemBase> T getFromRoom(Class<T> clazz, Rooms room) {
        return Inventory.findObjectFromInv(clazz, room.getRoomInv());
    }

    public static <T extends ItemBase> T getFromBag(Class<T> clazz) {
        return Inventory.findObjectFromInv(clazz, currPlayer.getInventory());
    }

    // getting object
    private static <T extends ItemBase> T findObjectFromInv(Class<T> clazz, Inventory inv) {
        for (ItemBase i : inv.itemList)
            if (i.getClass() == clazz)
                return (T) i;
        System.out.println("ERROR: Can't find the object");
        return null;
    }

}

