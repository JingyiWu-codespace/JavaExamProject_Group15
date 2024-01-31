package JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals;

public class Politipno extends PokeBallBase {
    public Politipno() {
        super(
                "Politipno",
                "The american version of a Hypno, but uses lies instead of hypnosis.",
                new String[]{},
                false
        );
        addPowerAttack("Claims", "also know as lies", 10, 20);
        addBuff("Its Fake News", "The most powerful weapon in the world.", 10, 20);
        addDebuff("Propaganda", "but people are retarded", -20, 20);
        addBuff("Embezzlement", "our money comrade", 20, 20);
    }
}
