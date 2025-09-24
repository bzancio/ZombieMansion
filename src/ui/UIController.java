package ui;

import actions.ActionResult;
import game.Game;
import game.Player;
import game.Room;

import java.util.List;

public record UIController(ConsoleUI consoleUI) {

    public void presentAllResults(List<ActionResult> results) {
        for (ActionResult result : results) {
            presentResult(result);
        }
    }

    public void presentResult(ActionResult result) {
        switch (result.type()) {
            case PLAYER_HEALS:
                consoleUI.showHealMessage();
                break;
            case PLAYER_TURN:
                consoleUI.showPlayerAttack(result.data1(), result.data2());
                break;
            case PLAYER_SEARCHED:
                consoleUI.showSearchMessage();
                break;
            case ZOMBIE_TURN:
                consoleUI.showZombieAttack(result.data1(), result.data2());
                break;
            case ZOMBIE_DEFEAT:
                consoleUI.showZombieDefeat();
                break;
            case ZOMBIE_APPEARED:
                consoleUI.showZombieAppeared(result.data1());
                break;
            case KIT_FOUND:
                consoleUI.showFoundKitMessage();
                break;
            case KIT_FULL:
                consoleUI.showFullKitMessage();
                break;
            case WEAPON_FOUND:
                consoleUI.showFoundWeaponMessage();
                break;
            case PROTECTION_FOUND:
                consoleUI.showFoundProtection();
                break;
            case SEARCH_NOISE:
                consoleUI.showNoiseMessage();
                break;
            case NOISE_IGNORED:
                consoleUI.showNoiseIgnored();
                break;
            case ADVANCED_ROOM:
                consoleUI.showNewRoomMessage(result.data1());
                break;
            case ESCAPED:
                consoleUI.showEscapeMessage();
                break;
            case PLAYER_LOSE:
                consoleUI.showGameOver();
                break;
        }
    }

    public void showTurnStatus(Game game) {
        Player p = game.getPlayer();
        Room r = game.getCurrentRoom();
        System.out.println("\n==== Estado actual ====");
        System.out.println("Habitación: " + game.getCurrentRoomNumber() + "/" + game.getDifficulty().getRoomNumber());
        System.out.println("Vida del jugador: " + p.getHp());
        System.out.println("Ataque: " + p.getAttackPoints() + " + Armas: " + p.getNumberWeapons());
        System.out.println("Protecciones: " + p.getNumberProtections());
        System.out.println("Botiquín disponible: " + (p.getHasKit() ? "Sí" : "No"));
        System.out.println("Zombies activos en la habitación: " + r.getActiveZombies());
        System.out.println("Búsquedas restantes en la habitación: " + r.getRemainingSearchAttemps());
        System.out.println("=======================\n");
    }

}
