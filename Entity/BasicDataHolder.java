package JavaExamProject_Group15.Entity;

import static JavaExamProject_Group15.UserParser.userInterface;

/**
 * The BaseEntityDataHolder class serves as an abstract base class for entities in the game.
 * It holds common data elements such as name, description, and aliases. This class provides
 * foundational functionality for all entities that share these common characteristics.
 */
public abstract class BasicDataHolder {
    // The name of the entity.
    protected String name;

    // The description of the entity.
    protected String description;

    //The list of aliases associated with the entity.
    protected String[] aliases;

    protected BasicDataHolder(String name, String description, String[] aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;

        userInterface.addCommand(this);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    private Boolean checkCommand(String commandName) {
        if (this.name.equals(commandName)) return true;
        for (String a : this.aliases)
            if (a.equals(commandName)) return true;
        return false;
    }

    public void printInformation() {
        System.out.println("  - '" + this.getName() + "'");
        System.out.println("        details: " + this.getDescription());
    }

    private void printAliases() {
        System.out.println("        aliases: ");
        for (String a : this.aliases)
            System.out.print(a + ", ");
    }
}

