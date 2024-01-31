package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions.Primary.ACTION_HELP;
import JavaExamProject_Group15.Entity.Actions.Primary.ACTION_INTERACT;
import JavaExamProject_Group15.Entity.Actions.Primary.ACTION_QUIT;
import JavaExamProject_Group15.StoryTeller.StoryTeller;

import java.util.Scanner;

import static JavaExamProject_Group15.UserParser.userInterface;

public class GameManager {
    StoryTeller currentStoryTeller;

    protected GameManager(StoryTeller storyTeller) {
        currentStoryTeller = storyTeller;
        startChapter();
        new ACTION_HELP();
        new ACTION_INTERACT();
        new ACTION_QUIT();

        System.out.println("++++++++++++++++++++++++++ BASIC COMMANDS ++++++++++++++++++++++++++");
        System.out.println(" > help, use, quit");
        System.out.println("\n type 'help' to get more information about accessible commands");
        System.out.println("type 'help' followed by the thing you want to know more about \n");
    }

    void mainLoop() {
        // check for chapter completion
        if (currentStoryTeller.checkVictory()) {
            currentStoryTeller = currentStoryTeller.nextChapter();
            if (currentStoryTeller == null) {
                System.out.println("You finished the game! YAAAAY!!!");
                System.out.println("Now go back to your miserable existence and face the fact that you will be a waste of space for the rest of your life.");
                System.exit(0);
            }

            // ask to continue
            if (!askToContinue())
                System.exit(0);

            startChapter();
        }
        if (currentStoryTeller.checkLoss()) {
            System.out.println("You lost the game! You are a failure!");
            System.exit(0);
        }

        //recognize player input
        UserParser.ParsedDataHolder parsed_codes = userInterface.getUserInput();
        // Check for quit action
        if (parsed_codes.actionCode instanceof ACTION_QUIT) System.exit(0);
        // Execute action based on parsed input
        boolean ret = parsed_codes.actionCode.executeAction(parsed_codes.itemCode, parsed_codes.roomCode);

        currentStoryTeller.endTurnPostAction(parsed_codes.actionCode, parsed_codes.itemCode, parsed_codes.roomCode, ret);
    }

    private boolean askToContinue() {
        System.out.println("Would you like to continue? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        if (!input.equals("y") && !input.equals("n") && !input.equals("yes") && !input.equals("no")) {
            System.out.println("Please enter yes or no (y/n)");
            return this.askToContinue();
        }
        return input.equals("y") || input.equals("yes");
    }

    private void startChapter() {
        currentStoryTeller.initiateChapter();
        currentStoryTeller.narrativePrint();
    }
}
