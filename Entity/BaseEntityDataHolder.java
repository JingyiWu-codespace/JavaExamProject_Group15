package JavaExamProject_Group15.Entity;

import java.util.Arrays;

public abstract class BaseEntityDataHolder {
    public final String name;
    private final String description;
    private final String[] aliases;

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

    public void printInformation(){
        System.out.println("  - '" + this.getName()+"'");
        System.out.println("        details: " + this.getDescription());
    }
    public String[] getAliases() {
        return this.aliases;
    }

    public Boolean checkCommand(String alias) {
        for (String a : this.aliases)
            if (a.equals(alias)) return true;
        return false;
    }
}
