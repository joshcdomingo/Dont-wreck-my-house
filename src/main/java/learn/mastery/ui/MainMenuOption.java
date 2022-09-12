package learn.mastery.ui;

public enum MainMenuOption {
    EXIT(0, "Exit", false),
    VIEW_RESERVATIONS(1, "View Forages By Date", false),
    MAKE_RESERVATIONS(2, "View Items", false),
    UPDATE_RESERVATIONS(3, "View Foragers By Last Name", false),
    CANCEL_RESERVATIONS(4, "View Foragers By Last Name", false);

    private int value;
    private String message;
    private boolean hidden;

    private MainMenuOption(int value, String message, boolean hidden) {
        this.value = value;
        this.message = message;
        this.hidden = hidden;
    }

    public static MainMenuOption fromValue(int value) {
        for (MainMenuOption option : MainMenuOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public boolean isHidden() {
        return hidden;
    }
}
