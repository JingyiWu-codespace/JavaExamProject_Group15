package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import java.io.IOException;
import java.util.Scanner;

import static JavaExamProject_Group15.Entity.Rooms.*;

public class GameManager {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean continueGame = true;
        int currentPart = 1;

        while (continueGame) {
            UserParser userIo = new UserParser();
            StoryTeller storyTeller = new StoryTeller();
            Player player = Player.player;


            getUserChoiceForStoryline();

            switch (currentPart) {

                case 1:
                    playPart1(userIo, storyTeller, player);

                    if (storyTeller.checkVictoryPart1()) {
                        System.out.println("进入成功检查part1");
                        continueGame = askToContinue(scanner, "Part 1 finished. Do you want to continue to Part 2? (yes/no)");
                        if (continueGame) {
                            currentPart = 2;
                        }
                    }
                    break;


                case 2:
                    playPart2(userIo, storyTeller, player);
                    if (storyTeller.checkVictoryPart2()) {
                        System.out.println("进入成功检查part2");
                        continueGame = askToContinue(scanner, "Part 2 finished. Do you want to continue to Part 3? (yes/no)");
                        if (continueGame) {
                            currentPart = 3;
                        }
                    }
                    break;
//
                case 3:
                    playPart3(userIo, storyTeller, player);
                    if (storyTeller.checkVictoryPart3()) {
                        storyTeller.checkVictoryPart3();
                        System.out.println("Congratulations! You have completed all parts of the game.");
                        continueGame = false;
                    }
                    break;
                default:
                    break;
            }


            if (continueGame) {
                resetGameState();
            }
        }

