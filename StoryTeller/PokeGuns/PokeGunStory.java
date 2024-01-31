package JavaExamProject_Group15.StoryTeller.PokeGuns;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Rooms.PokeGuns.AnimalEnslavatory;
import JavaExamProject_Group15.Entity.Rooms.PokeGuns.Arena1;
import JavaExamProject_Group15.Entity.Rooms.PokeGuns.Arena2;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.StoryTeller.StoryTeller;

import static JavaExamProject_Group15.Player.currPlayer;

public class PokeGunStory implements StoryTeller {
    Player[] playerList = new Player[2];
    public boolean checkVictory() {

    }

    public boolean checkLoss() {

    }

    public void cleanLastChapter() {

    }

    public void initiateChapter() {
        RoomBase lab = new AnimalEnslavatory();
        playerList[0] = new Player(lab);
        playerList[1] = new Player(lab);
        currPlayer = playerList[0];

        new Arena1();
        new Arena2();
    }

    public void narrativePrint() {
        System.out.println("Welcome to the PokeGuns game!");
        System.out.println("You are a simple american citizen, who is determined to be a good money maker.");
        System.out.println("here we use modified peo... animals to financially cripple each other.");
        System.out.println("First choose an animal, type 'help' to get more details.");
    }

    public void nextTurn(ActionBase prevActionCode, int prevItemCode, int prevRoomCode) {

    }

    public StoryTeller nextChapter() {
        return null;
    }
}
