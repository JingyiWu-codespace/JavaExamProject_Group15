package JavaExamProject_Group15.Entity;

import JavaExamProject_Group15.Entity.Items.Chapter0.*;
import JavaExamProject_Group15.Entity.Items.Chapter1.*;
import JavaExamProject_Group15.Entity.Items.Chapter2.*;
import JavaExamProject_Group15.Entity.Items.PokeGuns.*;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals.*;
import JavaExamProject_Group15.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum representing various Rooms in the game.
 * Each Room have specific items and can be interacted with
 * in different ways depending on the game's context.
 */
public enum Rooms {
    // Floor 0
    AnimalEnslavatory(
            "Animal cages",
            "a place where animals are held captive for the purpose of total war.",
            new String[]{"enslavatory"},
            new ItemBase[]{new PikaTeaser(), new Politipno()}
    ),

    Arena1(
            "Arena1",
            "",
            new String[]{""},
            new ItemBase[]{}
    ),

    Arena2(
            "Arena2",
            "",
            new String[]{""},
            new ItemBase[]{}
    ),


    // Floor 0
    ROOM_RECEPTION_DESK(
            "Reception Desk",
            " serving as the first point of contact for patients, visitors, and anyone entering the hospital.",
            new String[]{"info desk", "reception"},
            new ItemBase[]{new ITEM_REGISTER_FORM()}
    ),
    ROOM_OFFICE(
            "Doctor's Office",
            "You should communicate with doctor then get prescription instead of take the prescription directly .",
            new String[]{"doctor office", "office"},
            new ItemBase[]{new ITEM_DOCTOR()}
    ),
    ROOM_PHARMACY(
            "Pharmacy",
            "dispensing prescribed medications to patients.",
            new String[]{"medicine room", "drug store"},
            new ItemBase[]{new ITEM_PHARMACY_CLERK()}
    ),

    ROOM_ER(
            "Emergency Room",
            "the place where you don't wanna be in",
            new String[]{"emergency", "er"},
            new ItemBase[]{new ITEM_ER_STORAGE_KEY()}
    ),
    ROOM_ER_STORAGE(
            "ER Storage room",
            "the ER storage room",
            new String[]{"storage room"},
            new ItemBase[]{}
    ),

    // Floor 1
    ROOM_LABORATORY(
            "laboratory",
            "Perform tests like blood analysis to obtain vital health information about patients.",
            new String[]{"lab", "test room"},
            new ItemBase[]{new ITEM_TEST_RESULT()}
    ),
    ROOM_Ultrasonic(
            "Ultrasonic",
            "ultrasound procedures include abdominal ultrasounds, echocardiograms, and obstetric ultrasounds.",
            new String[]{"Ultra"},
            new ItemBase[]{new ITEM_ULTRASOUND_REPORT()}
    ),
    ROOM_WARD(
            "Patient Ward",
            "Ward rooms provide comfortable accommodations for patients during their hospital stay.",
            new String[]{"ward"},
            new ItemBase[]{new ITEM_ALCOHOL()}
    ),
    ROOM_ELEVATOR(
            "Elevator",
            "its an elevator, there are 3 floors",
            new String[]{"lift"},
            new ItemBase[]{new ITEM_BUTTONBAR()}
    ),
    ROOM_HALLWAY(
            "Hallway",
            "its a hallway.",
            new String[]{"corridor"},
            new ItemBase[]{}
    ),

    // Floor 2
    ROOM_SURGERY(
            "Surgery Room",
            "sterile environment where surgical procedures are performed by medical professionals.",
            new String[]{"surgery"},
            new ItemBase[]{new ITEM_BLOODY_BISTOURY(), new ITEM_SURGEON()}
    );

    static {
        Rooms.bidirPassage(ROOM_RECEPTION_DESK, ROOM_ER);
        Rooms.bidirPassage(ROOM_RECEPTION_DESK, ROOM_PHARMACY);
        Rooms.bidirPassage(ROOM_RECEPTION_DESK, ROOM_OFFICE);
        Rooms.bidirPassage(ROOM_RECEPTION_DESK, ROOM_ELEVATOR);

        Rooms.bidirPassage(ROOM_HALLWAY, ROOM_WARD);
        Rooms.bidirPassage(ROOM_HALLWAY, ROOM_LABORATORY);
        Rooms.bidirPassage(ROOM_HALLWAY, ROOM_Ultrasonic);

        ROOM_ER_STORAGE.addExits(ROOM_ER);
    }

    // ************************************************************

    private final Inventory inventory;
    // name and description related functions
    private final BasicData basicData;
    private List<Rooms> exits = new ArrayList<>();

    private Rooms(String name, String description, String[] alias, ItemBase[] item_list) {
        this.basicData = new BasicData(name, description, alias);
        this.inventory = new Inventory(item_list);
    }

    private static void bidirPassage(Rooms roomA, Rooms roomB) {
        roomA.addExits(roomB);
        roomB.addExits(roomA);
    }

    public List<Rooms> getExits() {
        return this.exits;
    }

    public Inventory getRoomInv() {
        return this.inventory;
    }

    public void removeAllExits() {
        this.exits = new ArrayList<Rooms>();
    }

    public void addExits(Rooms... toBeExist) {
        for (Rooms exit : toBeExist) {
            if (this.exits.contains(exit)) continue;
            this.exits.add(exit);
        }
    }

    public String getName() {
        return this.basicData.getName();
    }

    public String getDescription() {
        return this.basicData.getDescription();
    }

    public String[] getAliases() {
        return this.basicData.getAliases();
    }

    public void printInformation() {
        this.basicData.printInformation();
    }

    private static class BasicData extends BasicDataHolder {
        private BasicData(String name, String description, String[] alias) {
            super(name, description, alias);
        }
    }
}