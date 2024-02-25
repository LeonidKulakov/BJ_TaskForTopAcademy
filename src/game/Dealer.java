package game;

import data.cardsType.*;
import data.constants.NumericType;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class Dealer {
    //Массив используется так как на данном этапе обучения не изучен collection
    private Playable[] deck;
    //Массив используется так как на данном этапе обучения не изучен collection
    private Playable[] hand;

    /**
     * Метод описывает логику действий крупье
     *
     * @param playerScore - принимает кол-во очков набранных игроком
     * @return возвращает 0 если ничья, -1 если выиграл игрок, 1 если выиграл крупье
     */
    public int play(int playerScore) {
        if (hand == null) {
            hand = new Playable[NumericType.MAX_NUMBER_CARD_IN_HAND.getValue()];
        }
        while (calculateScore() <= playerScore && calculateScore() < NumericType.MAX_SCORE_VALUE.getValue()) {
            takeNewCard();
        }
        System.out.printf("Очки набранные крупье %d \n", calculateScore());
        if ((calculateScore() > NumericType.MAX_SCORE_VALUE.getValue() && playerScore > NumericType.MAX_SCORE_VALUE.getValue())
                || (playerScore == NumericType.MAX_SCORE_VALUE.getValue()) && (calculateScore() == NumericType.MAX_SCORE_VALUE.getValue())) {
            return 0;
        } else if (calculateScore() > NumericType.MAX_SCORE_VALUE.getValue()) {
            return -1;
        } else {
            return 1;
        }
    }

    private void takeNewCard() {
        // Использование класса Random как альтернатива Math.random()
        int index = new Random().nextInt(NumericType.MAX_NUMBER_CARD_IN_DECK.getValue() - 1);
        while (deck[index] == null) {
            index = new Random().nextInt(NumericType.MAX_NUMBER_CARD_IN_DECK.getValue() - 1);
        }
        // Проблема использования массива в необходимости поиска места для добавления элемента
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = deck[index];
                deck[index] = null;
                break;
            }
        }
    }

    private int calculateScore() {
        int sum = 0;
        for (Playable c : hand) {
            if (c == null) {
                break;
            }
            sum += c.getNominalValue();
        }
        return sum;
    }

    /**
     * Метод проводит ленивую инициализацию если массив не инициализирован
     * и заполняет массив deck элементами
     * В методе используется EnumSet для автоматизации процесса
     */
    public void takeNewDeck() {
        if (deck == null) {
            deck = new Playable[NumericType.MAX_NUMBER_CARD_IN_DECK.getValue()];
        }
        //Набор данных, который рассмотрится при изучении коллекций
        EnumSet<Clubs> clubs = EnumSet.allOf(Clubs.class);
        EnumSet<Diamonds> diamonds = EnumSet.allOf(Diamonds.class);
        EnumSet<Hearts> hearts = EnumSet.allOf(Hearts.class);
        EnumSet<Spades> spades = EnumSet.allOf(Spades.class);
        //Преобразование данных в обычный массив
        int index = 0;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0 -> {
                    for (Clubs el : clubs) {
                        deck[index++] = el;
                    }

                }
                case 1 -> {
                    for (Diamonds el : diamonds) {
                        deck[index++] = el;
                    }
                }
                case 2 -> {
                    for (Hearts el : hearts) {
                        deck[index++] = el;
                    }
                }
                case 3 -> {
                    for (Spades el : spades) {
                        deck[index++] = el;
                    }
                }
            }
        }
    }

    @Deprecated
    /**
     *   Метод не устаревший, но написан для примера, НЕ ИСПОЛЬЗОВАТЬ!!!!
     * */
    public void showHowNeedCreateNewDeck() {
        List<Playable> playables = new ArrayList<>();
        playables.addAll(EnumSet.allOf(Clubs.class));
        playables.addAll(EnumSet.allOf(Diamonds.class));
        playables.addAll(EnumSet.allOf(Hearts.class));
        playables.addAll(EnumSet.allOf(Spades.class));
        playables.forEach(System.out::println);
    }

    public void clearHand() {
        hand = new Playable[NumericType.MAX_NUMBER_CARD_IN_HAND.getValue()];
    }

    //!!!!!!!!!!!!!!!!Дальше гкттеры!!!!!!!!!!!!!!!!!!!!!!
    public Playable[] getDeck() {
        return deck;
    }

}
