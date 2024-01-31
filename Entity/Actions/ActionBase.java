package JavaExamProject_Group15.Entity.Actions;

import JavaExamProject_Group15.Entity.BasicDataHolder;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import java.util.ArrayList;
import java.util.List;

public class ActionBase extends BasicDataHolder {
    public static final List<ActionBase> actionList = new ArrayList<>();

    protected ActionBase(String name, String description, String[] aliases) {
        super(name, description, aliases);
        if (actionList.contains(this))
            throw new IllegalArgumentException("Action already exists");
        actionList.add(this);
    }

    /**
     * Executes an action based on the provided item and room.
     * If both item and room are null, executes the action without any context.
     * If only one is provided, executes the action in context of the item or room.
     *
     * @param item The item involved in the action, if applicable.
     * @param room The room involved in the action, if applicable.
     */

    public void executeAction(ItemBase item, RoomBase room) {
        if (item == null && room == null)
            executeAction();
        else if (item == null)
            executeAction(room);
        else if (room == null)
            executeAction(item);
        else
            System.out.println("ERROR: BOTH ROOM AND ITEM ARE OBJECTS");
    }

    /**
     * Executes an action in the context of a specific item.
     *
     * @param item The item involved in the action.
     */

    private void executeAction(ItemBase item) {
        System.out.println("doing " + this.getName() + ": I cant figure anything to do with " + item.getName());
    }

    /**
     * Executes an action in the context of a specific room.
     *
     * @param room The room involved in the action.
     */
    private void executeAction(RoomBase room) {
        System.out.println("doing " + this.getName() + ": what am I suppose to do to " + room.getName());
    }

    /**
     * Executes an action that does not require a specific item or room context.
     */
    private void executeAction() {
        System.out.println("doing " + this.getName() + ":this action in isolation is not possible");
    }

    private void executeAction(ActionBase action) {
        System.out.println("what to do, what not to do, that is the question");
    }
}
