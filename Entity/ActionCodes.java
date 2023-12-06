package JavaExamProject_Group15.Entity;

public enum ActionCodes {
    ACTION_INVENTORY_CHECK("inventory", "Check your inventory", new String[]{"inv", "bag"}),
    ACTION_HELP("help", "Show available actions and their descriptions", new String[]{"h", "commands"}),
    ACTION_MOVE("move", "Move to a different room", new String[]{"go", "walk", "run", "sprint"}),
    ACTION_EXITS("exits", "Show possible rooms to go to", new String[]{"exit", "map"}),
    ACTION_INTERACT("interact", "Interact with an item", new String[]{"use", "grab", "take", "pick up", "open"});

    private  ActionCodeInfo actionInfo;

    ActionCodes(String name, String description, String[] aliases) {
        this.actionInfo = new ActionCodeInfo(name, description, aliases);
    }

    public ActionCodeInfo getActionInfo() {
        return actionInfo;
    }

    public String getDescription() {
        return this.actionInfo.getDescription();
    }

    public String get_name() {
        return this.actionInfo.getName();
    }

    public String get_description() {
        return this.actionInfo.getDescription();
    }

    public Boolean check_command(String alias) {
        return this.actionInfo.checkCommand(alias);
    }

    public String[] getCommandArray() {
        return this.actionInfo.get_command_array();
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
        return this.actionInfo.getName();
    }
}
