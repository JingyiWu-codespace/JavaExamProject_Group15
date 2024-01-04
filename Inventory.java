package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * The Inventory class represents a collection of items in the game.
 * It provides methods to manage items such as adding, moving, or removing them.
 */
public class Inventory {
    protected List<Items> itemList;
    /**
     * Constructs an Inventory with an initial set of items.
     *
     * @param item_list An array of Items to be initially added to the inventory.
     */
    public Inventory(Items[] item_list) {
        this.itemList = new ArrayList<>(Arrays.asList(item_list));
    }
    /**
     * Checks if a specific item is available in the inventory.
     *
     * @param item The item to check for availability.
     * @return True if the item is found in the inventory, false otherwise.
     */
    public Boolean checkAvailable(Items item) {
        for (Items i : itemList)
            if (i == item) return true;
        return false;
    }
    /**
     * Retrieves the list of items currently in the inventory.
     *
     * @return The list of items in the inventory.
     */
    public List<Items> getItemList() {
        return itemList;
    }
    /**
     * Retrieves a specific item from the inventory.
     *
     * @param item_code The code of the item to be retrieved.
     * @return The item if found, or null if the item is not in the inventory.
     */
    public Items getItem(Items item_code) {
        for (Items i : itemList)
            if (i == item_code)
                return i;
        System.out.println("ERROR > item not found in inventory");
        return null;
    }
    /**
     * Moves an item from this inventory to another specified inventory.
     *
     * @param item        The item to be moved.
     * @param destination The inventory to which the item should be moved.
     */
    public void moveItem(Items item, Inventory destination) {
        if (item.getStationary())
            System.out.println("ERROR > You can't move this item");
        if (!checkAvailable(item))
            System.out.println("ERROR > item is not in this inventory");
        destination.itemList.add(item);
        this.itemList.remove(item);
    }
    /**
     * Forces an item to be added to the inventory, regardless of its current location.
     *
     * @param item The item to be added to the inventory.
     */
    public void forcePlaceItem(Items item) {
        this.itemList.add(item);
    }
    /**
     * Removes an item from the inventory.
     *
     * @param item The item to be removed.
     */
    public void destroyItem(Items item) {
        if (checkAvailable(item)) {
            this.itemList.remove(item);
            return;
        }
        System.out.println("ERROR > item not found in inventory");
    }

    public void resetInventory() {
        this.itemList.clear();
    }
    /**
     * Removes multiple specified items from the inventory.
     *
     * @param items The items to be removed.
     */
    public void forceRemove(Items... items) {
        for (Items it : items)
            it.removeFromWorld();
    }
}

