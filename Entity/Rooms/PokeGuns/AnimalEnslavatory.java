package JavaExamProject_Group15.Entity.Rooms.PokeGuns;

import JavaExamProject_Group15.Entity.Items.ItemBase;
import JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals.F35Zard;
import JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals.PikaTeaser;
import JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals.Politipno;
import JavaExamProject_Group15.Entity.Rooms.RoomBase;

public class AnimalEnslavatory extends RoomBase {
    //    AnimalEnslavatory(
//            "Animal cages",
//            "a place where animals are held captive for the purpose of total war.",
//            new String[]{"enslavatory"},
//            new ItemBase[]{new PikaTeaser(), new Politipno()}
//    ),
    public AnimalEnslavatory() {
        super(
                "Animal cages",
                "a place where animals are held captive for the purpose of total war.",
                new String[]{"enslavatory"},
                new ItemBase[]{new PikaTeaser(), new Politipno(), new F35Zard()}
        );
    }
}
