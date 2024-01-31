package JavaExamProject_Group15.Entity.Actions.Hospital;


import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_GO extends ActionBase {
    public ACTION_GO() {
        super(
                "go",
                "Move to a different room",
                new String[]{"move", "walk", "run", "sprint", "enter"}
        );
    }

    public boolean executeAction(RoomBase room) {
        if (currPlayer.getCurrentRoom() == room) {
            System.out.println("you are already in "+room.getName());
            return false;
        }
        for (RoomBase r : RoomBase.getExits(currPlayer.getCurrentRoom()))
            if (r == room) {
                currPlayer.setCurrentRoom(r);
                return false;
            }
        System.out.println("you can't go to "+room.getName()+" like that");
        return false;
    }
}
    