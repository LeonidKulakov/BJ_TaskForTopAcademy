package constants;

public enum NumericType {
    NUMBER_OF_CHARACTERS_TO_DELETE(16);
    private int value;

    NumericType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
