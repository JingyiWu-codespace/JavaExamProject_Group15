package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.ItemBase;

import JavaExamProject_Group15.Entity.Rooms.Hospital.*;

/**
 * The StoryTeller class manages the narrative and progression of the game's chapters.
 * It controls the flow of the story, checks for chapter completion, and sets up the environment
 * and tasks for each chapter.
 */
public class StoryTeller {
    private int currentChapter;

    /**
     * Constructs a StoryTeller with a specified starting chapter.
     *
     * @param startingChapter The chapter number where the story begins.
     */
    private StoryTeller(int startingChapter) {
        this.currentChapter = startingChapter;
    }

    private void nextChapter() {
        System.out.println("You have completed chapter " + this.currentChapter + "!");
        currentChapter++;
    }

    /**
     * Checks if the player has reached the victory condition, typically by completing all chapters.
     *
     * @return true if the victory condition is met, otherwise false.
     */
    private boolean checkVictory() {
        return this.currentChapter == 3;
    }

    /**
     * Determines if the current chapter is complete based on game-specific conditions.
     *
     * @return true if the current chapter is complete, otherwise false.
     */
    private boolean chapterComplete() {
        switch (this.currentChapter) {
            case 0:
                return ItemBase.ITEM_BANDAGE.getOwningInventory() == null;

            case 1:
                return ItemBase.ITEM_PATIENT_MEDICINE.getOwningInventory() == null;

            case 2:
                return Rooms.ROOM_SURGERY.getRoomInv().checkForItem(ItemBase.ITEM_MOTHER_PATIENT);
        }
        System.out.println("Error: chapterComplete() called with invalid chapter number");
        return false;
    }

    /**
     * Begins the current chapter by setting up the environment and narrating the story.
     * This method prepares the game state for each chapter and prints the narrative introduction.
     */
    private void beginChapter() {
        this.narrativePrint();
//        Player.player.getInventory().resetInventory();
        ROOM_ER.getRoomInv().forceRemove(ItemBase.ITEM_ER_PATIENT, ItemBase.ITEM_PATIENT_MEDICINE, ItemBase.ITEM_MOTHER_PATIENT, ItemBase.ITEM_NURSE);
        ROOM_ER.addExit(ROOM_RECEPTION_DESK);
        switch (this.currentChapter) {
            case 0:
                ROOM_ER.removeExit(ROOM_RECEPTION_DESK);
                ROOM_ER.getRoomInv().forcePlaceItem(ItemBase.ITEM_ER_PATIENT);
                ROOM_ER_STORAGE.getRoomInv().forcePlaceItem(ItemBase.ITEM_BANDAGE);
                break;
            case 1:
                ROOM_ER.getRoomInv().forcePlaceItem(ItemBase.ITEM_PATIENT_MEDICINE);
                ROOM_ER.getRoomInv().forcePlaceItem(ItemBase.ITEM_NURSE);
                break;
            case 2:
                ROOM_ER.getRoomInv().forcePlaceItem(ItemBase.ITEM_MOTHER_PATIENT);
                ROOM_ER_STORAGE.getRoomInv().forcePlaceItem(ItemBase.ITEM_WHEEL_CHAIR);
                break;
            default:
                System.out.println("Error: beginChapter() called with invalid chapter number");
        }
    }

    /**
     * Prints the narrative for the current chapter.
     * This method provides story context for the player.
     */
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
                                "\n The nurse excepts you to figure out the steps on your own, and only tells you an overview of the process. " +
                                "\n managing medication, conducting blood tests and lab work," +
                                "\n interpreting ultrasound results and preparing for the surgical procedure itself.");
                break;
            default:
                System.out.println("Error: narrative() called with invalid chapter number");
        }
    }
}