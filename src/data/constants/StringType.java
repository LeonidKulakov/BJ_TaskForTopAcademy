package data.constants;

public enum StringType {
    NOMINAL_VALUE_CARD("Номинальное значение карты");
    private String text;

    StringType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
