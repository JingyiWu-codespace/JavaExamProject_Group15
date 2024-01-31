package JavaExamProject_Group15.Entity.Actions;


import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_GO extends ActionBase {
    private ACTION_GO() {
        super(
                "go",
                "Move to a different room",
                new String[]{"move", "walk", "run", "sprint", "enter"}
        );
    }

    private void executeAction(RoomBase room) {
        for (RoomBase r : RoomBase.getExits(currPlayer.getCurrentRoom()))
            if (r == room) {
                currPlayer.setCurrentRoom(r);
                return;
            }

    }
}
    