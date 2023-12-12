package JavaExamProject_Group15.Entity;

public abstract class BaseEntityDataHolder {
    public final String name;
    private final String description;
    private final String[] aliases;

    BaseEntityDataHolder(String name, String description, String[] aliases) {
        this.name = name;
        this.description = description;

        this.aliases = new String[aliases.length + 1];
        this.aliases[0] = name;
        for (int i = 0; i < aliases.length; i++) this.aliases[i + 1] = aliases[i];
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean checkCommand(String alias) {
        for (String a : this.aliases)
            if (a.equals(alias)) return true;
        return false;
    }

    public String[] getCommandArray() {
        return this.aliases;
    }
}
