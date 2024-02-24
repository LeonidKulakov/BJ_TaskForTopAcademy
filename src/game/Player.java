package game;

import cardsType.Playable;
import constants.NumericType;

import java.util.Random;

public class Player {

    private int money;
    private Playable[] hand;


    public Player() {
        this.money = 100;
    }

    public void takeNewCard(Dealer dealer) {
        if (hand == null){
            hand = new Playable[30];
        }
        // Использование класса Random как альтернатива Math.random()
        int index = new Random().nextInt(NumericType.MAX_NUMBER_CARD_IN_DECK.getValue() - 1);
        while (dealer.getDeck()[index] == null) {
            index = new Random().nextInt(NumericType.MAX_NUMBER_CARD_IN_DECK.getValue() - 1);
        }
        // Проблема использования массива в необходимости поиска места для добавления элемента
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = dealer.getDeck()[index];
                dealer.getDeck()[index] = null;
                break;
            }
        }
    }

    public int calculateScore() {
        int sum = 0;
        for (Playable c : hand) {
            if (c == null) {
                break;
            }
            sum += c.getNominalValue();
        }
        return sum;
    }
}
