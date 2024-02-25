package game;

import data.cardsType.Playable;
import data.constants.NumericType;

import java.util.Random;

public class Player {

    private int money;
    private Playable[] hand;

    //При создании экземпляра игроку выдаются стартовые деньги
    public Player() {
        this.money = NumericType.STARTING_MONEY.getValue();
    }

    public void takeNewCard(Dealer dealer) {
        if (hand == null) {
            hand = new Playable[NumericType.MAX_NUMBER_CARD_IN_HAND.getValue()];
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

    public void doBet(int bet) {
        money -= bet;
    }

    public void clearHand() {
        hand = new Playable[NumericType.MAX_NUMBER_CARD_IN_HAND.getValue()];
    }

    public void takeWinning(int winning, int bet) {
        switch (winning) {
            case 0 -> {
                System.out.println("\nНичья");
                money += bet;
            }
            case 1 -> {
                System.out.println("\nИгрок проиграл");
            }
            case -1 -> {
                System.out.println("\nИгрок выиграл");
                money += (bet * 2);
            }
        }
    }

    //Дальше геттеры
    public int getMoney() {
        return money;
    }

    public Playable[] getHand() {
        return hand;
    }
}
