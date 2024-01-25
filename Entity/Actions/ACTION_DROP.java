package JavaExamProject_Group15.Entity.Actions;

import JavaExamProject_Group15.Entity.Items.ItemBase;

public class ACTION_DROP extends ActionBase {
    private ACTION_DROP() {
        super(
                "drop",
                "leave something in room",
                new String[]{"put", "leave away"}
        );
    }

    private void executeAction(ItemBase item) {
        item.dropItem();
    }
}

    