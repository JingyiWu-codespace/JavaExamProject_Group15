package JavaExamProject_Group15.Entity;

public enum Actions {
    ACTION_HELP("help", "Show available actions and their descriptions", new String[]{"h", "commands"}),
    ACTION_INVENTORY_CHECK("inventory", "Check your bag", new String[]{"inv", "bag"}),
    ACTION_ROOM_CHECK("room", "Check the current room and items in it", new String[]{"where", "location"}),
    ACTION_QUIT("quit", "quits the game, who would have thought", new String[]{"Q","q"}),
    ACTION_MOVE("move", "Move to a different room", new String[]{"go", "walk", "run", "sprint"}),
    ACTION_DISINFECT("disinfect","disinfect specific item",new String[]{"clear"}),
    ACTION_EXITS("exits", "Show possible rooms to go to", new String[]{"exit"}),
    ACTION_INTERACT("interact", "Interact with an item", new String[]{"use", "get", "grab", "take", "pickup", "open", "talk"}),
    ACTION_LEAVE("leave","leave something in room",new String[]{"put"}),
    ACTIONS_MAP("maps","the layout of hospital",new String[]{"Map","MAP","map"}),
    ACTIONS_ELEVATOR("elevator","user elevator to specific floor",new String[]{"lift"}),
    ACTIONS_STAIR("stair","user stair to specific floor",new String[]{"steps"});
    ;

    // ************************************************************
    private final ActionDataHolder entityData;

    public String getName() {
        return this.entityData.getName();
    }

    public String getDescription() {
        return this.entityData.getDescription();
    }

    public Boolean checkCommand(String alias) {
        return this.entityData.checkCommand(alias);
    }

    public String[] getAliases() {
        return this.entityData.getAliases();
    }

    Actions(String name, String description, String[] aliases) {
        this.entityData = new ActionDataHolder(name, description, aliases);
    }

    static class ActionDataHolder extends BaseEntityDataHolder {
        ActionDataHolder(String name, String description, String[] aliases) {
            super(name, description, aliases);
        }
    }

}
