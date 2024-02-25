package data.constants;

public enum NumericType {
    NUMBER_OF_CHARACTERS_TO_DELETE(21), MAX_NUMBER_CARD_IN_DECK(36),
    MAX_SCORE_VALUE(21),MAX_NUMBER_CARD_IN_HAND(4), STARTING_MONEY(100);
    private int value;

    NumericType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
