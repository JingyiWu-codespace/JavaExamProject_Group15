package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;
/**
 * Enum representing various Rooms in the game.
 * Each Room have specific items and can be interacted with
 * in different ways depending on the game's context.
 */
public enum Rooms {
    // Floor 0
    ROOM_RECEPTION_DESK("Reception Desk"," serving as the first point of contact for patients, visitors, and anyone entering the hospital.",
            new String[]{"info desk", "reception"},new Items[]{Items.ITEM_REGISTER_FORM}),
    ROOM_OFFICE("Doctor's Office", "You should communicate with doctor then get prescription instead of take the prescription directly .",
            new String[]{"doctor office","office"}, new Items[]{Items.ITEM_DOCTOR}),
    ROOM_PHARMACY("Pharmacy","dispensing prescribed medications to patients.",
            new String[]{"medicine room", "drug store"},new Items[]{Items.ITEM_PHARMACY_CLERK}),
    ROOM_ER("Emergency Room", "the place where you don't wanna be in",
        new String[]{"emergency", "er"}, new Items[]{Items.ITEM_ER_STORAGE_KEY}),
    ROOM_ER_STORAGE("ER Storage room", "the ER storage room",
        new String[]{"storage room"}, new Items[]{}),

    // Floor 1
    ROOM_LABORATORY("laboratory","Perform tests like blood analysis to obtain vital health information about patients.",
        new String[]{"lab","test room"},new Items[]{Items.ITEM_TEST_RESULT}),
    ROOM_Ultrasonic("Ultrasonic","ultrasound procedures include abdominal ultrasounds, echocardiograms, and obstetric ultrasounds.",
        new String[]{"Ultra"},new Items[]{Items.ITEM_ULTRA_REPORT}),
    ROOM_WARD("Patient Ward", "Ward rooms provide comfortable accommodations for patients during their hospital stay.",
        new String[]{"ward"}, new Items[]{Items.ITEM_ALCOHOL}),
    ROOM_ELEVATOR("Elevator", "its an elevator, there are 3 floors",
            new String[]{"lift"}, new Items[]{Items.ITEM_BUTTONBAR}),
    ROOM_HALLWAY("Hallway", "its a hallway.",
                         new String[]{"corridor"}, new Items[]{}),

    // Floor 2
    ROOM_SURGERY("Surgery Room", "sterile environment where surgical procedures are performed by medical professionals.",
                         new String[]{"surgery"}, new Items[]{Items.ITEM_BLOODY_BISTOURY, Items.ITEM_SURGEON})
        ;

    static {
        ROOM_RECEPTION_DESK.addExits(ROOM_ER, ROOM_PHARMACY, ROOM_OFFICE, ROOM_ELEVATOR);
        ROOM_ELEVATOR.addExits(ROOM_RECEPTION_DESK);
        ROOM_OFFICE.addExits(ROOM_RECEPTION_DESK);
        ROOM_PHARMACY.addExits(ROOM_RECEPTION_DESK);

        ROOM_HALLWAY.addExits(ROOM_WARD, ROOM_LABORATORY, ROOM_Ultrasonic);
        ROOM_WARD.addExits(ROOM_HALLWAY);
        ROOM_LABORATORY.addExits(ROOM_HALLWAY);
        ROOM_Ultrasonic.addExits(ROOM_HALLWAY);


        ROOM_ER_STORAGE.addExit(ROOM_ER);
    }

    public void addExit(Rooms exit) {
        Rooms[] newExits = new Rooms[this.entityData.exits.length + 1];
        System.arraycopy(this.entityData.exits, 0, newExits, 0, this.entityData.exits.length);
        newExits[this.entityData.exits.length] = exit;
        this.entityData.setExits(newExits);
    }

    public void addExits(Rooms... exits) {
        for (Rooms ex : exits)
            this.addExit(ex);
    }

    public void removeExit(Rooms exit) {
        if (this.entityData.exits.length == 0) return;
        Rooms[] newExits = new Rooms[this.entityData.exits.length - 1];
        int i = 0;
        for (Rooms room : this.entityData.exits) {
            if (room == exit) continue;
            newExits[i] = room;
            i++;
        }
        this.entityData.setExits(newExits);
    }

    public void removeExits(Rooms... exits) {
        for (Rooms ex : exits)
            this.removeExit(ex);
    }

    public void isolate() {
        for (Rooms exit : this.entityData.exits)
            exit.removeExit(this);
        this.entityData.setExits(new Rooms[]{});
    }

    public Rooms[] getExits() {
        return this.entityData.exits;
    }

    public Inventory getInventory() {
        return this.entityData.inventory;
    }

    // ************************************************************
    private final Rooms.RoomDataHolder entityData;

    public String getName() {
        return this.entityData.getName();
    }

    public String getDescription() {
        return this.entityData.getDescription();
    }


    public void printInformation(){ this.entityData.printInformation(); }

    public Boolean checkCommand(String alias) {
        return this.entityData.checkCommand(alias);
    }

    public String[] getAliases() {
        return this.entityData.getAliases();
    }

    Rooms(String name, String description, String[] aliases, Items[] item_list) {
        this.entityData = new RoomDataHolder(name, description, aliases, item_list);
    }

    static class RoomDataHolder extends BaseEntityDataHolder {
        private Rooms[] exits;
        private final Inventory inventory;

        RoomDataHolder(String name, String description, String[] aliases, Items[] item_list) {
            super(name, description, aliases);
            this.exits = new Rooms[]{};
            this.inventory = new Inventory(item_list);
        }

        public Rooms[] getExits() {
            return this.exits;
        }

        public void setExits(Rooms... exits) {
            // remove duplicates
            Rooms[] newExits = new Rooms[exits.length];
            int i = 0;
            for (Rooms exit : exits) {
                boolean duplicate = false;
                for (Rooms room : newExits)
                    if (room == exit) {
                        duplicate = true;
                        break;
                    }
                if (duplicate) continue;

                newExits[i] = exit;
                i++;
            }

            Rooms[] temp = new Rooms[i];
            System.arraycopy(newExits, 0, temp, 0, i);
            this.exits = temp;
        }
    }
}