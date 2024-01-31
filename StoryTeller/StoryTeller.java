package JavaExamProject_Group15.StoryTeller;


import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public interface StoryTeller {
    boolean checkVictory();

    boolean checkLoss();

    void cleanLastChapter();

    void initiateChapter();

    void narrativePrint();

    void nextTurn(ActionBase prevActionCode, ItemBase prevItemCode, RoomBase prevRoomCode);

    StoryTeller nextChapter();
}