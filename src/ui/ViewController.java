package ui;

import actions.Action;
import events.*;
import game.Difficulty;
import game.Game;
import state.GameStatusDTO;

import java.util.List;

public class ViewController implements MenuDelegate, GameDelegate, CombatDelegate {
    private final MenuView menuView;
    private GameView gameView;
    private CombatView combatView;
    private Game game;

    public ViewController(MenuView menuView) {
        this.menuView = menuView;
        menuView.setMenuDelegate(this);
    }

    public void handleNotification(GameNotification notification) {
        switch (notification.getType()) {
            case PLAYER_ATTACK -> System.out.println("turno player");//consoleUI.showPlayerAttack((PlayerTurnResult) result);//
            case ZOMBIE_ATTACK -> System.out.println("turno zombie");//consoleUI.showZombieAttack((ZombieTurnResult) result);
            case PLAYER_HEALS -> gameView.showDefaultEventInfo("Curación", "Te has curado");
            case ZOMBIE_DEFEAT -> gameView.showDefaultEventInfo("Zombie", "El zombie cae Desplomado");
            case ZOMBIE_SPAWN -> gameView.showZombieSpawned((ZombieSpawnInfo)notification);
            case KIT_FOUND -> gameView.showDefaultEventInfo("Kit", "Has encontrado un kit");
            case KIT_FULL -> gameView.showDefaultEventInfo("Kit", "Ya tienes un kit");
            case WEAPON_FOUND -> gameView.showDefaultEventInfo("Arma", "Has encontrado un arma");
            case PROTECTION_FOUND -> gameView.showDefaultEventInfo("Proteccion", "Has encontrado una proteccion");
            case SEARCH_NOISE -> gameView.showDefaultEventInfo("Ruido", "Upps hiciste ruido");
            case NOISE_IGNORED -> gameView.showDefaultEventInfo("Ruido ignorado", "Tu ruido fue ignorado");
            case ADVANCED_ROOM -> gameView.showAdvanceRoom((RoomAdvanceInfo)notification);
            case ESCAPED -> gameView.showDefaultEventInfo("Escapaste", "Felicidades, has sobrevivido");
            case PLAYER_LOSE -> gameView.showDefaultEventInfo("Muerte", "Ahora formas parte de la mansión");
            case PLAYER_SEARCHED -> gameView.showDefaultEventInfo("Busqueda", "Empiezas a inspeccionar la habitación");
        }
    }

    public void handleAllNotifications(List<GameNotification> results) {
        for (GameNotification result : results) {
            handleNotification(result);
        }
    }

    public void handleStatusUpdate(GameStatusDTO gameStatusDTO) {
        gameView.updateStatus(gameStatusDTO);
    }

    public void handleCombatStatusUpdate(GameStatusDTO gameStatusDTO) {
        combatView.updateStatus(gameStatusDTO);
    }

    public void handleEndGame() {
        combatView = null;
        gameView.dispose();
        menuView.setVisible(true);
    }

    @Override
    public void showGameView(Difficulty difficulty) {
        menuView.setVisible(false);
        this.gameView = new GameView(this);
        this.game = new Game(this, difficulty);
        gameView.setupWindow();
        game.start();
    }

    @Override
    public void loadGame() {

    }

    @Override
    public void viewHistory() {

    }

    @Override
    public void handleGameAction(Action action) {
        game.performAction(action);
    }

    @Override
    public void showCombatView() {
        if (this.combatView == null)
            this.combatView = new CombatView(gameView, this);
        this.combatView.setVisible(true);
    }

    @Override
    public void exitGameView() {
        handleEndGame();
    }

    @Override
    public void performCombatTurn() {
        game.performAction(Action.FIGHT);
    }

    @Override
    public void exitCombat() {
        combatView.dispose();
    }
}
