package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

public class StoryTeller {
    private final Player player = new Player();
    public int gameTimeout = 100;

    public boolean check_victory() {
        return false;
    }

    public void executeAction(Actions action_code, Rooms room_code) {
        switch (action_code) {
            // move to a different room
            case ACTION_MOVE:
                for (Rooms r : this.player.getCurrentRoom().getExits())
                    if (r == room_code) {
                        this.player.setCurrentRoom(r);
                        System.out.println("You move to the " + r.getName());
                        return;
                    }
                System.out.println("You can't go there");
                break;
        }
    }

    public void executeAction(Actions action_code, Items item_code) {
        switch (action_code) {
            // explain available actions
            case ACTION_HELP:
                System.out.println("You can use the following commands:");
                for (Actions temp : Actions.values()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   details: " + temp.getDescription());
                }

                System.out.println("The following items are in your bag:");
                for (Items temp : this.player.getInventory().getItemList()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   details: " + temp.getDescription());
                }

                System.out.println("Room includes the following things/people:");
                for (Items temp : this.player.getCurrentRoom().getInventory().getItemList()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   details: " + temp.getDescription());
                }

                break;

            case ACTION_EXITS:
                System.out.println("You can try to DIRECTLY walk to these exits\n" +
                        "   some exits require interaction with entities:");
                for (Rooms temp : this.player.getCurrentRoom().getExits()) {
                    System.out.println("    - " + temp.getName());
                    System.out.println("                   details: " + temp.getDescription());
                }
                break;


            case ACTION_INTERACT:
                break;
        }
    }
}
