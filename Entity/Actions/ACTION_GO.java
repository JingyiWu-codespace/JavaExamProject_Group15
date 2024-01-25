package JavaExamProject_Group15.Entity.Actions;

import JavaExamProject_Group15.Entity.Rooms;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_GO extends ActionBase {
    private ACTION_GO() {
        super(
                "go",
                "Move to a different room",
                new String[]{"move", "walk", "run", "sprint", "enter"}
        );
    }

    private void executeAction(Rooms room) {
        for (Rooms r : currPlayer.getCurrentRoom().getExits())
            if (r == room) {
                currPlayer.setCurrentRoom(r);
                System.out.println("-> You move to the " + r.getName());
                System.out.println("        description:  " + r.getDescription());
                return;
            }

    }
}
    