package ui;

import events.*;
import state.GameStatusDTO;

import java.util.List;

public record ViewController(GameView gameView) {

    public void handleNotification(GameNotification notification) {
        switch (notification.getType()) {
            case PLAYER_ATTACK -> System.out.println("turno player");//consoleUI.showPlayerAttack((PlayerTurnResult) result);//
            case ZOMBIE_ATTACK -> System.out.println("turno zombie");//consoleUI.showZombieAttack((ZombieTurnResult) result);
            case PLAYER_HEALS -> System.out.println("jugador curación");//consoleUI.showHealMessage();
            case ZOMBIE_DEFEAT -> System.out.println("zombie derrotado");//consoleUI.showZombieDefeat();
            case ZOMBIE_SPAWN -> System.out.println("zombie aparece");//consoleUI.showZombieAppeared((ZombieAppearedResult) result);
            case KIT_FOUND -> System.out.println("kit encontrado");//consoleUI.showFoundKitMessage();
            case KIT_FULL -> System.out.println("kit lleno");//consoleUI.showFullKitMessage();
            case WEAPON_FOUND -> System.out.println("arma encontrada");//consoleUI.showFoundWeaponMessage();
            case PROTECTION_FOUND -> System.out.println("proteccion encontrada");//consoleUI.showFoundProtection();
            case SEARCH_NOISE -> System.out.println("ruido al buscar");//consoleUI.showNoiseMessage();
            case NOISE_IGNORED -> System.out.println("ruido ignorado");//consoleUI.showNoiseIgnored();
            case ADVANCED_ROOM -> System.out.println("avance habitación");//consoleUI.showNewRoomMessage((AdvancedRoomResult)result);
            case ESCAPED -> System.out.println("escape");//consoleUI.showEscapeMessage();
            case PLAYER_LOSE -> System.out.println("derrota");//consoleUI.showGameOver();
            case PLAYER_SEARCHED -> System.out.println("busqueda");//consoleUI.showSearchMessage();
            case GAME_STATUS -> gameView.updateStatus((GameStatusDTO)notification);//consoleUI.showGameStatus((GameStatusResult)result);
        }
    }

    public void handleAllNotifications(List<GameNotification> results) {
        for (GameNotification result : results) {
            handleNotification(result);
        }
    }
}
