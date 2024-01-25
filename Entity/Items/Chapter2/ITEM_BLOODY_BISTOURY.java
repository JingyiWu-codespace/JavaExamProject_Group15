package JavaExamProject_Group15.Entity.Items.Chapter2;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Inventory;

public class ITEM_BLOODY_BISTOURY extends ItemBase {
    public ITEM_BLOODY_BISTOURY() {
        super(
                "bloody bistoury",
                "surgery knife, need to be disinfect with the alcohol in the ward",
                new String[]{"bloody knife"},
                false
        );
    }

    public void interaction() {
        if (!Inventory.inBag(ITEM_ALCOHOL.class)) {
            System.out.println("this knifes are bloody, better not touch them without disinfecting them with alcohol first");
            return;
        }
        System.out.println("you got the clean bistoury now");
        Inventory.removeFromAllInvs(this);
        ITEM_ALCOHOL alcohol = Inventory.getFromBag(ITEM_ALCOHOL.class);
        Inventory.removeFromAllInvs(alcohol);
        ITEM_BISTOURY knife = new ITEM_BISTOURY();
        knife.pickup();
    }

    public void pickup() {
        this.interaction();
    }

}

    