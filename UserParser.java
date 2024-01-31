package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Actions.Primary.ACTION_HELP;
import JavaExamProject_Group15.Entity.BasicDataHolder;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import java.util.*;

/**
 * The UserParser class is responsible for interpreting user input during the game.
 * It converts user input strings into game actions, items, and rooms, facilitating
 */
public class UserParser {
    public final static UserParser userInterface = new UserParser();

    private final Map<String, ItemBase> itemWordMap = new HashMap<>();
    private final Map<String, RoomBase> roomWordMap = new HashMap<>();
    private final Map<String, ActionBase> actionWordMap = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Waits for and processes user input, converting it into actionable game data.
     *
     * @return A ParsedDataHolder object containing the parsed action, item, and room data.
     */
    ParsedDataHolder getUserInput() {
        ParsedDataHolder parsedContent = null;
        while (parsedContent == null) {
            System.out.print("\n\nEnter a command (verb or verb + entity): \n");
            String input = this.scanner.nextLine().toLowerCase();
            List<String> wordlist = new ArrayList<>(Arrays.asList(input.split(" ")));

            if (wordlist.isEmpty()) {
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
        RoomBase room = null;

        int start = 0;
        int seq_end = wordlist.size();
        List<String> reco_sequences = new ArrayList<String>();
        while (start != seq_end && reco_sequences.size() != 2) {
            String word = String.join(" ", wordlist.subList(start, seq_end)).toLowerCase();

            if (action == null && this.actionWordMap.containsKey(word))
                action = this.actionWordMap.get(word);
            else if (room == null && this.roomWordMap.containsKey(word))
                room = this.roomWordMap.get(word);
            else if (item == null && this.itemWordMap.containsKey(word))
                item = this.itemWordMap.get(word);

                //special help case
            else if (action instanceof ACTION_HELP actionHelp && this.actionWordMap.containsKey(word)) {
                ActionBase otherAction = this.actionWordMap.get(word);
                actionHelp.executeAction(otherAction);
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

    private <T extends BasicDataHolder> void addCommand(Map<String, T> wordMap, T thing) {
        String name = thing.getName();
        String[] temp = thing.getAliases();
        String[] aliases = new String[temp.length + 1];
        System.arraycopy(temp, 0, aliases, 0, temp.length);
        aliases[aliases.length - 1] = name;
        for(int i = 0; i < aliases.length; i++)
            aliases[i] = aliases[i].toLowerCase();

        for (String alias : aliases) {
            if (wordMap.containsKey(alias) && !(wordMap.get(alias) instanceof T)) {
                if (Player.debug_flag)
                    System.out.println("debug > duplicate key: " + alias);
                continue;
            }
            wordMap.put(alias, thing);
        }
    }

    public <T extends BasicDataHolder> void addCommand(T thing) {
        if (thing instanceof ActionBase)
            addCommand(this.actionWordMap, (ActionBase) thing);
        else if (thing instanceof ItemBase)
            addCommand(this.itemWordMap, (ItemBase) thing);
        else if (thing instanceof RoomBase)
            addCommand(this.roomWordMap, (RoomBase) thing);
        else
            throw new IllegalArgumentException("thing is not an instance of ActionBase, ItemBase, or RoomBase");
    }

    private <T extends BasicDataHolder> void removeCommand(Map<String, T> wordMap, T thing) {
        String name = thing.getName();
        String[] temp = thing.getAliases();
        String[] aliases = new String[temp.length + 1];
        System.arraycopy(temp, 0, aliases, 0, temp.length);
        aliases[aliases.length - 1] = name;
        for(int i = 0; i < aliases.length; i++)
            aliases[i] = aliases[i].toLowerCase();

        for (String alias : aliases)
            wordMap.remove(alias);
    }

    public <T extends BasicDataHolder> void removeCommand(T thing) {
        if (thing instanceof ActionBase)
            removeCommand(this.actionWordMap, (ActionBase) thing);
        else if (thing instanceof ItemBase)
            removeCommand(this.itemWordMap, (ItemBase) thing);
        else if (thing instanceof RoomBase)
            removeCommand(this.roomWordMap, (RoomBase) thing);
    }

    public static class ParsedDataHolder {
        final ActionBase actionCode;
        final ItemBase itemCode;
        final RoomBase roomCode;

        private ParsedDataHolder(ActionBase actionCode, ItemBase itemCode, RoomBase roomCode) {
            this.actionCode = actionCode;
            this.itemCode = itemCode;
            this.roomCode = roomCode;
        }
    }
}