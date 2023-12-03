package WPO_final.Entity;

public enum ActionCodes {
    ACTION_INVENTORY_CHECK("inventory", "Check your inventory", new String[]{"inv", "bag"}),
    ACTION_HELP("help", "Show available actions and their descriptions", new String[]{"h", "commands"}),
    ACTION_MOVE("move", "Move to a different room", new String[]{"go", "walk", "run", "sprint"}),
    ACTION_EXITS("exits", "Show possible rooms to go to", new String[]{"exit", "map"}),
    ACTION_INTERACT("interact", "Interact with an item", new String[]{"use", "grab", "take", "pick up", "open"});

    private  ActionCodeInfo action_info;
    private final String[] aliases;

    ActionCodes(String name, String description, String[] aliases) {
        this.aliases = aliases;
        this.action_info = new ActionCodeInfo(name, description, aliases);
    }

    public boolean checkCommand(String input) {
        for (String alias : aliases) {
            if (alias.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public ActionCodeInfo getAction_info() {
        return action_info;
    }

    private class ActionCodeInfo extends BaseEntity {
        ActionCodeInfo(String name, String description, String[] aliases) {
            super(name, description, aliases);
        }

        public String getName() {
            return super.getName(); // 调用 BaseEntity 的 get_name 方法
        }

        public String getDescription() {
            return super.getDescription(); // 调用 BaseEntity 的 get_description 方法
        }
    }
    public String getName() {
        return this.action_info.getName();
    }

    public String getDescription() {
        return this.action_info.getDescription();
    }
    private ActionCodes parseActionCode(String action) {
        for (ActionCodes code : ActionCodes.values()) {
            System.out.println("Checking command: " + action + " against " + code.name());
            if (code.checkCommand(action)) {
                System.out.println("Matched: " + code.name());
                return code;
            }
        }
        System.out.println("No matching action code found.");
        return null; // No action found
    }

}
