package JavaExamProject_Group15.StoryTeller.Hospital;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.StoryTeller.StoryTeller;

public abstract class BaseHospital extends StoryTeller {
    protected int deadlineTimer;

    public boolean checkLoss() {
        boolean loss= this.deadlineTimer <= 0;
        if (loss)
            System.out.println("You have run out of time. the patient died.");
        return loss;
    }


    public void endTurnPostAction(ActionBase prevActionCode, ItemBase prevItemCode, RoomBase prevRoomCode, boolean prevActionSuccess) {
        this.deadlineTimer--;
    }
}
