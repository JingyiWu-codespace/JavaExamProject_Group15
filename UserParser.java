package WPO_final;

import WPO_final.Entity.ActionCodes;
import WPO_final.Entity.Items;
import WPO_final.Entity.Rooms;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserParser {
    private final Scanner scanner;
    private final Map<String, Items> word_to_item;
    private final Map<String, Rooms> word_to_room;
    private final Map<String, ActionCodes> word_to_action;

    public UserParser() {
        scanner = new Scanner(System.in);

        // Initialize the maps
        this.word_to_item = new HashMap<String, Items>();
        for (Items item : Items.values())
            for (String word : item.get_command_array())
                if (!this.word_to_item.containsKey(word))
                    this.word_to_item.put(word, item);
                else System.out.println("Duplicate key: " + word);

        this.word_to_room = new HashMap<String, Rooms>();
        for (Rooms room : Rooms.values())
            for (String word : room.get_command_array())
                if (!this.word_to_room.containsKey(word))
                    this.word_to_room.put(word, room);
                else System.out.println("Duplicate key: " + word);

        this.word_to_action = new HashMap<String, ActionCodes>();
        for (ActionCodes action : ActionCodes.values())
            for (String word : action.get_command_array())
                if (!this.word_to_action.containsKey(word))
                    this.word_to_action.put(word, action);
                else System.out.println("Duplicate key: " + word);

        // Check for duplicate keys
        for (String key : this.word_to_action.keySet()) {
            if (this.word_to_item.containsKey(key))
                System.out.println("Duplicate key: " + key);
            if (this.word_to_room.containsKey(key))
                System.out.println("Duplicate key: " + key);
        }
        for (String key : this.word_to_item.keySet()) {
            if (this.word_to_room.containsKey(key))
                System.out.println("Duplicate key: " + key);
        }
    }

    public ParsedData parseInput() {
        System.out.print("> "); // Prompt the user
        String inputLine = scanner.nextLine().toLowerCase();
        String[] inputParts = inputLine.trim().split("\\s+"); // Split input by spaces
        if (inputParts.length == 0) {
            System.out.println("No input given. Please try again."); // Feedback to the user
            return null;
        }

        ActionCodes actionCode = null;
        Items objectCode = null;
        Rooms roomCode = null;
        Items otherObjectCode = null;

        // Parse the input
        for (String word : inputParts)
            if (actionCode == null)
                actionCode = this.word_to_action.get(word);
            else if (objectCode == null)
                objectCode = this.word_to_item.get(word);
            else if (roomCode == null)
                roomCode = this.word_to_room.get(word);
            else if (otherObjectCode == null)
                otherObjectCode = this.word_to_item.get(word);

        System.out.println("debug\n"+
                "actionCode: " + actionCode.get_name()+"\n"+
                "objectCode: " + objectCode.get_name()+"\n"+
                "roomCode: " + roomCode.get_name()+"\n"+
                "otherObjectCode: " + otherObjectCode.get_name()+"\n");

        return new ParsedData(actionCode, objectCode, roomCode, otherObjectCode);
    }

    public static class ParsedData {
        public final ActionCodes actionCode;
        public final Items objectCode;
        public final Rooms roomCode;
        public final Items otherObjectCode;

        public ParsedData(ActionCodes actionCode, Items objectCode, Rooms roomCode, Items otherObjectCode) {
            this.actionCode = actionCode;
            this.objectCode = objectCode;
            this.roomCode = roomCode;
            this.otherObjectCode = otherObjectCode;
        }
    }
}