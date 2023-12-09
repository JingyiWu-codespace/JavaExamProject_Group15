package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.ActionCodes;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

public class StoryTeller {
    private final Player player;
    private final UserParser userIo;
    private int game_timeout = 100;

    public StoryTeller(Player player, UserParser userIo) {
        this.player = player;
        this.userIo = userIo;
    }

    public static void main(String[] args) {
    }

    private void gameLoop() {
        int time_counter = -1;
        while (true) {
            time_counter++;

            // check victory
            if (this.check_victory()) {
                System.out.println("A good days work. Now go sleep.");
                break;
            }

            // check timeout
            if (time_counter > this.game_timeout)
                System.out.println("You are late and people died. You failed.");

            // get and run action
            UserParser.ParsedData parsed_codes = this.userIo.parseInput();
            if (parsed_codes.objectCode == null)
                this.execute_action(parsed_codes.actionCode, parsed_codes.roomCode);
            else
                this.execute_action(parsed_codes.actionCode, parsed_codes.objectCode, parsed_codes.otherObjectCode);
        }
    }

    private boolean check_victory() {
        return false;
    }

    private void execute_action(ActionCodes action_code, Items item_code, Items other_item_code) {
        switch (action_code) {
            // explain available actions
            case ACTION_HELP:
                System.out.println("You can use the following commands:");
                for (ActionCodes temp: ActionCodes.values()) {
                    System.out.println("    - " + temp.get_name());
                    System.out.println("                   details: " + temp.get_description());
                }

                System.out.println("The following items are in your bag:");
                for (Items temp: this.player.getInventory().getItemList()) {
                    System.out.println("    - " + temp.get_name());
                    System.out.println("                   details: " + temp.get_description());
                }

                System.out.println("Room includes the following things/people:");
                for (Items temp: this.player.getCurrentRoom().getInventory().getItemList()) {
                    System.out.println("    - " + temp.get_name());
                    System.out.println("                   details: " + temp.get_description());
                }

                break;

            case ACTION_EXITS:
                System.out.println("You can try to DIRECTLY walk to these exits\n" +
                        "   some exits require interaction with entities:");
                for (Rooms temp: this.player.getCurrentRoom().get_exits()) {
                    System.out.println("    - " + temp.get_name());
                    System.out.println("                   details: " + temp.get_description());
                }
                break;


            case ACTION_INTERACT:
                item_code.interaction(action_code, this.player, other_item_code, other_item_code.get_owning_inventory());
                break;
        }
    }

    private void execute_action(ActionCodes action_code, Rooms room_code) {
        switch (action_code) {
            // move to a different room
            case ACTION_MOVE:
                for (Rooms r : this.player.getCurrentRoom().get_exits())
                    if (r==room_code) {
                        this.player.setCurrentRoom(r);
                        System.out.println("You move to the " + r.get_name());
                        return;
                    }
                System.out.println("You can't go there");
                break;
        }
    }
}
