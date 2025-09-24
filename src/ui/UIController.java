package ui;

import results.*;
import game.Game;
import game.Player;
import game.Room;

import java.util.List;

public record UIController(ConsoleUI consoleUI) {

    public void presentAllResults(List<ActionResult> results) {
        for (ActionResult result : results) {
            switch (result.getType()) {
                case PLAYER_TURN -> consoleUI.showPlayerAttack((PlayerTurnResult) result);
                case ZOMBIE_TURN -> consoleUI.showZombieAttack((ZombieTurnResult) result);
                case PLAYER_HEALS -> consoleUI.showHealMessage();
                case ZOMBIE_DEFEAT -> consoleUI.showZombieDefeat();
                case ZOMBIE_APPEARED -> consoleUI.showZombieAppeared((ZombieAppearedResult) result);
                case KIT_FOUND -> consoleUI.showFoundKitMessage();
                case KIT_FULL -> consoleUI.showFullKitMessage();
                case WEAPON_FOUND -> consoleUI.showFoundWeaponMessage();
                case PROTECTION_FOUND -> consoleUI.showFoundProtection();
                case SEARCH_NOISE -> consoleUI.showNoiseMessage();
                case NOISE_IGNORED -> consoleUI.showNoiseIgnored();
                case ADVANCED_ROOM -> consoleUI.showNewRoomMessage((AdvancedRoomResult)result);
                case ESCAPED -> consoleUI.showEscapeMessage();
                case PLAYER_LOSE -> consoleUI.showGameOver();
                case PLAYER_SEARCHED -> consoleUI.showSearchMessage();
            }
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
        System.out.println("Búsquedas restantes en la habitación: " + r.getRemainingSearchAttempts());
        System.out.println("=======================\n");
    }

}