        System.out.println("Thank you for playing!");
    }

    private static void playPart1(UserParser userIo, StoryTeller storyTeller, Player player) {
        displayOpeningNarrative();
        boolean partCompleted = false;
        while (!partCompleted) {
            UserParser.ParsedDataHolder parsed_codes = null;
            while (parsed_codes == null) {
                parsed_codes = userIo.getUserInput();
            }

            if (parsed_codes.actionCode == Actions.ACTION_QUIT)
                break;

            if (parsed_codes.itemCode == null && parsed_codes.roomCode == null)
                storyTeller.executeAction(parsed_codes.actionCode);
            else if (parsed_codes.itemCode == null)
                storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.roomCode);
            else
                storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.itemCode);
            if (storyTeller.checkVictoryPart1()) {
                System.out.println("Congratulations! You have completed Part 1.");
                resetGameState();
                partCompleted = true; // Set the flag to true to exit the loop
            }

        }
    }

    private static void playPart2(UserParser userIo, StoryTeller storyTeller, Player player) {
        // Logic for playing Part 2
        Part2Narrative();
        ROOM_ER.setExits(ROOM_RECEPTION_DESK);
        ROOM_RECEPTION_DESK.setExits(ROOM_ER, ROOM_PHARMACY, ROOM_OFFICE);
        boolean partCompleted = false;
        while (!partCompleted) {
            UserParser.ParsedDataHolder parsed_codes = null;
            while (parsed_codes == null)
                parsed_codes = userIo.getUserInput();

            if (parsed_codes.actionCode == Actions.ACTION_QUIT)
                break;

            if (parsed_codes.itemCode == null && parsed_codes.roomCode == null)
                storyTeller.executeAction(parsed_codes.actionCode);
            else if (parsed_codes.itemCode == null)
                storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.roomCode);
            else
                storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.itemCode);
            if (storyTeller.checkVictoryPart2()) {
                System.out.println("Congratulations! You have completed Part 2.");
                partCompleted = true; // Set the flag to true to exit the loop
            }
        }
    }


    private static void playPart3(UserParser userIo, StoryTeller storyTeller, Player player) {
        // Logic for playing Part 3
        Part3Narrative();

        while (true) {
            UserParser.ParsedDataHolder parsed_codes = null;
            while (parsed_codes == null)
                parsed_codes = userIo.getUserInput();

            if (parsed_codes.actionCode == Actions.ACTION_QUIT)
                break;

            if (parsed_codes.itemCode == null && parsed_codes.roomCode == null)
                storyTeller.executeAction(parsed_codes.actionCode);
            else if (parsed_codes.itemCode == null)
                storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.roomCode);
            else
                storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.itemCode);
        }
    }

    private static boolean askToContinue(Scanner scanner, String message) {
        System.out.println(message);
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes") || response.equals("true")) {
            return true;
        } else if (response.equals("no") || response.equals("false")) {
            return false;
        } else if (response.equals("q") || response.equals("quit")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            return askToContinue(scanner, message); // Recursively ask again for valid input
        }
    }


    private static void displayOpeningNarrative() {
        System.out.println("\n====================== Game intro ======================");
        System.out.println("\n You're an intern at a hospital that's seen better days. " +
                "\n The echoes of war rumble in the distance, " +
                "\n a stark backdrop to the daily battles within these walls. " +
                "\n Your job—to assist doctors with medical tools is vital." +
                "\n You steel yourself for the day ahead, " +
                "\n knowing that every second counts in saving lives." +
                "\n ================== Task 1 ====================" +
                "\n you are tasked with locating bandages in the storage of the [ Emergency Room ]" +
                "\n to aid an injured patient. " +
                "\n ======================================" +
                "\n example : pickup key-> move storage->pickup bandage->interact patient"

        );

    }

    private static void Part2Narrative() {
        System.out.println("\n====================== Task 2 ======================" +
                "\n ======================================" +
                "\n example : move info-> take form->move er->talk patient2-> move office->talk doctor->take prescription-> move pharm-> take medicine-> talk nurse");

        System.out.println(" Now, you enter the Task 2");
        System.out.println(" A nurse approaches you with a sense of urgency. ");
        System.out.println(" the patient in dire need of medication.");
        System.out.println(" she says, her voice laced with concern.");
        System.out.println(" 'I need you to handle this with utmost priority.'");
        System.out.println(" 'Your first task is to construct a register form from reception desk for the patient.'");
        System.out.println(" 'The information you gather here is crucial for accurate treatment'");
        System.out.println(" 'With the form in hand, your next stop is the doctor's office.'");
        System.out.println(" 'The doctor, after examining the form, writes a detailed prescription.'");
        System.out.println(" 'With the prescription now in your possession, you make your way to the pharmacy to take the medicine.'");
        System.out.println(" 'Your final task is to deliver the medication to the me.'");
                /*
                 She awaits you, the anticipation clear in her eyes.
                 Handing over the medication, you feel a sense of accomplishment.
                 You've played a crucial role in the patient's care.
                 The nurse nods in gratitude, ready to administer the medication.
                 \\\"Thank you,\\\" she says, \\\"You've been a great help.\\\"\");")
                 */
    }

    private static void Part3Narrative() {
        System.out.println("\n====================== Task 3 ======================");
        System.out.println(" Now, you enter the Task 3");
        System.out.println(" In this chapter, you face a gripping medical challenge adapted from a real-life documentary story. " +
                "\n A soon-to-be mother, diagnosed with a congenital heart defect known as Bicuspid Aortic Valve, requires cardiac surgery. " +
                "\n At 27 weeks pregnant with twins, the stakes are incredibly high. " +
                "\n The surgery poses a grave risk of cardiac arrest, threatening the lives of both the mother and her unborn twins " +
                "\n – with a 30% chance of fatality for the babies.\n" +
                "\n ");
        System.out.println(" As the surgery approaches, the twins are soon to be born, adding urgency to an already delicate situation. " +
                "\n Your task is to ensure the smooth progression of the surgery, including the crucial preparatory stages. " +
                "\n This involves managing medication, " +
                "\n collect register form " +
                "\n conducting blood tests and lab work " +
                "\n interpreting ultrasound results, " +
                "\n and preparing for the surgical procedure itself.\n");
    }

    private static void getUserChoiceForStoryline() {
        System.out.println("\n - You should check game instruction 'help'");
        System.out.println("\n - Quit 'quit'");
        System.out.println("\n - Map 'map'");
    }

    private static void resetGameState() {
        Player.player.reset(); // Reset the player
        resetAllRoomsInventory(); // Reset inventory for all rooms
        StoryTeller.reset();
    }

    private static void resetAllRoomsInventory() {
        for (Rooms room : Rooms.values()) {
            Items[] initialItems = getInitialItemsForRoom(Rooms.valueOf(room.name()));
            room.setInventory(new Inventory(initialItems));
        }
    }

}
