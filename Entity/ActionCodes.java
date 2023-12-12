package JavaExamProject_Group15.Entity;

public enum ActionCodes implements BaseEntityInterface {
    ACTION_INVENTORY_CHECK("inventory", "Check your inventory", new String[]{"inv", "bag"}),
    ACTION_HELP("help", "Show available actions and their descriptions", new String[]{"h", "commands"}),
    ACTION_MOVE("move", "Move to a different room", new String[]{"go", "walk", "run", "sprint"}),
    ACTION_EXITS("exits", "Show possible rooms to go to", new String[]{"exit", "map"}),
    ACTION_INTERACT("interact", "Interact with an item", new String[]{"use", "grab", "take", "pick up", "open"});

    ActionCodes(String name, String description, String[] aliases) {
        this.setEntityData(name, description, aliases);
    }
}
