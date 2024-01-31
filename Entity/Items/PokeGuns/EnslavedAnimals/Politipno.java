package JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals;

public class Politipno extends PokeBallBase {
    public Politipno() {
        super(
                "Politipno",
                "The american version of a Hypno, but uses lies instead of hypnosis.",
                new String[]{},
                false
        );
        addPowerAttack("Lies", "The most powerful weapon in the world.", 10, 20);
        addBuff("Fake News", "The most powerful weapon in the world.", 10, 20);
        addDebuff("Truth", "The most powerful weapon in the world.", -20, 20);
    }
}
