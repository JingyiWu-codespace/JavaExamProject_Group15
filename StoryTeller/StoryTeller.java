package JavaExamProject_Group15.StoryTeller;


import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public abstract class StoryTeller implements StoryTellerInterface {
    public void initiateChapter() {
        roomInitiate();
        playerInitiate();
        actionInitiate();
    }
}

interface StoryTellerInterface {
    boolean checkVictory();

    boolean checkLoss();

    void initiateChapter();

    void roomInitiate();

    void actionInitiate();

    void playerInitiate();

    void narrativePrint();

    void endTurnPostAction(ActionBase prevActionCode, ItemBase prevItemCode, RoomBase prevRoomCode, boolean actionReturn);

    StoryTeller nextChapter();
}