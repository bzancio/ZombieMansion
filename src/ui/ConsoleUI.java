package ui;

import actions.Action;
import game.Difficulty;
import results.AdvancedRoomResult;
import results.PlayerTurnResult;
import results.ZombieAppearedResult;
import results.ZombieTurnResult;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner sc;

    public ConsoleUI() {
        this.sc = new Scanner(System.in);
    }

    public void showActionOptions(List<Action> actions) {
        System.out.println("\nElige una opción:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d- %s%n", i + 1, actions.get(i).getLabel());
        }
    }

    public Action askActions(List<Action> actions) {
        while (true) {
            showActionOptions(actions);
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= 1 && choice <= actions.size()) {
                    return actions.get(choice - 1);
                } else
                    System.out.println("Opción no valida");
            } else {
                System.out.println("Introduce un número por favor.");
                sc.nextLine();
            }
        }
    }

    public void showDifficultyOptions(List<Difficulty> difficulties) {
        System.out.println("\nElige una opción:");
        for (int i = 0; i < difficulties.size(); i++) {
            System.out.printf("%d- %s%n", i + 1, difficulties.get(i).getLabel());
        }
    }

    public Difficulty askDifficulties(List<Difficulty> difficulties) {
        while (true) {
            showDifficultyOptions(difficulties);
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= 1 && choice <= difficulties.size()) {
                    return difficulties.get(choice - 1);
                } else
                    System.out.println("Opción no valida");
            } else {
                System.out.println("Introduce un número por favor.");
                sc.nextLine();
            }
        }
    }

    public void showTitle() {
        System.out.println("Bienvenido a la mansión zombie");
    }

    public void showGameOver() {
        System.out.println("Game Over");
    }

    public void showZombieAttack(ZombieTurnResult result) {
        System.out.println("Recibes " + result.getDamage());
        System.out.println("Tu vida: " + result.getHp());
    }

    public void showZombieDefeat() {
        System.out.println("El zombie cae desplomado.");
    }

    public void showZombieAppeared(ZombieAppearedResult result) {
        System.out.println(result.getZombieNumber() + " zombie(s) hambiento(s) irrumpe(n) en la habitación");
    }

    public void showPlayerAttack(PlayerTurnResult result) {
        System.out.println("Zombie recibio: " + result.getDamage());
        System.out.println("Vida Zombie: " + result.getHp());
    }

    public void showNewRoomMessage(AdvancedRoomResult result) {
        System.out.println("Avanzas a la habitación " + result.getRoomNumber());
    }

    public void showEscapeMessage() {
        System.out.println("Has escapado");
    }

    public void showHealMessage() {
        System.out.println("Te has curado");
    }

    public void showNoiseMessage() {
        System.out.println("Upss, hiciste ruido");
    }

    public void showNoiseIgnored() {
        System.out.println("Tu ruido fue ignorado, que suerte...");
    }

    public void showFullKitMessage() {
        System.out.println("No puedes cargar con mas asi que lo dejas donde lo encontraste");
    }

    public void showFoundKitMessage() {
        System.out.println("Encuentras un botiquín tirado por ahí");
    }

    public void showFoundWeaponMessage() {
        System.out.println("Es tu dia de suerte, encuentras un arma blanca escondida");
    }

    public void showFoundProtection() {
        System.out.println("Encuentras algo precario con lo que cubrirte en caso de ataque");
    }

    public void showSearchMessage() {
        System.out.println("Buscando...");
    }

    public void close() {
        sc.close();
    }
}