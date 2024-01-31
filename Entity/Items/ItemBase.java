package JavaExamProject_Group15.Entity.Items;

import JavaExamProject_Group15.Entity.BasicDataHolder;
import JavaExamProject_Group15.Inventory;

import static JavaExamProject_Group15.Player.currPlayer;

public class ItemBase extends BasicDataHolder {
    private final boolean stationary;

    protected ItemBase(String name, String description, String[] aliases, Boolean stationary) {
        super(name, description, aliases);
        this.stationary = stationary;
    }

    public void interaction() {
        System.out.println("Can't figure out any interaction");
    }

    public void dropItem() {
        if (!Inventory.inBag(this)) {
            System.out.println("You can't drop this item as its not in your bag");
            return;
        }
        if (this.getStationary()) {
            System.out.println("You can't drop this entity, because its stationary");
            return;
        }

        Inventory.moveThisToRoom(this, currPlayer.getCurrentRoom());
        System.out.println("You dropped the item, " + this.getName());
    }

    public void pickup() {
        if (this.getStationary()) {
            System.out.println("You can't pick up this entity, because its stationary");
            return;
        }
        if (Inventory.inBag(this)) {
            System.out.println("You already have this item in your bag");
            return;
        }
        if (!Inventory.inRoom(this, currPlayer.getCurrentRoom())) {
            System.out.println("You can't pick up this item as its not in this area");
            return;
        }

        Inventory.moveThisToBag(this);
        System.out.println("You picked up the item, " + this.getName());
    }

    protected Boolean getStationary() {
        return this.stationary;
    }

    protected void resetBasicInfo(String name, String description, String[] aliases) {
        // todo: check if there is any item with the same name and aliases
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }
}
