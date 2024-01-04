package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;

import java.io.IOException;
/**
 * The GameManager class is the main class for running the game.
 * It initializes the game environment, handles the game loop, processes user input,
 * and manages the progression of the game chapters and story.
 */
public class GameManager {
    public static void main(String[] args) throws IOException {
        /*
        active userIO
         */
        final UserParser userIo = new UserParser();
        /*
        Player can choose which chapter he/she want to play
         */
        final StoryTeller storyTeller = new StoryTeller(userIo.askWhatChapter());

        System.out.println("++++++++++++++++++++++++++ Welcome to the game! ++++++++++++++++++++++++++");
        System.out.println("BASIC COMMANDS: help, pickup, move, talk, use, drop, inventory, quit");
        System.out.println("\n type 'help' to get more information about accessible commands");
        System.out.println("type 'help' followed by the thing you want to know more about \n");
        //initialize game
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
            //recognize player input
            UserParser.ParsedDataHolder parsed_codes = userIo.getUserInput();
            // Check for quit action
            if (parsed_codes.actionCode == Actions.ACTION_QUIT) break;
            // Execute action based on parsed input
            parsed_codes.actionCode.executeAction(parsed_codes.itemCode, parsed_codes.roomCode);

        }
    }
}


