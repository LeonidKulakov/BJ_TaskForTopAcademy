package data.cardsType;

import data.constants.NumericType;
import data.constants.StringType;

import java.util.StringJoiner;

public enum Diamonds implements Playable {
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUINE(10), KING(10), ACE(11);
    private int nominalValue;

    Diamonds(int nominalValue) {
        this.nominalValue = nominalValue;
    }

    public int getNominalValue() {
        return nominalValue;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(this.name());
        stringJoiner.add(this.getClass().toString().substring(NumericType.NUMBER_OF_CHARACTERS_TO_DELETE.getValue()));
        stringJoiner.add(StringType.NOMINAL_VALUE_CARD.getText());
        stringJoiner.add(Integer.toString(nominalValue));
        return stringJoiner.toString();
    }
}
