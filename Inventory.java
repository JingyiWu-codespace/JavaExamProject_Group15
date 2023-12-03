package WPO_final;

import WPO_final.Entity.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    protected List<Items> itemList;

    public Inventory(Items[] item_list) {
        this.itemList = new ArrayList<>(Arrays.asList(item_list));
    }

    public List<Items> getItemList() {
        return itemList;
    }

    public Boolean check_available(Items item) {
        for (Items i : itemList)
            if (i == item) return true;
        return false;
    }

    public Boolean move_item(Items item, Inventory destination) {
        if (item.get_stationary()) {
            System.out.println("You can't move this item");
            return false;
        }
        if (!check_available(item)) {
            System.out.println("item is not in this inventory");
            return false;
        }
        destination.itemList.add(item);
        this.itemList.remove(item);
        return true;
    }

    public void destroy_item(Items item) {
        if (check_available(item))
            this.itemList.remove(item);
    }

    public Items get_item(Items item_code) {
        for (Items i : itemList)
            if (i == item_code)
                return i;
        return null;
    }
}

