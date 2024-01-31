package JavaExamProject_Group15.Entity.Actions.Hospital;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Items.ItemBase;

public class ACTION_DROP extends ActionBase {
    public ACTION_DROP() {
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

    