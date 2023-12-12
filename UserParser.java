package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import java.util.*;

public class UserParser {
    private final Map<String, Items> itemWordMap;
    private final Map<String, Rooms> roomWordMap;
    private final Map<String, Actions> actionWordMap;
    private final Scanner scanner = new Scanner(System.in);

    public ParsedData getUserInput() {
        while (true) {
            System.out.print("\n\nEnter a command (verb entity): \n");
            String input = this.scanner.nextLine();
            List<String> wordlist = Arrays.asList(input.split(" "));
            if (wordlist.size() > 2) {
                System.out.println("Commands should consist of one or two words: an action and a target.\n");
                continue;
            }
            if (wordlist.size() == 0) {
                System.out.print("nothing was received\n");
                continue;
            }
            return this.parseUserString(wordlist);
        }
    }

    public ParsedData parseUserString(List<String> wordlist) {
        String verb = wordlist.get(0);
        String noun = null;
        if(wordlist.size() == 2) noun = wordlist.get(1);

        Actions action = this.actionWordMap.get(verb);
        Items item = this.itemWordMap.get(noun);
        Rooms room = this.roomWordMap.get(noun);

        if (action != null && ((item != null || room != null) || wordlist.size()==1))
            return new ParsedData(action, item, room);

        System.out.println(
              "debug > actionCode: " + (action != null ? action.getName() : "null") + "\n" +
              "debug > itemCode: " +   (item != null ? item.getName()     : "null") + "\n" +
              "debug > roomCode: " +   (room != null ? room.getName()     : "null") + "\n"
        );

        if (action == null)
            System.out.println("Unrecognized action: " + action);
        if (item == null && room == null)
            System.out.println("Unrecognized target: " + noun);
        return null;

    }

    public static class ParsedData {
        public final Actions actionCode;
        public final Items itemCode;
        public final Rooms roomCode;

        public ParsedData(Actions actionCode, Items itemCode, Rooms roomCode) {
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

        this.actionWordMap = new HashMap<String, Actions>();
        for (Actions action : Actions.values())
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