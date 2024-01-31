package JavaExamProject_Group15.Entity.Items.PokeGuns.EnslavedAnimals;

public class F35Zard extends PokeBallBase {
    public F35Zard() {
        super(
                "Flamezard",
                "The american version of a Charizard, shaped like a flame thrower",
                new String[]{},
                false
        );
        addPowerAttack("Expand Democracy", "Inva... bring voting rights for the to be dead people", 10, 10);
        addBuff("Freedom cry", "WHAT IS A KILOMETEEEEER", 20, 20);
        addDebuff("CIA", "We work in the shadows.", -30, 20);
    }
}
