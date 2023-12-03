package WPO_final;

import WPO_final.Entity.Rooms;
import WPO_final.Player;
import WPO_final.StoryTeller;
import WPO_final.UserParser;

public class Main {
    public static void main(String[] args) {
        // 创建游戏中的主要对象
        Player player = new Player();
        UserParser userParser = new UserParser();
        StoryTeller storyteller = new StoryTeller(player, userParser);

        // 打印欢迎信息和帮助信息
        System.out.println("Welcome to the Text Adventure Game!");
        printHelp(userParser);

        // 游戏循环
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("> "); // 提示用户输入
            UserParser.ParsedInput parsedInput = userParser.parseInput();

            if (parsedInput == null) {
                System.out.println("No valid input detected. Please try again.");
                continue;
            }

            if (parsedInput.actionCode == null) {
                System.out.println("Invalid command. Try again or type 'help' for a list of commands.");
                continue;
            }

            // 根据解析的输入执行动作
            switch (parsedInput.actionCode) {
                case ACTION_INVENTORY_CHECK:
                    System.out.println("Your inventory: " + player.getInventory());
                    break;
                case ACTION_MOVE:
                    // 需要在 parseInput 方法中正确设置 roomCode
                    if (parsedInput.roomCode != null) {
                        player.setCurrentRoom(parsedInput.roomCode);
                        System.out.println("Moved to: " + parsedInput.roomCode.name());
                    } else {
                        System.out.println("No room specified or room does not exist.");
                    }
                    break;
                case ACTION_HELP:
                    printHelp(userParser);
                    break;
                case ACTION_EXITS:
                    Rooms currentRoom = player.getCurrentRoom();
                    if (currentRoom != null) {
                        System.out.println("Exits from " + currentRoom.name() + ":");
                        for (Rooms exit : currentRoom.get_exits()) {
                            System.out.println(exit.name());
                        }
                    }
                    break;
                case ACTION_INTERACT:
                    // 这里需要更详细的逻辑来处理不同的物品交互
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
            isRunning = false;
        }

        System.out.println("Thank you for playing. Goodbye!");
    }

    private static void printHelp(UserParser userParser) {
        String[][] actions = userParser.getActionStrings();
        System.out.println("Here are some commands you can use:");
        for (String[] action : actions) {
            System.out.println(action[0] + " - " + action[1]);
        }
    }
}
