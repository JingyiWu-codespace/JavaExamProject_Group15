package JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals;

public class PikaTeaser extends PokeBallBase {
    public PikaTeaser() {
        super(
                "PikaTeaser",
                "The american version of a Pikachu",
                new String[]{},
                false
        );
        addPowerAttack("Police Brutality", "your nose did need a shake up", 10, 20);
        addDebuff("Prison Time", "you are carrying weed btw", -10, 20);
        addBuff("Racism", "an american traditional dish meant to improve self confidence", 10, 20);
    }
}
