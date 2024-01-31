package JavaExamProject_Group15;


import JavaExamProject_Group15.StoryTeller.Hospital.Chapter0;
import JavaExamProject_Group15.StoryTeller.PokeGuns.PokeGunStory;

import java.io.IOException;
import java.util.Scanner;

public class MainRun {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // prompt the use for the name of the game
        System.out.println("Enter the name of the game: (PokeGuns or Hospital)");
        String gameName = sc.nextLine();
        GameManager gm;
        if (gameName.equals("PokeGuns"))
            gm = new GameManager(new PokeGunStory());
        else if (gameName.equals("Hospital"))
            gm = new GameManager(new Chapter0());
        else
            throw new IllegalArgumentException("Invalid game name");

        while (true)
            gm.mainLoop();
    }
}


