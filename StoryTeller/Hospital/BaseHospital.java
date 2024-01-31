package JavaExamProject_Group15.StoryTeller.Hospital;

import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_ER;
import JavaExamProject_Group15.StoryTeller.StoryTeller;

public abstract class BaseHospital implements StoryTeller {
    private int deadlineTimer = 20;

    public void cleanLastChapter() {
        ROOM_ER.getRoomInv().forceRemove(ItemBase.ITEM_ER_PATIENT, ItemBase.ITEM_PATIENT_MEDICINE, ItemBase.ITEM_MOTHER_PATIENT, ItemBase.ITEM_NURSE);
        ROOM_ER.addExit(ROOM_RECEPTION_DESK);
    }

    public boolean checkLoss() {
        return this.deadlineTimer <= 0;
    }

    public void singleEpoch() {
        this.deadlineTimer--;
    }
}
