package JavaExamProject_Group15;

import JavaExamProject_Group15.Entity.ActionCodes;
import JavaExamProject_Group15.Entity.Items;
import JavaExamProject_Group15.Entity.Rooms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GameLuncher {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input, output;
        Player player = new Player(); // 初始化 player
        // initiate map
        Map<String, ActionCodes> actionMap = initializeActionMap();
        Map<String, Items> itemMap = new HashMap<>();
        Map<String, Rooms> roomMap = new HashMap<>();
        // fill the map

        do {
            System.out.print("> ");
            input = in.readLine();
            output = UserInput.RunCommand(input, player, actionMap, itemMap, roomMap);
            System.out.println(output);
        } while (!"q".equals(input));
    }
    private static Map<String, ActionCodes> initializeActionMap() {
        Map<String, ActionCodes> actionMap = new HashMap<>();
        for (ActionCodes action : ActionCodes.values()) {
            // 将每个动作的别名加入映射
            for (String alias : action.getCommandArray()) {
                actionMap.put(alias, action);
            }
        }
        return actionMap;
    }
}
