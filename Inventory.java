package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    protected List<Items> itemList;

    public Inventory(Items[] item_list) {
        this.itemList = new ArrayList<>(Arrays.asList(item_list));
    }

    public Boolean checkAvailable(Items item) {
        for (Items i : itemList)
            if (i == item) return true;
        return false;
    }

    public List<Items> getItemList() {
        return itemList;
    }

    public Items getItem(Items item_code) {
        for (Items i : itemList)
            if (i == item_code)
                return i;
        System.out.println("ERROR > item not found in inventory");
        return null;
    }

    public Boolean moveItem(Items item, Inventory destination) {
        if (item.getStationary()) {
            System.out.println("ERROR > You can't move this item");
            return false;
        }
        if (!checkAvailable(item)) {
            System.out.println("ERROR > item is not in this inventory");
            return false;
        }
        destination.itemList.add(item);
        this.itemList.remove(item);
        return true;
    }

    public void destroyItem(Items item) {
        if (checkAvailable(item)) {
            this.itemList.remove(item);
            return;
        }
        System.out.println("ERROR > item not found in inventory");
    }
}

