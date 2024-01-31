package JavaExamProject_Group15.StoryTeller.PokeGuns;

import JavaExamProject_Group15.Entity.Actions.ActionBase;
import JavaExamProject_Group15.Entity.Actions.Primary.ACTION_INTERACT;
import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Items.PokeGuns.*;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;
import JavaExamProject_Group15.Entity.Rooms.PokeGuns.*;
import JavaExamProject_Group15.Inventory;
import JavaExamProject_Group15.Player;
import JavaExamProject_Group15.StoryTeller.StoryTeller;

import static JavaExamProject_Group15.Player.currPlayer;

public class PokeGunStory extends StoryTeller {
    static Player[] playerList = new Player[2];

    public boolean checkVictory() {
        RoomBase rivalRoom = getRival().getCurrentRoom();
        if(rivalRoom instanceof AnimalEnslavatory)
            return false;
        HP rivalHP = Inventory.getFromRoom(HP.class, rivalRoom);
        return rivalHP.getHP() <= 0;
    }

    public boolean checkLoss() {
        RoomBase currRoom = currPlayer.getCurrentRoom();
        if(currRoom instanceof AnimalEnslavatory)
            return false;
        HP currHP = Inventory.getFromRoom(HP.class, currRoom);
        return currHP.getHP() <= 0;
    }

    public void roomInitiate() {
        new AnimalEnslavatory();
    }

    public void actionInitiate() { }

    public void playerInitiate() {
        RoomBase lab = RoomBase.getRoomObj(AnimalEnslavatory.class);
        playerList[0] = new Player(lab, "player 1");
        playerList[1] = new Player(lab, "player 2");
        currPlayer = playerList[0];
    }

    public void narrativePrint() {
        System.out.println("Welcome to the PokeGuns game!");
        System.out.println("You are a simple american citizen, who is determined to be a good money maker.");
        System.out.println("here we use modified peo... animals to financially cripple each other.");
        System.out.println("First choose an animal, type 'help' to get more details.");
    }

    public void endTurnPostAction(ActionBase prevActionCode, ItemBase prevItemCode, RoomBase prevRoomCode, boolean actionReturn) {
        if (!((prevActionCode instanceof ACTION_INTERACT) && actionReturn)) return;

        currPlayer = getRival();
        System.out.println("\n\n **** It's " + currPlayer.getPlayerName() + "'s turn ****\n\n");
    }

    public StoryTeller nextChapter() {
        return null;
    }

    public static Player getRival() {
        if(currPlayer == playerList[0])
            return playerList[1];
        else
            return playerList[0];
    }

    public static RoomBase findRivalRoom() {
        return PokeGunStory.getRival().getCurrentRoom();
    }
}
