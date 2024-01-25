package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.ActionBase;
import JavaExamProject_Group15.Entity.ItemBase;
import JavaExamProject_Group15.Entity.Rooms;

import java.util.*;

/**
 * The UserParser class is responsible for interpreting user input during the game.
 * It converts user input strings into game actions, items, and rooms, facilitating
 */
public class UserParser {
    private final Map<String, ItemBase> itemWordMap;
    private final Map<String, Rooms> roomWordMap;
    private final Map<String, ActionBase> actionWordMap;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a UserParser and initializes maps to associate string representations
     * with game entities like actions, items, and rooms. It populates these maps using
     * the aliases provided in the enums. The constructor also checks for duplicate keys
     * across these maps and reports them if the debug flag is set in the Player class.
     */
    private UserParser() {
        // Initialize the maps   ******************************
        this.itemWordMap = new HashMap<String, ItemBase>();
        for (ItemBase item : ItemBase.values())
            for (String word : item.getAliases())
                if (!this.itemWordMap.containsKey(word))
                    this.itemWordMap.put(word, item);
                else if (Player.debug_flag)
                    System.out.println("Duplicate item key: " + word);

        this.roomWordMap = new HashMap<String, Rooms>();
        for (Rooms room : Rooms.values())
            for (String word : room.getAliases())
                if (!this.roomWordMap.containsKey(word))
                    this.roomWordMap.put(word, room);
                else if (Player.debug_flag)
                    System.out.println("Duplicate room key: " + word);

        this.actionWordMap = new HashMap<String, ActionBase>();
        for (ActionBase action : ActionBase.values())
            for (String word : action.getAliases())
                if (!this.actionWordMap.containsKey(word))
                    this.actionWordMap.put(word, action);
                else if (Player.debug_flag)
                    System.out.println("Duplicate action key: " + word);
        // Finished Initializing the maps   **********************

        // Check for duplicate keys    *******************
        for (String key : this.actionWordMap.keySet()) {
            if (this.itemWordMap.containsKey(key) && Player.debug_flag)
                System.out.println("Duplicate action key: " + key);
            if (this.roomWordMap.containsKey(key) && Player.debug_flag)
                System.out.println("Duplicate action key: " + key);
        }
        for (String key : this.itemWordMap.keySet())
            if (this.roomWordMap.containsKey(key) && Player.debug_flag)
                System.out.println("Duplicate item key: " + key);
        // Finished Check for duplicate keys    *************
    }

    /**
     * Waits for and processes user input, converting it into actionable game data.
     *
     * @return A ParsedDataHolder object containing the parsed action, item, and room data.
     */
    private ParsedDataHolder getUserInput() {
        ParsedDataHolder parsedContent = null;
        while (parsedContent == null) {
            System.out.print("\n\nEnter a command (verb or verb + entity): \n");
            String input = this.scanner.nextLine().toLowerCase();
            List<String> wordlist = new ArrayList<>(Arrays.asList(input.split(" ")));

            if (wordlist.size() == 0) {
                System.out.print("nothing was received\n");
                continue;
            }
            parsedContent = this.parseUserString(wordlist);
        }
        return parsedContent;
    }

    /**
     * Parses a list of words from user input to identify game actions, items, and rooms.
     *
     * @param wordlist The list of words to parse.
     * @return A ParsedDataHolder object if parsing is successful, null otherwise.
     */
    private ParsedDataHolder parseUserString(List<String> wordlist) {
        ActionBase action = null;
        ItemBase item = null;
        Rooms room = null;

        int start = 0;
        int seq_end = wordlist.size();
        List<String> reco_sequences = new ArrayList<String>();
        while (start != seq_end && reco_sequences.size() != 2) {
            String word = String.join(" ", wordlist.subList(start, seq_end));

            if (action == null && this.actionWordMap.containsKey(word))
                action = this.actionWordMap.get(word);
            else if (room == null && this.roomWordMap.containsKey(word))
                room = this.roomWordMap.get(word);
            else if (item == null && this.itemWordMap.containsKey(word))
                item = this.itemWordMap.get(word);

                //special help case
            else if (action == ActionBase.ACTION_HELP && this.actionWordMap.containsKey(word)) {
                ActionBase.ACTION_HELP.executeAction(this.actionWordMap.get(word));
                return this.getUserInput();
            } else if (seq_end - 1 == start) {
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

        if (Player.debug_flag)
            System.out.println(
                    "debug > actionCode: " + (action != null ? action.getName() : "null") + "\n" +
                            "debug > itemCode: " + (item != null ? item.getName() : "null") + "\n" +
                            "debug > roomCode: " + (room != null ? room.getName() : "null") + "\n"
            );

        if (action == null)
            System.out.println("Unrecognized action");
        if (item == null || room == null)
            System.out.println("Unrecognized entity or room");
        return null;
    }

    /**
     * Prompts the user to decide whether to continue the game.
     *
     * @return true if the user wants to continue, false otherwise.
     */
    private boolean askToContinue() {
        System.out.println("Would you like to continue? (y/n)");
        String input = this.scanner.nextLine().toLowerCase();
        if (!input.equals("y") && !input.equals("n") && !input.equals("yes") && !input.equals("no")) {
            System.out.println("Please enter yes or no (y/n)");
            return this.askToContinue();
        }
        return input.equals("y") || input.equals("yes");
    }

    /**
     * Asks the user which chapter they would like to start from.
     *
     * @return An integer representing the chosen starting chapter.
     */
    private int askWhatChapter() {
        // prompt user for chapter
        System.out.println("What chapter would you like to start at?");
        System.out.println("  0. tutorial: I hate my job\n  1. Chapter 1: getting some drugs\n  2. Chapter 2: Baby time");

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

    private static class ParsedDataHolder {
        private final ActionBase actionCode;
        private final ItemBase itemCode;
        private final Rooms roomCode;

        private ParsedDataHolder(ActionBase actionCode, ItemBase itemCode, Rooms roomCode) {
            this.actionCode = actionCode;
            this.itemCode = itemCode;
            this.roomCode = roomCode;
        }
    }
}