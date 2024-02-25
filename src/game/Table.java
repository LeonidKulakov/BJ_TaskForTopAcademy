package game;

import data.cardsType.Playable;

import java.util.Scanner;

public class Table {

    public static void startGame() {
        Dealer dealer = new Dealer();
        Player player = new Player();

        dealer.takeNewDeck();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n!!!!!!!!НОВЫЙ РАУНД!!!!!!!!!!!!\n");
            System.out.println("Играть будем? \n Будем - нажми 1 \n Не будем - любая другая цифра");
            // Упомянуть про try/catch
            if (scanner.nextInt() != 1) {
                break;
            }

            //Ставка
            System.out.printf("Ваш баланс равен %d укажите размер ставки \n", player.getMoney());
            int bet = scanner.nextInt();
            while (bet > player.getMoney()) {
                System.out.println("Сумма ставки привышает баланс, укажите меньшую сумму");
                bet = scanner.nextInt();
            }
            player.doBet(bet);

            //Игра игрока
            player.takeNewCard(dealer);
            player.takeNewCard(dealer);
            while (true) {
                if (player.getHand()[player.getHand().length - 1] != null) {
                    break;
                }
                System.out.println("У вас на руках\n");
                for (Playable el : player.getHand()) {
                    if (el == null) {
                        break;
                    }
                    System.out.println(el);
                }
                System.out.println("\nВзять еще карту - 1 \nПродолжить игру - любая другая цифра");
                if (scanner.nextInt() != 1) {
                    break;
                }
                player.takeNewCard(dealer);
            }
            int test = dealer.play(player.calculateScore());
            player.takeWinning(test, bet);

            //Сброс карт
            player.clearHand();
            dealer.clearHand();
        }
    }
}

