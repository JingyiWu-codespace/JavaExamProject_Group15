package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import static JavaExamProject_Group15.Entity.Rooms.*;

public class StoryTeller {
    private int currentChapter;

    public StoryTeller(int startingChapter) {
        this.currentChapter = startingChapter;
    }

    public void nextChapter() {
        System.out.println("You have completed chapter " + this.currentChapter + "!");
        currentChapter++;
    }

    public boolean checkVictory() {
        return this.currentChapter == 3;
    }

    public boolean chapterComplete() {
        switch (this.currentChapter) {
            case 0:
                return Items.ITEM_BANDAGE.getOwningInventory() == null;

            case 1:
                return Items.ITEM_PATIENT_MEDICINE.getOwningInventory() == null;

            case 2:
                return Rooms.ROOM_SURGERY.getInventory().checkAvailable(Items.ITEM_MOTHER_PATIENT);
        }
        System.out.println("Error: chapterComplete() called with invalid chapter number");
        return false;
    }


    public void beginChapter() {
        this.narrativePrint();
        Player.player.getInventory().resetInventory();
        ROOM_ER.getInventory().forceRemove(Items.ITEM_ER_PATIENT, Items.ITEM_PATIENT_MEDICINE, Items.ITEM_MOTHER_PATIENT, Items.ITEM_NURSE);
        ROOM_ER.addExit(ROOM_RECEPTION_DESK);
        switch (this.currentChapter) {
            case 0:
                ROOM_ER.removeExit(ROOM_RECEPTION_DESK);
                ROOM_ER.getInventory().forcePlaceItem(Items.ITEM_ER_PATIENT);
                break;
            case 1:
                ROOM_ER.getInventory().forcePlaceItem(Items.ITEM_PATIENT_MEDICINE);
                ROOM_ER.getInventory().forcePlaceItem(Items.ITEM_NURSE);
                break;
            case 2:
                ROOM_ER.getInventory().forcePlaceItem(Items.ITEM_MOTHER_PATIENT);
                ROOM_ER_STORAGE.getInventory().forcePlaceItem(Items.ITEM_WHEEL_CHAIR);
                break;
            default:
                System.out.println("Error: beginChapter() called with invalid chapter number");
        }
    }

    private void narrativePrint() {
        switch (this.currentChapter) {
            case 0:
                System.out.println(
                        "\n============================== Game intro ==============================" +
                                "\n    You're an intern at a hospital that's seen better days. " +
                                "\n    The echoes of war rumble in the distance, " +
                                "\n    a stark backdrop to the daily battles within these walls. " +
                                "\n    Your job—to assist doctors with medical tools is vital." +
                                "\n    You steel yourself for the day ahead, " +
                                "\n    knowing that every second counts in saving lives."
                );
                System.out.println(
                        "\n============================== tutorial ==============================" +
                        "\n    you are tasked with locating bandages in the storage " +
                        "\n    of the Emergency Room to aid an injured patient. " +
                        "\n\n cheat : pickup storage room key -> move to storage room -> pickup bandage -> move back er -> talk with patient"
                );
                System.out.println("\n type 'look around', 'look' or 'help' to see your surroundings");
                break;
            case 1:
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
                /*
                 She awaits you, the anticipation clear in her eyes.
                 Handing over the medication, you feel a sense of accomplishment.
                 You've played a crucial role in the patient's care.
                 The nurse nods in gratitude, ready to administer the medication.
                 \\\"Thank you,\\\" she says, \\\"You've been a great help.\\\"\");")
                 */
                break;
            case 2:
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
                    "\n The nurse excects you to figure out the steps on your own, and only tells you an overview of the process. " +
                    "\n managing medication, collect register form, conducting blood tests and lab work,"+
                    "\n interpreting ultrasound results and preparing for the surgical procedure itself.");
                break;
            default:
                System.out.println("Error: narrative() called with invalid chapter number");
        }
    }
}