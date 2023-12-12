package JavaExamProject_Group15.Entity;

public interface BaseEntityInterface {
    BaseEntityDataHolder entityData = new BaseEntityDataHolder();

    default void setEntityData(String name, String description, String[] aliases) {
        this.entityData.setData(name, description, aliases);
    }

    default String getName() {
        return this.entityData.name;
    }

    default String getDescription() {
        return this.entityData.description;
    }

    default Boolean checkCommand(String alias) {
        for (String a : this.entityData.aliases)
            if (a.equals(alias)) return true;
        return false;
    }

    default String[] getCommandArray() {
        return this.entityData.aliases;
    }
}

class BaseEntityDataHolder {
    String name;
    String description;
    String[] aliases;

    void setData(String name, String description, String[] aliases) {
        this.name = name;
        this.description = description;

        this.aliases = new String[aliases.length + 1];
        this.aliases[0] = name;
        System.arraycopy(aliases, 0, this.aliases, 1, aliases.length);
    }
}
