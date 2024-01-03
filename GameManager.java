package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;

import java.io.IOException;

import static JavaExamProject_Group15.Entity.Rooms.*;

public class GameManager {
    public static void main(String[] args) throws IOException {
        final UserParser userIo = new UserParser();
        final StoryTeller storyTeller = new StoryTeller(userIo.askWhatChapter());

        System.out.println("++++++++++++++++++++++++++ Welcome to the game! ++++++++++++++++++++++++++");
        System.out.println("BASIC COMMANDS: help, pickup, move, talk, use, drop, inventory, quit");
        System.out.println("\n type 'help' to get more information about accessible commands");
        System.out.println("type 'help' followed by the thing you want to know more about \n");

        storyTeller.beginChapter();

        while (true) {
            // check for chapter completion
            if (storyTeller.chapterComplete()) {
                storyTeller.nextChapter();

                // check for game completion
                if (storyTeller.checkVictory()) {
                    System.out.println("A good days work. Tomorrow is gonna be worse.");
                    System.out.println("--- You win! ---");
                    break;
                }

                // ask to continue
                if (!userIo.askToContinue()) break;

                // print narrative
                storyTeller.beginChapter();
            }

            UserParser.ParsedDataHolder parsed_codes = userIo.getUserInput();

            if (parsed_codes.actionCode == Actions.ACTION_QUIT) break;

            parsed_codes.actionCode.executeAction(parsed_codes.itemCode, parsed_codes.roomCode);

        }
    }
}
