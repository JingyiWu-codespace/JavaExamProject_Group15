package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.Inventory;

public enum Items {
    // **************** part one ****************
    ITEM_ER_STORAGE_KEY("ER storage room key", "use it to open the storage room where the bandage is in",
            new String[]{"er storage key", "storage key", "storage keys", "storage room keys", "storage room key"}, false) {
        @Override
        public void pickup() {
            super.pickup();
            if (!this.isInBag()) return;

            Rooms.ROOM_ER.addExit(Rooms.ROOM_ER_STORAGE);
            System.out.println("you also unlocked the storage room door and can go there");
        }
    },
    ITEM_BANDAGE("bandage", "you need take this to the patient in the ER room",
            new String[]{}, false) {
        public void interaction(){
            this.pickup();
            System.out.println("you got the bandage now");
        }
    },
    ITEM_ER_PATIENT("Bleeding Patient", "the patient is bleeding. give him the bandage, he'll do the rest.",
            new String[]{}, true) {
        @Override
        public void interaction() {
            if (!ITEM_BANDAGE.checkAccessibility()) {
                System.out.println("you don't have the bandage");
                return;
            }

            System.out.println("you give the bandage to the patient\n" +
                    "the patient's wound has been controlled and they start to express a passionate love for you.\n" +
                    "before you get to hear a cry for you love, they are taken away \n\n" );

            this.removeFromWorld();
            ITEM_BANDAGE.removeFromWorld();
            ITEM_ER_STORAGE_KEY.getOwningInventory().moveItem(ITEM_ER_STORAGE_KEY, Rooms.ROOM_ER.getInventory());
        }
    },

    // **************** part two ****************
    ITEM_REGISTER_FORM("registration form","you need this to registration form to check patients information",
            new String[]{"register form", "form"}, false),
    ITEM_FILLED_REGISTER_FORM("filled registration form","completed registration form",
            new String[]{"filled register form", "filled form"}, false),
    ITEM_PRESCRIPTION("prescription", " Used by healthcare professionals to document patient information and treatment plans.",
            new String[]{},false),
    ITEM_MEDICINE("medicine","patient need medicine, like antibiotic",
            new String[]{"drug"},false),
    ITEM_PHARMACY_CLERK("pharmacy clerk","The pharmacy clerk is waiting for your prescription",
            new String[]{"clerk"},true){
        @Override
        public void interaction(){
            if (!ITEM_PRESCRIPTION.checkAccessibility()) {
                System.out.println("the clerk is busy, he asks you what you want. \n" +
                        "then he notices you don't have the prescription and attends others\n");
                return;
            }
            System.out.println("You give the prescription to the pharmacy clerk\n" +
                    "The clerk gives you the medicine \n\n" );

            Player.player.getInventory().forcePlaceItem(ITEM_MEDICINE);
            ITEM_PRESCRIPTION.removeFromWorld();
        }
    },
    ITEM_NURSE("nurse","The nurse is waiting for your medicine",
            new String[]{},true){
        @Override
        public void interaction(){
            if (!(ITEM_MEDICINE.checkAccessibility())) {
                System.out.println("you are not in Emergency room or do not have medicine");
                return;
            }
            System.out.println("You give the medicine to the nurse\n" +
                    "The patient receives the right treatment and is send elsewhere \n\n" );

            ITEM_MEDICINE.removeFromWorld();
            ITEM_PATIENT_MEDICINE.removeFromWorld();
            ITEM_NURSE.removeFromWorld();
            Rooms.ROOM_RECEPTION_DESK.getInventory().forcePlaceItem(ITEM_REGISTER_FORM);
        }
    },

