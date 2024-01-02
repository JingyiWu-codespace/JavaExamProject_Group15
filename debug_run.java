package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;

import java.io.IOException;

public class debug_run {

    public static void main(String[] args) throws IOException {
        final UserParser userIo = new UserParser();
        final StoryTeller storyTeller = new StoryTeller();
        int timeCounter = -1;

        while (true) {
            timeCounter++;

            // check victory
            if (storyTeller.checkVictory()) {
                System.out.println("A good days work. Now go sleep.");
                break;
            }

            // check timeout
            if (timeCounter > storyTeller.gameTimeout) {
                System.out.println("You are late and people died. You failed.");
                break;
            }

            // get and run action
            UserParser.ParsedDataHolder parsed_codes=null;
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
}