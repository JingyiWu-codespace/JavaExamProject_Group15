package JavaExamProject_Group15.StoryTeller.Hospital;

import JavaExamProject_Group15.Entity.Items.Chapter2.ITEM_MOTHER_PATIENT;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_ER;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_ER_STORAGE;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_SURGERY;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Inventory;

public class Chapter2 extends BaseHospital {

    public boolean checkVictory() {
        RoomBase surgRoom = RoomBase.getRoomObj(ROOM_SURGERY.class);
        return Inventory.inRoom(ITEM_MOTHER_PATIENT.class, surgRoom);
    }

    public void initiateWorld() {
        ROOM_ER.getRoomInv().forcePlaceItem(ItemBase.ITEM_MOTHER_PATIENT);
        ROOM_ER_STORAGE.getRoomInv().forcePlaceItem(ItemBase.ITEM_WHEEL_CHAIR);
    }

    public void narrativePrint() {
        System.out.println(
                "\n============================== Task 2 ==============================" +
                        "\n In this chapter, you face a gripping medical challenge adapted from a real-life documentary story. " +
                        "\n A soon-to-be mother, diagnosed with a congenital heart defect known as Bicuspid Aortic Valve, requires cardiac surgery. " +
                        "\n At 27 weeks pregnant with twins, the stakes are incredibly high. " +
                        "\n The surgery poses a grave risk of cardiac arrest, threatening the lives of both the mother and her unborn twins " +
                        "\n – with a 30% chance of fatality for the babies.\n" +
                        "\n " +
                        "\n As the surgery approaches, the twins are soon to be born, adding urgency to an already delicate situation. " +
                        "\n Your task is to ensure the smooth progression of the surgery, including the crucial preparatory stages. " +
                        "\n The nurse excepts you to figure out the steps on your own, and only tells you an overview of the process. " +
                        "\n managing medication, conducting blood tests and lab work," +
                        "\n interpreting ultrasound results and preparing for the surgical procedure itself."
        );
    }
}
