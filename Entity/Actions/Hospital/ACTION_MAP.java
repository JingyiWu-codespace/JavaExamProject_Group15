package JavaExamProject_Group15.Entity.Actions.Hospital;

import JavaExamProject_Group15.Entity.Actions.ActionBase;

public class ACTION_MAP extends ActionBase {
    public ACTION_MAP() {
        super(
                "map",
                "the layout of hospital",
                new String[]{"maps"}
        );
    }

    private void executeAction() {
        System.out.println(
                " --- floor 0 --- " +
                        "\n  - 'reception desk -> leads to'" +
                        "\n          - 'elevator'" +
                        "\n          - 'doctor's office'" +
                        "\n          - 'pharmacy'" +
                        "\n          - 'emergency room -> leads to'" +
                        "\n                  - 'ER storage room'"
        );
        System.out.println(
                "\n --- floor 1 --- " +
                        "\n  - 'hallway -> leads to'" +
                        "\n          - 'elevator'" +
                        "\n          - 'ward'" +
                        "\n          - 'ultrasound'" +
                        "\n          - 'lab'"
        );
        System.out.println(
                "\n --- floor 2 --- " +
                        "\n  - 'surgery room'"
        );
    }
}
