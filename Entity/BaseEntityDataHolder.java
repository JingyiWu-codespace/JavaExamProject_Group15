package JavaExamProject_Group15.Entity;

import java.util.Arrays;
/**
 * The BaseEntityDataHolder class serves as an abstract base class for entities in the game.
 * It holds common data elements such as name, description, and aliases. This class provides
 * foundational functionality for all entities that share these common characteristics.
 */
public abstract class BaseEntityDataHolder {
    /**
     * The name of the entity.
     */
    public final String name;

    /**
     * The description of the entity.
     */
    private final String description;

    /**
     * The list of aliases associated with the entity.
     */
    private final String[] aliases;

    /**
     * Constructs a new BaseEntityDataHolder with the specified name, description, and aliases.
     * The name is also automatically added to the aliases list.
     *
     * @param name        The name of the entity.
     * @param description The description of the entity.
     * @param aliases     The aliases associated with the entity.
     */
    BaseEntityDataHolder(String name, String description, String[] aliases) {
        this.name = name;
        this.description = description;

        this.aliases = new String[aliases.length + 1];
        this.aliases[0] = name.toLowerCase();
        aliases = Arrays.stream(aliases).map(String::toLowerCase).toArray(String[]::new);
        System.arraycopy(aliases, 0, this.aliases, 1, aliases.length);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Prints the information of the entity, including its name and details.
     */
    public void printInformation(){
        System.out.println("  - '" + this.getName()+"'");
        System.out.println("        details: " + this.getDescription());
    }
    /**
     * Retrieves the aliases associated with the entity.
     *
     * @return An array of aliases for the entity.
     */
    public String[] getAliases() {
        return this.aliases;
    }
    /**
     * Checks if the provided alias matches any of the entity's aliases.
     *
     * @param alias The alias to check.
     * @return True if the alias matches any of the entity's aliases, false otherwise.
     */
    public Boolean checkCommand(String alias) {
        for (String a : this.aliases)
            if (a.equals(alias)) return true;
        return false;
    }
}
