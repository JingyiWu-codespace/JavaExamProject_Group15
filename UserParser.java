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

    public ParsedDataHolder getUserInput() {
        while (true) {
            System.out.print("\n\nEnter a command (verb or verb + entity): \n");
            String input = this.scanner.nextLine().toLowerCase();
            List<String> wordlist = new ArrayList<>(Arrays.asList(input.split(" ")));

            if (wordlist.size() == 0) {
                System.out.print("nothing was received\n");
                continue;
            }

            return this.parseUserString(wordlist);
        }
    }

    public ParsedDataHolder parseUserString(List<String> wordlist) {
        Actions action = null;
        Items item = null;
        Rooms room = null;

        int start = 0;
        int seq_end = wordlist.size();
        List<String> reco_sequences = new ArrayList<String>();
        while (start!=seq_end && reco_sequences.size()!=2) {
            String word = String.join(" ", wordlist.subList(start, seq_end));

            if (action == null && this.actionWordMap.containsKey(word))
                action = this.actionWordMap.get(word);
            else if (room == null && this.roomWordMap.containsKey(word))
                room = this.roomWordMap.get(word);
            else if (item == null && this.itemWordMap.containsKey(word))
                item = this.itemWordMap.get(word);

            //special help case
            else if (action == Actions.ACTION_HELP && this.actionWordMap.containsKey(word)) {
                Actions.ACTION_HELP.executeAction(this.actionWordMap.get(word));
                return this.getUserInput();
            }

            else if (seq_end-1 == start){
                seq_end = wordlist.size();
                start++;
                continue;
            } else {
                seq_end--;
                continue;
            }

            reco_sequences.add(word);
            start = seq_end;
            seq_end = wordlist.size();
        }

        if (action != null
             && (item != null || room != null
             || reco_sequences.get(0).equals(String.join(" ", wordlist))))
            return new ParsedDataHolder(action, item, room);

        System.out.println(
                "debug > actionCode: " + (action != null ? action.getName() : "null") + "\n" +
                "debug > itemCode: " +   (item != null ? item.getName()     : "null") + "\n" +
                "debug > roomCode: " +   (room != null ? room.getName()     : "null") + "\n"
        );

        if (action == null)
            System.out.println("Unrecognized action: " + action);
        if (item == null)
            System.out.println("Unrecognized target: " + item);
        if (room == null)
            System.out.println("Unrecognized target: " + room);
        return null;
    }

    public boolean askToContinue() {
        System.out.println("Would you like to continue? (y/n)");
        String input = this.scanner.nextLine().toLowerCase();
        if (!input.equals("y") && !input.equals("n") && !input.equals("yes") && !input.equals("no")) {
            System.out.println("Please enter yes or no (y/n)");
            return this.askToContinue();
        }
        return input.equals("y") || input.equals("yes");
    }

    public int askWhatChapter() {
        // prompt user for chapter
        System.out.println("What chapter would you like to start at?");
        System.out.println("  0. tutorial\n  1. Chapter 1\n  2. Chapter 2");

        // get user input
        int chapter = -1;
        while (chapter < 0 || chapter > 2)
            try {
                chapter = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer between 0 and 2");
            }
        return chapter;
    }

    public static class ParsedDataHolder {
        public final Actions actionCode;
        public final Items itemCode;
        public final Rooms roomCode;

        public ParsedDataHolder(Actions actionCode, Items itemCode, Rooms roomCode) {
            this.actionCode = actionCode;
            this.itemCode = itemCode;
            this.roomCode = roomCode;
        }
    }

    public UserParser() {
        // Initialize the maps   ******************************
        this.itemWordMap = new HashMap<String, Items>();
        for (Items item : Items.values())
            for (String word : item.getAliases())
                if (!this.itemWordMap.containsKey(word))
                    this.itemWordMap.put(word, item);
                else System.out.println("Duplicate item key: " + word);

        this.roomWordMap = new HashMap<String, Rooms>();
        for (Rooms room : Rooms.values())
            for (String word : room.getAliases())
                if (!this.roomWordMap.containsKey(word))
                    this.roomWordMap.put(word, room);
                else System.out.println("Duplicate room key: " + word);

        this.actionWordMap = new HashMap<String, Actions>();
        for (Actions action : Actions.values())
            for (String word : action.getAliases())
                if (!this.actionWordMap.containsKey(word))
                    this.actionWordMap.put(word, action);
                else System.out.println("Duplicate action key: " + word);
        // Finished Initializing the maps   **********************

        // Check for duplicate keys    *******************
        for (String key : this.actionWordMap.keySet()) {
            if (this.itemWordMap.containsKey(key))
                System.out.println("Duplicate action key: " + key);
            if (this.roomWordMap.containsKey(key))
                System.out.println("Duplicate action key: " + key);
        }
        for (String key : this.itemWordMap.keySet())
            if (this.roomWordMap.containsKey(key))
                System.out.println("Duplicate item key: " + key);
        // Finished Check for duplicate keys    *************
    }
}