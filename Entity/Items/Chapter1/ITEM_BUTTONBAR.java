package JavaExamProject_Group15.Entity.Items.Chapter1;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_ELEVATOR;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_HALLWAY;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_RECEPTION_DESK;
import JavaExamProject_Group15.Entity.Rooms.Hospital.ROOM_SURGERY;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

import java.util.Scanner;

public class ITEM_BUTTONBAR extends ItemBase {
    public ITEM_BUTTONBAR() {
        super(
                "elevator buttons",
                "you can use it to choose the floor you want to go",
                new String[]{"buttons", "button", "keypad"},
                true
        );
    }

    public void interaction() {
        System.out.println("you can choose the floor you want to go");
        System.out.println("0. Ground floor (Reception)");
        System.out.println("1. floor 1 (Hallway)");
        System.out.println("2. floor 2 (Surgery)");
        System.out.println("Please enter the number of the floor you want to go");

        int floor = -1;
        while (floor < 0 || floor > 2)
            try {
                floor = Integer.parseInt(new Scanner(System.in).nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 0 and 2");
            }

        RoomBase.removeAllExits(ROOM_ELEVATOR.class);
        switch (floor) {
            case 0:
                RoomBase.bidirPassage(ROOM_ELEVATOR.class, ROOM_RECEPTION_DESK.class);
                break;
            case 1:
                RoomBase.bidirPassage(ROOM_ELEVATOR.class, ROOM_HALLWAY.class);
                break;
            case 2:
                RoomBase.bidirPassage(ROOM_ELEVATOR.class, ROOM_SURGERY.class);
                break;
        }
        System.out.println("you are now in floor " + floor);
    }
}

    