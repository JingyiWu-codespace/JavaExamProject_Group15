package JavaExamProject_Group15.StoryTeller.Hospital;

import JavaExamProject_Group15.Entity.Items.Chapter1.ITEM_PATIENT_MEDICINE;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;
import JavaExamProject_Group15.StoryTeller.StoryTeller;

public class Chapter1 extends BaseHospital {
    protected int deadlineTimer = 20;

    public StoryTeller nextChapter() {
        return new Chapter2();
    }

    public boolean checkVictory() {
        if (Inventory.inBag(ITEM_PATIENT_MEDICINE.class))
            return false;
        for (RoomBase room : RoomBase.getAllRooms())
            if (Inventory.inRoom(ITEM_PATIENT_MEDICINE.class, room))
                return false;
        return true;
    }

    public void initiateChapter() {
//        ROOM_ER.getRoomInv().forcePlaceItem(ItemBase.ITEM_PATIENT_MEDICINE);
//        ROOM_ER.getRoomInv().forcePlaceItem(ItemBase.ITEM_NURSE);
    }

    public void narrativePrint() {
        System.out.println(
                "\n============================== Task 1 ==============================" +
                        "\n" +
                        "\n A nurse approaches you with a sense of urgency. " +
                        "\n the patient in dire need of medication." +
                        "\n she says, her voice laced with concern." +
                        "\n 'I need you to handle this with utmost priority.'" +
                        "\n 'Your first task is to construct a register form at the reception desk for the patient.'" +
                        "\n 'The information you gather here is crucial for accurate treatment'" +
                        "\n 'With the form in hand, your next stop is the doctor's office.'" +
                        "\n 'The doctor, after examining the form, writes a detailed prescription.'" +
                        "\n 'With the prescription now in your possession, you make your way to the pharmacy.'" +
                        "\n 'Your final task is to deliver the medication back here.'" +
                        "\n\n cheat : move to info desk -> grab form -> move er -> talk to patient -> move to info desk ->" +
                        "\n  move to office -> talk doctor -> move pharmacy -> talk to the clerk -> move to info desk -> move to er -> talk to nurse"
        );
    }
}
