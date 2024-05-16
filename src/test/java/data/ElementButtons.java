package data;

public enum ElementButtons {
    TEXT_BOX("Text Box"),
    CHECK_BOX("Check Box"),
    RADIO_BUTTON("Radio Button"),
    WEB_TABLES("Web Tables");

    public final String description;

    ElementButtons(String description) {
        this.description = description;
    }
}
