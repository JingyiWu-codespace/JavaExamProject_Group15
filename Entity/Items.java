package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.Inventory;

public enum Items {
    ITEM_ER_STORAGE_KEY("ER storage room key", "use it to open the storage room where the bandage is in",
            new String[]{"er storage key"}, false) {
        @Override
        public void interaction() {
            this.pickup();
            Rooms.ROOM_ER.setExits(Rooms.ROOM_ER_STORAGE);
            System.out.println("you can now open the storage room and go there");
        }
    },
    BANDAGE("bandage", "you need take this to the patient in the ER room", new String[]{}, false) {
        public void interaction(){
            this.pickup();
            System.out.println("you got the bandage now");
        }
    },
    REGISTER_FORM("register form","you need this to register from to ckeck patient informatin",
            new String[]{"form"}, false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the register form now");
        }
    },
    MEDICINE("medicine","patient need medicine, like antibiotic",new String[]{"drug"},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the medicine now");
            Rooms.ROOM_PHARMACY.setExits(Rooms.ROOM_ER,Rooms.ROOM_ICU);

        }
    },
    BISTOURY("bistoury","surgery tools, need to be disinfect",new String[]{"knife"},true){
        public void interaction(){
            if (checkAccessibility(Items.ER_PATIENT3)) {
                System.out.println("you should back the patient into icu");
                return;
            }
            this.pickup();

            Rooms.ROOM_SURGERY.setExits(Rooms.ROOM_ER,Rooms.ROOM_ICU);

        }
    },
    TEST_RESULT("test result","the result of patient blood analysis ",new String[]{"result","report","blood_test"},false){
        public void interaction() {
            if (!checkAccessibility(Items.ER_PATIENT3)) {
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
            Rooms.ROOM_LABORATORY.setExits(Rooms.ROOM_Ultrasonic);
            Rooms.ROOM_LABORATORY.setExits(Rooms.ROOM_OFFICE);
            System.out.println("Finish !!" +
                    "\n The result need to be waited 10 minutes, you can continue next test  \n\n" );
            try {
                for (int i = 10; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }
            System.out.println("The Blood test report is out, please take it");
            this.pickup();
            Rooms.ROOM_LABORATORY.setExits(Rooms.ROOM_Ultrasonic);
            Rooms.ROOM_LABORATORY.setExits(Rooms.ROOM_OFFICE);

        }
    },
    XRAY_IMAGE("X-ray image","X-ray images are extensively used in medical settings to visualize the internal structures of the human body. ",new String[]{"image"},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the X-ray image now");
        }
    },
    ULTRA_REPORT("ultra report","This technique allows healthcare professionals to create real-time images of internal organs, tissues, and developing fetuses during pregnancy. ",new String[]{"ultra"},false){
        public void interaction() {
            if (!checkAccessibility(Items.ER_PATIENT3)) {
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
            Rooms.ROOM_Ultrasonic.setExits(Rooms.ROOM_OFFICE);
            Rooms.ROOM_Ultrasonic.setExits(Rooms.ROOM_LABORATORY);


            this.pickup();
            System.out.println("Finish !! You got it, now doctor need to read the result" +
                    "\n you should take the patient and results find doctor" );
        }
    },
    CRADLE("cradle","Cradles are used for safely and comfortably holding infants or babies.",new String[]{},true){
        public void interaction(){
            this.pickup();
            System.out.println("you got the cradle now");
        }
    },
    INCUBATOR("incubator","Incubators provide a controlled environment for premature or ill newborns, helping to regulate temperature and humidity.",new String[]{},true){
        public void interaction(){
            this.pickup();
            System.out.println("you got the incubator now");
        }
    },
    WHEEL_CHAIR("wheelchair", "Wheelchairs are mobility devices used by individuals with limited or no ability to walk.",new String[]{},false){
        public void interaction(){
            this.pickup();
            Rooms.ROOM_ER.setExits(Rooms.ROOM_RECEPTION_DESK);
            System.out.println("you got the wheelchair now");
        }
    },
    EYE_CHART("eye chart", "Eye charts are used by eye care professionals to measure visual acuity and assess vision.",new String[]{},false),
    COMPUTER("computer", "Computers are used in healthcare for various purposes, including patient record management and medical research.",new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the computer now");
        }
    },
    PRESCRIPTION("prescription", " Used by healthcare professionals to document patient information and treatment plans.",new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the prescription now");
        }
    },
    MASKS("mask","Provide protection when dealing with infectious patients or\n" +
            "hazardous materials.",new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the mask now");
        }
    },
    MEDICAL_GLOVES("medical gloves","Protect player from infections during patient treatment or surgery.",new String[]{},false){
        public void interaction(){
            this.pickup();
            System.out.println("you got the medical gloves now");
        }
    },
    NURSE("nurse","The nurse is waiting for your medicine",new String[]{},false){
        @Override
        public void interaction(){
            if (!(checkAccessibility(Items.MEDICINE)&&Player.player.getCurrentRoom()==Rooms.ROOM_ER)) {
                System.out.println("you are not in Emergency room or do not have medicine");
                return;
            }
            Player.player.getInventory().destroyItem(Items.MEDICINE);
            System.out.println("You give the medicine to the nurse\n" +
                    "The patient receives the right treatment \n\n" );
            //new StoryTeller().check_victory();

            //Player.player.setRoundCounter(2);
            this.getOwningInventory(REGISTER_FORM).moveItem(REGISTER_FORM,Rooms.ROOM_RECEPTION_DESK.getInventory());

        }
    },



    ER_PATIENT("patient", "the patient needs bandage", new String[]{}, true) {
        @Override
        public void interaction() {
            if (!checkAccessibility(Items.BANDAGE)) {
                System.out.println("you don't have the bandage");
                return;
            }

            Player.player.getInventory().destroyItem(Items.BANDAGE);
            System.out.println("you give the bandage to the patient\n" +
                    "the patient's wound has been controlled. \n\n" );
            //new StoryTeller().check_victory();
            this.getOwningInventory(Items.ER_PATIENT).destroyItem(Items.ER_PATIENT);
            this.getOwningInventory(ITEM_ER_STORAGE_KEY).moveItem(ITEM_ER_STORAGE_KEY,Rooms.ROOM_ER.getInventory());
        }
    },
    DOCTOR("doctor", "the doctor give correct prescription for patient's register from", new String[]{"dr","Dr","DR"}, false) {
        @Override
        public void interaction() {
            if (!(checkAccessibility(Items.REGISTER_FORM)||checkAccessibility(Items.TEST_RESULT)||checkAccessibility(Items.ULTRA_REPORT))) {
                System.out.println("you don't have the specific documents, like register form, test report...");
                return;
            }
            if(!BISTOURY.getStationary()){
                System.out.println("Surgery prepare very well !!, you should accompany patient to surgery room");
                return;
            }

            System.out.println("you give the form to the doctor\n" +
                    "Doctor is considering....  \n\n" );

            // Wait for 3 seconds and display countdown
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }
            //this.getOwningInventory(Items.DOCTOR).destroyItem(Items.DOCTOR);
            System.out.println("Doctor gave the prescription/plan form doctor, you can takeaway");
            //Player.player.getInventory().destroyItem(Items.REGISTER_FORM);
            Rooms.ROOM_OFFICE.setExits(Rooms.ROOM_PHARMACY,Rooms.ROOM_ICU,Rooms.ROOM_SURGERY);
            Rooms.ROOM_ICU.setExits(Rooms.ROOM_SURGERY,Rooms.ROOM_OFFICE,Rooms.ROOM_ER);

        }
    },

    ER_PATIENT2("patient2", "the patient infomation need to be filled into register form", new String[]{}, true) {
        @Override
        public void interaction() {
            if (!checkAccessibility(Items.REGISTER_FORM)) {
                System.out.println("you don't have the register form ");
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
            System.out.println("Filled form then find the doctor.");
            Rooms.ROOM_ER.setExits(Rooms.ROOM_OFFICE);
            //this.getOwningInventory(REGISTER_FORM).moveItem(REGISTER_FORM,Rooms.ROOM_RECEPTION_DESK.getInventory());
            this.getOwningInventory(Items.ER_PATIENT2).destroyItem(Items.ER_PATIENT2);


        }
    },
    ER_PATIENT3("patient3", "A pregnant woman with congenital heart disease and twins", new String[]{"patient3","mother-to-be"}, false) {
        @Override
        public void interaction() {
            if (!(checkAccessibility(Items.REGISTER_FORM)&&checkAccessibility(Items.WHEEL_CHAIR))) {
                System.out.println("you don't have the register form OR wheel chair");
                return;
            }

            System.out.println("you give the form to the patient\n" +
                    "you are collecting patient detailed information \n\n" );
            this.pickup();
            // Wait for 3 seconds and display countdown
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("wait " + i + "m...");
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting.");
            }
            //this.getOwningInventory(Items.ER_PATIENT3).destroyItem(Items.ER_PATIENT3);
            System.out.println("Filled form.");
            Rooms.ROOM_ER.setExits(Rooms.ROOM_Ultrasonic,Rooms.ROOM_LABORATORY);
            System.out.println("Then you need accompany with patient to do blood tests and ultrasound tests.");


        }
    }
    ;

//    public boolean infected = false;

    public Boolean getStationary() {
        return this.entityData.getStationary();
    }

    public void setStationary(Boolean stationary) {
        this.entityData.setStationary(stationary);
    }

    public void interaction() {
        System.out.println("Can't figure out any interaction");
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
        // todo
//        System.out.println("ERROR > item not found in any inventory");
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