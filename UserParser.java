package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.ActionCodes;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import java.util.*;

public class UserParser {
    private final Map<String, Items> itemWordMap;
    private final Map<String, Rooms> roomWordMap;
    private final Map<String, ActionCodes> actionWordMap;
    private final Scanner scanner = new Scanner(System.in);

    public ParsedData getUserInput() {
        String input = this.scanner.nextLine();
        List<String> wordlist = Arrays.asList(input.split(" "));
        return parseUserString(wordlist);
    }

    public ParsedData parseUserString(List<String> wordlist) {
        if (wordlist.size() != 2) {
            System.out.println("Commands should consist of two words: an action and a target.");
            return null;
        }

        String verb = wordlist.get(0);
        String noun = wordlist.get(1);

        ActionCodes action = this.actionWordMap.get(verb);
        Items item = this.itemWordMap.get(noun);
        Rooms room = this.roomWordMap.get(noun);

        System.out.println("debug\n" +
                "actionCode: " + action.getName() + "\n" +
                "itemCode: " + item.getName() + "\n" +
                "roomCode: " + room.getName() + "\n");

        if (action != null && (item != null || room != null))
            return new ParsedData(action,item,room);

        if (action==null)
            System.out.println("Unrecognized action: " + action);
        if (item == null && room == null)
            System.out.println("Unrecognized target: " + noun);
        return null;

    }

    public static class ParsedData {
        public final ActionCodes actionCode;
        public final Items itemCode;
        public final Rooms roomCode;

        public ParsedData(ActionCodes actionCode, Items itemCode, Rooms roomCode) {
            this.actionCode = actionCode;
            this.itemCode = itemCode;
            this.roomCode = roomCode;
        }
    }

    public UserParser() {
        // Initialize the maps   ******************************
        this.itemWordMap = new HashMap<String, Items>();
        for (Items item : Items.values())
            for (String word : item.getCommandArray())
                if (!this.itemWordMap.containsKey(word))
                    this.itemWordMap.put(word, item);
                else System.out.println("Duplicate key: " + word);

        this.roomWordMap = new HashMap<String, Rooms>();
        for (Rooms room : Rooms.values())
            for (String word : room.getCommandArray())
                if (!this.roomWordMap.containsKey(word))
                    this.roomWordMap.put(word, room);
                else System.out.println("Duplicate key: " + word);

        this.actionWordMap = new HashMap<String, ActionCodes>();
        for (ActionCodes action : ActionCodes.values())
            for (String word : action.getCommandArray())
                if (!this.actionWordMap.containsKey(word))
                    this.actionWordMap.put(word, action);
                else System.out.println("Duplicate key: " + word);
        // Finished Initializing the maps   **********************

        // Check for duplicate keys    *******************
        for (String key : this.actionWordMap.keySet()) {
            if (this.itemWordMap.containsKey(key))
                System.out.println("Duplicate key: " + key);
            if (this.roomWordMap.containsKey(key))
                System.out.println("Duplicate key: " + key);
        }
        for (String key : this.itemWordMap.keySet())
            if (this.roomWordMap.containsKey(key))
                System.out.println("Duplicate key: " + key);
        // Finished Check for duplicate keys    *************
    }
}