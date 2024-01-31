package JavaExamProject_Group15.Entity.Actions.Hospital;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import static JavaExamProject_Group15.Player.currPlayer;

public class ACTION_EXITS extends ActionBase {
    public ACTION_EXITS() {
        super(
                "exits",
                "Show possible rooms to go to",
                new String[]{"exit list", "exit", "where to go"}
        );
    }

    public boolean executeAction() {
        System.out.println("You can try to DIRECTLY walk to these exits\n" +
                "   some exits require interaction with entities:");
        for (RoomBase room : RoomBase.getExits(currPlayer.getCurrentRoom()))
            room.printInformation();
        return false;
    }
}

    