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

    public boolean executeAction(ItemBase item, RoomBase room) {
        if (item == null && room == null)
            return executeAction();
        else if (item == null)
            return executeAction(room);
        else if (room == null)
            return executeAction(item);
        else
            throw new IllegalArgumentException("Cannot execute action");
    }

    /**
     * Executes an action in the context of a specific item.
     *
     * @param item The item involved in the action.
     */

    public boolean executeAction(ItemBase item) {
        System.out.println("doing " + this.getName() + ": I cant figure anything to do with " + item.getName());
        return false;
    }

    /**
     * Executes an action in the context of a specific room.
     *
     * @param room The room involved in the action.
     */
    public boolean executeAction(RoomBase room) {
        System.out.println("doing " + this.getName() + ": what am I suppose to do to " + room.getName());
        return false;
    }

    /**
     * Executes an action that does not require a specific item or room context.
     */
    public boolean executeAction() {
        System.out.println("doing " + this.getName() + ":this action in isolation is not possible");
        return false;
    }

    public boolean executeAction(ActionBase action) {
        System.out.println("what to do, what not to do, that is the question");
        return false;
    }
}
