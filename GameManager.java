package JavaExamProject_Group15;

import java.io.IOException;

public class GameManager {
    private final UserParser userIo = new UserParser();
    private final StoryTeller storyTeller = new StoryTeller();
    private int gameTimeout = 100;

    private void gameLoop() {
        int timeCounter = -1;
        while (true) {
            timeCounter++;

            // check victory
            if (this.storyTeller.check_victory()) {
                System.out.println("A good days work. Now go sleep.");
                break;
            }

            // check timeout
            if (timeCounter > this.gameTimeout)
                System.out.println("You are late and people died. You failed.");

            // get and run action
            UserParser.ParsedData parsed_codes = this.userIo.getUserInput();
            if (parsed_codes.itemCode == null)
                this.storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.roomCode);
            else
                this.storyTeller.executeAction(parsed_codes.actionCode, parsed_codes.itemCode);
        }
    }

    public static void main(String[] args) throws IOException {
    }
}
