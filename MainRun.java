package JavaExamProject_Group15;


import JavaExamProject_Group15.StoryTeller.Hospital.Chapter0;
import JavaExamProject_Group15.StoryTeller.PokeGuns.PokeGunStory;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class MainRun {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the game: (PokeGuns or Hospital)");
        String gameName = sc.nextLine().toLowerCase();
        while (!gameName.equals("pokeGuns") && !gameName.equals("hospital")) {
            System.out.println("Invalid game name, please enter again");
            gameName = sc.nextLine().toLowerCase();
        }

        GameManager gm;
        if (gameName.equals("PokeGuns")) gm = new GameManager(new PokeGunStory());
        else gm = new GameManager(new Chapter0());

        while (true)
            gm.mainLoop();
    }
}


