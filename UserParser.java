package WPO_final;

import WPO_final.Entity.ActionCodes;
import WPO_final.Entity.Items;
import WPO_final.Entity.Rooms;

import java.util.Scanner;

public class UserParser {
    private Scanner scanner;

    public UserParser() {
        scanner = new Scanner(System.in);
    }
    public static class ParsedInput {
        public final ActionCodes actionCode;
        public final Items objectCode;
        public final Rooms roomCode;
        public final Items otherObjectCode;

        public ParsedInput(ActionCodes actionCode, Items objectCode, Rooms roomCode, Items otherObjectCode) {
            this.actionCode = actionCode;
            this.objectCode = objectCode;
            this.roomCode = roomCode;
            this.otherObjectCode = otherObjectCode;
        }
    }

    public ParsedInput parseInput() {
        System.out.print("> "); // Prompt the user
        String inputLine = scanner.nextLine().toLowerCase();
        String[] inputParts = inputLine.trim().split("\\s+"); // Split input by spaces
        if (inputParts.length == 0) {
            System.out.println("No input given. Please try again."); // Feedback to the user
            return null; // No input
        }

        if (inputParts.length == 0) {
            return null;
        }

        // Parse the action code
        // Parse the action code
        ActionCodes actionCode = parseActionCode(inputParts[0]);
        if (actionCode == null) {
            System.out.println("Invalid command. Try again or type 'help' for a list of commands.");
            return null; // No action found, return early
        }

        Items objectCode = null; // Initialize to null, add parsing logic if necessary
        Items otherObjectCode = null; // Initialize to null, add parsing logic if necessary
        Rooms roomCode = null; // Initialize to null, add parsing logic if necessary

        // TODO: Add parsing logic for objectCode, roomCode, and otherObjectCode if needed

        return new ParsedInput(actionCode, objectCode, roomCode, otherObjectCode);
    }

    private ActionCodes parseActionCode(String action) {
        for (ActionCodes code : ActionCodes.values()) {
            if (code.checkCommand(action)) {
                return code;
            }
        }
        System.out.println("Invalid command. Try again or type 'help' for a list of commands.");
        return null; // No action found
    }

    public String[][] getActionStrings() {
        String[][] actionStrings = new String[ActionCodes.values().length][2];
        for (int i = 0; i < ActionCodes.values().length; i++) {
            ActionCodes code = ActionCodes.values()[i];
            actionStrings[i][0] = code.name(); // Get the name of the enum constant itself
            actionStrings[i][1] = code.getDescription(); // Get the description from the ActionCodeInfo
        }
        return actionStrings;
    }
}