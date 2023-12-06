package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.ActionCodes;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;
import java.util.*;

public class UserInput {
    public static void ParseCommand(List<String> wordlist, Player player, Map<String, ActionCodes> actionMap, Map<String, Items> itemMap, Map<String, Rooms> roomMap) {
        if (wordlist.size() != 2) {
            System.out.println("Commands should consist of two words: an action and a target.");
            return;
        }

        String verb = wordlist.get(0);
        String noun = wordlist.get(1);

        ActionCodes action = actionMap.get(verb);
        Items item = itemMap.get(noun);
        Rooms room = roomMap.get(noun);

        if (action == null) {
            System.out.println("Unrecognized action: " + verb);
        } else if (item == null && room == null) {
            System.out.println("Unrecognized target: " + noun);
        } else {
            // Implement the logic for the action
            switch (action) {
                case ACTION_INVENTORY_CHECK:
                    player.getInventory();
                    break;
                case ACTION_HELP:
                    for (ActionCodes ac : ActionCodes.values()) {
                        System.out.println(ac.getName() + ": " + ac.getDescription());
                    }
                    break;
                case ACTION_MOVE:
                    if (room != null) {
                        player.setCurrentRoom(room);
                        System.out.println("You move to " + room.getName());
                    } else {
                        System.out.println("You can't move there.");
                    }
                    break;
                case ACTION_EXITS:
                    player.getCurrentRoom().getExits();
                    break;
                case ACTION_INTERACT:
                    if (item != null) {
                        item.interaction(action, player, item, player.getInventory());
                    } else {
                        System.out.println("There is no such item to interact with.");
                    }
                    break;
                default:
                    System.out.println("This action is not implemented yet.");
                    break;
            }
        }
    }

    public static List<String> WordList(String input) {
        String delims = " \t,.:;?!\"'";
        List<String> strlist = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delims);
        String t;

        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            strlist.add(t);
        }
        return strlist;
    }

    public static String RunCommand(String inputstr, Player player, Map<String, ActionCodes> actionMap, Map<String, Items> itemMap, Map<String, Rooms> roomMap) {
        List<String> wordlist;
        String response = "ok";
        String lowstr = inputstr.trim().toLowerCase();

        if (!lowstr.equals("q")) {
            if (lowstr.equals("")) {
                response = "You must enter a command";
            } else {
                wordlist = WordList(lowstr);
                ParseCommand(wordlist, player, actionMap, itemMap, roomMap);
            }
        }
        return response;
    }
}