    ITEM_PATIENT_MEDICINE("ER patient", "the patient will help you fill the form.",
            new String[]{}, true) {
        @Override
        public void interaction() {
            if (ITEM_MEDICINE.checkAccessibility()) {
                ITEM_NURSE.interaction();
                return;
            }
            if (ITEM_PRESCRIPTION.checkAccessibility()||ITEM_FILLED_REGISTER_FORM.checkAccessibility()) {
                System.out.println("the patient needs medicine, not paperwork");
                return;
            }

            System.out.println("you start asking stuff from the patient\n" +
                    "you are collecting patient detailed information \n\n" );

            // Wait for 3 seconds and display countdown
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }
            System.out.println("Filled form, now find the doctor.");

            ITEM_REGISTER_FORM.removeFromWorld();
            Player.player.getInventory().forcePlaceItem(ITEM_FILLED_REGISTER_FORM);
        }
    },

    // **************** mix ****************
    ITEM_DOCTOR("Doctor", "the doctor (hopefully) gives the correct prescription for patients",
            new String[]{"dr", "doc"}, true) {
        @Override
        public void interaction() {
            if (!(ITEM_FILLED_REGISTER_FORM.checkAccessibility()|| ITEM_TEST_RESULT.checkAccessibility()|| ITEM_ULTRA_REPORT.checkAccessibility())) {
                System.out.println("the doctor is busy, he looks at you with a frown and says: \n" +
                        "I can't help you if you don't have the right documents, stop wasting time");
                System.out.println("you don't have the specific documents, like FILLED register form, test report...");
                return;
            }
            if(!ITEM_BISTOURY.getStationary()){
                System.out.println("Surgery prepare very well !!, you should accompany patient to surgery room");
                return;
            }

            System.out.println("you give the form to the doctor\n" +
                    "Doctor is thinking and writing something....  \n\n" );

            // Wait for 3 seconds and display countdown
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }

            System.out.println("Doctor gave you the prescription. no go to the pharmacy, FAST");

            ITEM_FILLED_REGISTER_FORM.removeFromWorld();
            Player.player.getInventory().forcePlaceItem(ITEM_PRESCRIPTION);
        }
    },

    // **************** part three ****************
    ITEM_BISTOURY("bistoury","surgery tools, need to be disinfect",
            new String[]{"knife"},true){
        public void interaction(){
            if (ITEM_MOTHER_PATIENT.checkAccessibility()) {
                System.out.println("you should back the patient into icu");
                return;
            }
            this.pickup();

            Rooms.ROOM_SURGERY.addExits(Rooms.ROOM_ER,Rooms.ROOM_ICU);

        }
    },
    ITEM_TEST_RESULT("test result","the result of patient blood analysis ",
            new String[]{"result","report","blood_test"},false){
        public void interaction() {
            if (!ITEM_MOTHER_PATIENT.checkAccessibility()) {
                System.out.println("you should take your patient do the test");
                return;
            }
            System.out.println("Medical staff are drawing blood.......  \n\n" );

            // Wait for 3 seconds and display countdown
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }

            System.out.println("Finish !!" +
                    "\n The result need to be wait for a few minutes. \n\n" );
            try {
                for (int i = 10; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }
            System.out.println("The Blood test report is out");

            this.pickup();
        }
    },
    ITEM_XRAY_IMAGE("X-ray image","X-ray images are extensively used in medical settings to visualize the internal structures of the human body. ",
            new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the X-ray image now");
        }
    },
    ITEM_ULTRA_REPORT("ultra report","This technique allows healthcare professionals to create real-time images of internal organs, tissues, and developing fetuses during pregnancy. ",
        new String[]{},false){
        public void interaction() {
            if (!ITEM_MOTHER_PATIENT.checkAccessibility()) {
                System.out.println("you should take your patient do the test");
                return;
            }
            System.out.println("Medical staff are doing Ultra image.......  \n\n" );

            // Wait for 3 seconds and display countdown
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }
            this.pickup();
            System.out.println("Finish !!");
        }
    },
    ITEM_CRADLE("cradle","Cradles are used for safely and comfortably holding infants or babies.",
            new String[]{},true){
        public void interaction(){
            this.pickup();
            System.out.println("you got the cradle now");
        }
    },
    ITEM_INCUBATOR("incubator","Incubators provide a controlled environment for premature or ill newborns, helping to regulate temperature and humidity.",
        new String[]{},true){
        public void interaction(){
            this.pickup();
            System.out.println("you got the incubator now");
        }
    },
    ITEM_WHEEL_CHAIR("wheelchair", "Wheelchairs are mobility devices used by individuals with limited or no ability to walk.",
            new String[]{},false),
    ITEM_EYE_CHART("eye chart", "Eye charts are used by eye care professionals to measure visual acuity and assess vision.",
            new String[]{},false),
    ITEM_COMPUTER("computer", "Computers are used in healthcare for various purposes, including patient record management and medical research.",
        new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the computer now");
        }
    },
    ITEM_MASKS("mask","Provide protection when dealing with infectious patients or\n" +
            "hazardous materials.",
        new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the mask now");
        }
    },
    ITEM_MEDICAL_GLOVES("medical gloves","Protect player from infections during patient treatment or surgery.",
            new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the medical gloves now");
        }
    },
    ITEM_SURGEON("surgeon","The surgeon is waiting for you to prepare for surgery",
            new String[]{},true){
        @Override
        public void interaction() {
            if (!(ITEM_TEST_RESULT.checkAccessibility()
                    && ITEM_ULTRA_REPORT.checkAccessibility()
                    && ITEM_MEDICINE.checkAccessibility())) {
                System.out.println("the surgeon tells you to get 3 documents first, test result, ultra report and drugs");
                return;
            }
            System.out.println("You give the documents to the surgeon\n The surgeon takes the patient. \n\n" );

            ITEM_MOTHER_PATIENT.getOwningInventory().moveItem(ITEM_MOTHER_PATIENT, Rooms.ROOM_SURGERY.getInventory());
            ITEM_TEST_RESULT.removeFromWorld();
            ITEM_ULTRA_REPORT.removeFromWorld();
            ITEM_MEDICINE.removeFromWorld();
        }
    },
    ITEM_MOTHER_PATIENT("to-be-mother patient", "A pregnant woman with congenital heart disease and twins",
            new String[]{"to-be-mother", "patient"}, true) {
        @Override
        public void interaction() {
            if (!Items.ITEM_REGISTER_FORM.checkAccessibility()&&!Items.ITEM_WHEEL_CHAIR.checkAccessibility()) {
                System.out.println("you don't have the register form nor the wheel chair");
                return;
            }
            if (!Items.ITEM_REGISTER_FORM.checkAccessibility()) {
                System.out.println("the register form is not enough, you should take the patient to the doctor");
                System.out.println("there is one in the storage room");
                return;
            }

            System.out.println("you give the form to the patient\n" +
                    "you are collecting patient detailed information \n\n" );

            // Wait for 3 seconds and display countdown
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }
            System.out.println("Filled form.");

            System.out.println("you gently put the patient on the wheelchair. no to the doc" );

            System.out.println("Then you need accompany the patient to do blood tests and ultrasound tests.");

            this.removeFromWorld();
            Player.player.getInventory().forcePlaceItem(this);
            ITEM_REGISTER_FORM.removeFromWorld();
            ITEM_WHEEL_CHAIR.removeFromWorld();
            Player.player.getInventory().forcePlaceItem(ITEM_FILLED_REGISTER_FORM);
        }
    }
    ;
    // ************************************************************

    public Boolean getStationary() {
        return this.entityData.getStationary();
    }

    public void setStationary(Boolean stationary) {
        this.entityData.setStationary(stationary);
    }

    public void interaction() {
        System.out.println("Can't figure out any interaction");
    }

    public void removeFromWorld() {
        if (this.getOwningInventory()==null) return;
        this.getOwningInventory().destroyItem(this);
    }

    public void pickup() {
        if (this.getStationary()) {
            System.out.println("You can't pick up this item directly, because its stationary");
            return;
        }
        if (this.isInBag()) {
            System.out.println("You already have this item in your bag");
            return;
        }
        if (!this.isInRoom()) {
            System.out.println("You can't pick up this item as its not in this room");
            return;
        }

        Inventory playerInv = Player.player.getInventory();
        Inventory owningInv = this.getOwningInventory();
        owningInv.moveItem(this, playerInv);
        System.out.println("You picked up the item, " + this.getName());
    }

    public Boolean checkAccessibility() {
        return this.isInBag() || this.isInRoom();
    }

    public Boolean isInBag() {
        Inventory playerInv = Player.player.getInventory();
        for (Items i : playerInv.getItemList())
            if (i == this) return true;
        return false;
    }

    public Boolean isInRoom() {
        Inventory currRoomInv = Player.player.getCurrentRoom().getInventory();
        for (Items i : currRoomInv.getItemList())
            if (i == this) return true;
        return false;
    }

    public Inventory getOwningInventory() {
        if (this.isInBag()) return Player.player.getInventory();
        if (this.isInRoom()) return Player.player.getCurrentRoom().getInventory();
        for (Rooms r : Rooms.values())
            if (r.getInventory().checkAvailable(this))
                return r.getInventory();
        return null;
    }

    // ************************************************************
    private final ItemDataHolder entityData;

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

    Items(String name, String description, String[] aliases, Boolean stationary) {
        this.entityData = new ItemDataHolder(name, description, aliases, stationary);
    }

    static class ItemDataHolder extends BaseEntityDataHolder {
        private Boolean stationary;

        ItemDataHolder(String name, String description, String[] aliases, Boolean stationary) {
            super(name, description, aliases);
            this.stationary = stationary;
        }

        public Boolean getStationary() {
            return stationary;
        }

        public void setStationary(Boolean stationary) {
            this.stationary = stationary;
        }
    }
}