package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;

import java.io.IOException;
import java.util.Scanner;

public class GameManager {

    public static void main(String[] args) throws IOException {

        final UserParser userIo = new UserParser();
        final StoryTeller storyTeller = new StoryTeller();
        int timeCounter = -1;
        displayOpeningNarrative();
        getUserChoiceForStoryline();

        while (true) {
            timeCounter++;

            // check victory
            if (storyTeller.check_victory()) {
                System.out.println("A good days work. Now go sleep.");
                break;
            }

            // check timeout
            if (timeCounter > storyTeller.gameTimeout) {
                System.out.println("You are late and people died. You failed.");
                break;
            }

            // get and run action
            UserParser.ParsedData parsed_codes=null;
            while(parsed_codes==null)
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
    private static void displayOpeningNarrative() {
        System.out.println("\n Game intro");
        System.out.println("\n ======================");
        System.out.println("\n You're an intern at a hospital that's seen better days. " +
                "\n The echoes of war rumble in the distance, " +
                "\n a stark backdrop to the daily battles within these walls. " +
                "\n Your job—to assist doctors with medical tools is vital."+
                "\n You steel yourself for the day ahead, " +
                "\n knowing that every second counts in saving lives."+
                "\n ======================================"+
                "\n Task 1: In the first level, " +
                "\n you are tasked with locating bandages in the storage of the Emergency Room" +
                "\n to aid an injured patient. "+
                "\n ======================================"+
                "\n example : move storage->pickup bandage->interact patient"

        );
        ;
    }
    private static void getUserChoiceForStoryline() {
        System.out.println("\n - You should check game instruction 'help'");
        System.out.println("\n - Quit 'q'");
    }

}