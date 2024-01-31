package JavaExamProject_Group15.Entity.Items.PokeGuns;

import JavaExamProject_Group15.Entity.Items.ItemBase;

public class HP extends ItemBase {
    int amount = 100;
    int insurance = 0;

    public HP() {
        super(
                "finances",
                "its the american version of a Health bar, if it reaches 0 you get enslaved. " +
                        "you can also buy insurance to make it more resilient or be in debt and pay interest on it.",
                new String[]{},
                false
        );
    }

    public void addToHP(int a) {
        this.amount += a;
    }

    public void addToInsurance(int a) {
        this.insurance += a;
    }

    public void interaction() {
        printInformation();
    }

    public void printInformation() {
        System.out.println("Your finances are at " + amount + "/100, rip in advance just in case!");
        System.out.println("You have " + insurance + "% Insurance/Debt-Interest for all dmg taken!");
    }
}
