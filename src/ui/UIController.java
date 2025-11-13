package ui;

import results.*;

import java.util.List;

public record UIController(GameView gameView) {

    public void presentResult(ActionResult result) {
        switch (result.getType()) {
            case PLAYER_TURN -> System.out.println("turno player");//consoleUI.showPlayerAttack((PlayerTurnResult) result);//
            case ZOMBIE_TURN -> System.out.println("turno zombie");//consoleUI.showZombieAttack((ZombieTurnResult) result);
            case PLAYER_HEALS -> System.out.println("jugador curación");//consoleUI.showHealMessage();
            case ZOMBIE_DEFEAT -> System.out.println("zombie derrotado");//consoleUI.showZombieDefeat();
            case ZOMBIE_APPEARED -> System.out.println("zombie aparece");//consoleUI.showZombieAppeared((ZombieAppearedResult) result);
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
            case GAME_STATUS -> gameView.updateStatus((GameStatusResult)result);//consoleUI.showGameStatus((GameStatusResult)result);
        }
    }

    public void presentAllResults(List<ActionResult> results) {
        for (ActionResult result : results) {
            presentResult(result);
        }
    }
}
