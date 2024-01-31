package JavaExamProject_Group15.Entity.Items.PokeGuns;

import JavaExamProject_Group15.Entity.Items.ItemBase;

public class HP extends ItemBase {
    int amount = 100;
    int insurance = 0;

    public HP() {
        super(
                "Health and Defence Points",
                "its the american version of a Health bar, if it reaches 0 you get enslaved. " +
                        "you can also buy insurance to make it more resilient or be in debt and pay interest on it.",
                new String[]{"HP", "finances", "money", "insurance", "debt"},
                false
        );
    }

    public void addToHP(int a) {
        if (a<0 && insurance>0)
            a=a*(100-insurance)/100;
        this.amount += a;
        System.out.println("the HP is now at " + amount + "/100");
    }

    public void addToInsurance(int a) {
        this.insurance += a;
        System.out.println("the insurance is now at " + insurance + "%");
    }

    public boolean interaction() {
        printInformation();
        return false;
    }

    public void printInformation() {
        System.out.println("Your finances are at " + amount + "/100, rip in advance just in case!");
        System.out.println("You have " + insurance + "% Insurance/Debt-Interest for all dmg taken!");
    }

    public int getHP() {
        return amount;
    }

    public int getInsurance() {
        return insurance;
    }
}
