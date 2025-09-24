package ui;

import actions.Action;
import game.Difficulty;
import results.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner sc;

    public ConsoleUI() {
        this.sc = new Scanner(System.in);
    }

    public Action askAction(List<Action> actions) {
        while (true) {
            showOptions("Elige una acción", actions.stream().map(Action::getLabel).toList());
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= actions.size()) {
                    clearScreen();
                    return actions.get(choice - 1);
                }
            } else {
                sc.nextLine();
            }
            showMessage("Opción no válida. Introduce un número.");
            clearScreen();
        }
    }

    public Difficulty askDifficulty(List<Difficulty> difficulties) {
        while (true) {
            showOptions("Elige dificultad", difficulties.stream().map(Difficulty::getLabel).toList());
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= difficulties.size()) {
                    clearScreen();
                    return difficulties.get(choice - 1);
                }
            } else {
                sc.nextLine();
            }
            showMessage("Opción no válida. Introduce un número.");
            clearScreen();
        }
    }

    public void showTitle() {
        clearScreen();
        showBoxInstant("Bienvenido a la mansión zombie");
        waitForKey();
    }

    public void showGameOver() {
        showBoxInstant("GAME OVER");
        waitForKey();
    }

    public void showMessage(String message) {
        showBoxTypeWriter(message.split("\n"));
        waitForKey();
    }

    public void showZombieAttack(ZombieTurnResult result) {
        showBoxTypeWriter(("Recibes " + result.getDamage() + "\nVida: " + result.getHp()).split("\n"));
        waitForKey();
    }

    public void showZombieDefeat() {
        showBoxTypeWriter(new String[]{"El zombie cae desplomado."});
        waitForKey();
    }

    public void showZombieAppeared(ZombieAppearedResult result) {
        showBoxTypeWriter(new String[]{result.getZombieNumber() + " zombie(s) hambriento(s) irrumpe(n) en la habitación"});
        waitForKey();
    }

    public void showPlayerAttack(PlayerTurnResult result) {
        showBoxTypeWriter(("Zombie recibió: " + result.getDamage() + "\nVida Zombie: " + result.getHp()).split("\n"));
        waitForKey();
    }

    public void showNewRoomMessage(AdvancedRoomResult result) {
        showBoxTypeWriter(new String[]{"Avanzas a la habitación " + result.getRoomNumber()});
        waitForKey();
    }

    public void showEscapeMessage() {
        showBoxTypeWriter(new String[]{"Has escapado"});
        waitForKey();
    }

    public void showHealMessage() {
        showBoxTypeWriter(new String[]{"Te has curado"});
        waitForKey();
    }

    public void showNoiseMessage() {
        showBoxTypeWriter(new String[]{"Upss, hiciste ruido"});
        waitForKey();
    }

    public void showNoiseIgnored() {
        showBoxTypeWriter(new String[]{"Tu ruido fue ignorado, que suerte..."});
        waitForKey();
    }

    public void showFullKitMessage() {
        showBoxTypeWriter(new String[]{"No puedes cargar con más, lo dejas donde lo encontraste"});
        waitForKey();
    }

    public void showFoundKitMessage() {
        showBoxTypeWriter(new String[]{"Encuentras un botiquín tirado por ahí"});
        waitForKey();
    }

    public void showFoundWeaponMessage() {
        showBoxTypeWriter(new String[]{"Es tu día de suerte, encuentras un arma blanca escondida"});
        waitForKey();
    }

    public void showFoundProtection() {
        showBoxTypeWriter(new String[]{"Encuentras algo precario para protegerte"});
        waitForKey();
    }

    public void showSearchMessage() {
        showBoxTypeWriter(new String[]{"Buscando..."});
        waitForKey();
    }

    public void showGameStatus(GameStatusResult result) {
        String[] lines = {
                "ESTADO ACTUAL",
                "Habitación: " + result.getCurrentRoomNumber() + "/" + result.getMaxRoomNumber(),
                "Vida del jugador: " + result.getPlayerHp() + "/" + result.getPlayerMaxHp(),
                "Ataque: " + result.getPlayerAttackPoints() + " + Armas: " + result.getPlayerNumberWeapons(),
                "Protecciones: " + result.getNumberProtections(),
                "Botiquín disponible: " + result.getHasKit(),
                "Zombies activos: " + result.getRoomActiveZombies(),
                "Búsquedas restantes: " + result.getRoomRemainingSearchAttempts()
        };
        showBoxInstant(lines);
        waitForKey();
    }

    private void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private void showBoxInstant(String message) {
        showBoxInstant(message.split("\n"));
    }

    private void showBoxInstant(String[] lines) {
        int maxLength = 0;
        for (String line : lines)
            if (line.length() > maxLength)
                maxLength = line.length();

        String border = "+" + "-".repeat(maxLength + 2) + "+";
        System.out.println(border);
        for (String line : lines) {
            System.out.println("| " + String.format("%-" + maxLength + "s", line) + " |");
        }
        System.out.println(border);
        System.out.println();
    }

    private void showBoxTypeWriter(String[] lines) {
        int maxLength = 0;
        for (String line : lines)
            if (line.length() > maxLength)
                maxLength = line.length();

        String border = "+" + "-".repeat(maxLength + 2) + "+";
        System.out.println(border);
        for (String line : lines) {
            typeWriter("| " + String.format("%-" + maxLength + "s", line) + " |");
        }
        System.out.println(border);
        System.out.println();
    }

    private void showOptions(String title, List<String> options) {
        String[] lines = new String[options.size() + 1];
        lines[0] = title;
        for (int i = 0; i < options.size(); i++) {
            lines[i + 1] = (i + 1) + ". " + options.get(i);
        }
        showBoxInstant(lines);
    }

    private void typeWriter(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    private void waitForKey() {
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
        clearScreen();
    }

    public void close() {
        sc.close();
    }
}
