package WPO_final;

import WPO_final.Entity.ActionCodes;
import WPO_final.Entity.Items;
import WPO_final.Entity.Rooms;

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
            this.userIo.parseInput();
            if (this.userIo.parseInput().objectCode == null)
                this.execute_action(this.userIo.parseInput().actionCode, this.userIo.parseInput().roomCode);
            else
                this.execute_action(this.userIo.parseInput().actionCode, this.userIo.parseInput().objectCode, this.userIo.parseInput().otherObjectCode);
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
                for (String[] temp : userIo.getActionStrings()) {
                    String action_name = temp[0];
                    String action_desc = temp[1];
                    System.out.println("    - " + action_name);
                    System.out.println("                   details: " + action_desc);
                }
                break;

            case ACTION_EXITS:
                System.out.println("You can go to the following rooms:");
                for (Rooms r : this.player.getCurrentRoom().get_exits())
                    System.out.println("    - " + r.get_name());
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
