package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Inventory;

public enum Rooms {
    // Floor 0
    ROOM_ER("Emergency Room", "the place where you don't wanna be in",
        new String[]{"emergency", "er"}, new Items[]{Items.ITEM_ER_PATIENT, Items.ITEM_ER_STORAGE_KEY,Items.ITEM_ER_PATIENT2,Items.ITEM_ER_PATIENT3}),
    ROOM_ER_STORAGE("ER Storage room", "the ER storage room",
        new String[]{"storage room"}, new Items[]{Items.ITEM_BANDAGE,Items.ITEM_WHEEL_CHAIR}),
    ROOM_RECEPTION_DESK("ReceptionDesk"," serving as the first point of contact for patients, visitors, and anyone entering the hospital.",
        new String[]{"info","rd"},new Items[]{Items.ITEM_REGISTER_FORM}),
    ROOM_PHARMACY("Pharmacy","dispensing prescribed medications to patients.",
        new String[]{"medicine room", "drug store"},new Items[]{Items.ITEM_MEDICINE}),
    ROOM_LABORATORY("laboratory","Perform tests like blood analysis to obtain vital health information about patients.",
        new String[]{"lab","test room"},new Items[]{Items.ITEM_TEST_RESULT}),
    // Floor 1
    ROOM_RADIOLOGY("x-ray","Conduct imaging tests, acquire diagnostic information, for example X-ray analysis.",
        new String[]{"xray"},new Items[]{Items.ITEM_XRAY_IMAGE}),
    ROOM_Ultrasonic("Ultrasonic","ultrasound procedures include abdominal ultrasounds, echocardiograms, and obstetric ultrasounds.",
        new String[]{"Ultra"},new Items[]{Items.ITEM_ULTRA_REPORT}),
    ROOM_ICU("ICU", "An intensive care unit (ICU) room is equipped with advanced monitoring and life support equipment for critically ill patients.",
        new String[]{}, new Items[]{}),
    ROOM_SURGERY("Surgery Room", "sterile environment where surgical procedures are performed by medical professionals.",
        new String[]{"surgery"}, new Items[]{Items.ITEM_BISTOURY}),
    ROOM_WARD
            ("Patient Ward", "Ward rooms provide comfortable accommodations for patients during their hospital stay.",
        new String[]{"ward"}, new Items[]{Items.ITEM_ER_PATIENT}),
    // Floor 2
    ROOM_OBSTETRICS("Obstetrics Medicial Area", "designed for childbirth and maternity care, ensuring a safe and comfortable environment for expectant mothers.",
        new String[]{"baby room", "enfant"}, new Items[]{Items.ITEM_CRADLE, Items.ITEM_INCUBATOR}),
    ROOM_GYNECOLOGY("Gynecology Medicial Area", "dedicated to women's health and provide services related to gynecological examinations and treatments.",
        new String[]{}, new Items[]{}),
    ROOM_REHABILITATION("Rehabilitation Room", "are equipped for physical and occupational therapy to aid patients in their recovery and mobility.",
        new String[]{}, new Items[]{Items.ITEM_WHEEL_CHAIR}),
    ROOM_ENT("ENT Medical Area", "are specialized for examinations and treatments related to ear, nose, and throat conditions.",
        new String[]{"ent", "eye","nose","throat"}, new Items[]{Items.ITEM_EYE_CHART}),
    ROOM_OFFICE("Doctor Office", "You should communicate with doctor then get prescription instead of take the prescription directly .",
        new String[]{"office","doctionoffice","df"}, new Items[]{Items.ITEM_DOCTOR,Items.ITEM_COMPUTER, Items.ITEM_PRESCRIPTION});

    static {
        ROOM_ER_STORAGE.addExit(ROOM_ER);
        ROOM_ER.addExit(ROOM_RECEPTION_DESK);
        ROOM_RECEPTION_DESK.addExit(ROOM_ER);
        ROOM_RECEPTION_DESK.addExit(ROOM_PHARMACY);
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
            this.exits = exits;
        }
    }
}