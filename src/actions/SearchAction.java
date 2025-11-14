package actions;

import events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class SearchAction implements ActionStrategy {
    private final Consumer<Integer> setRemainingSearchAttempts;
    private final Consumer<Boolean> setPlayerHasKit;
    private final Consumer<Integer> setPlayerNumberProtections;
    private final Consumer<Integer> setPlayerNumberWeapons;
    private final BiConsumer<Integer, Integer> addZombieFunction;
    private final Consumer<Integer> setActiveZombiesFunction;
    private final boolean playerHasKit;
    private final int remainingSearchAttempts;
    private final int playerNumberProtections;
    private final int playerNumberWeapons;
    private final int roomActiveZombies;
    private final int currentRoomNumber;

    public SearchAction(Consumer<Integer> setRemainingSearchAttempts,
                        Consumer<Boolean> setPlayerHasKit,
                        Consumer<Integer> setPlayerNumberProtections,
                        Consumer<Integer> setPlayerNumberWeapons,
                        BiConsumer<Integer, Integer> addZombieFunction,
                        Consumer<Integer> setActiveZombiesFunction,
                        boolean playerHasKit,
                        int remainingSearchAttempts,
                        int playerNumberProtections,
                        int playerNumberWeapons,
                        int roomActiveZombies,
                        int currentRoomNumber
    ) {
        this.setRemainingSearchAttempts = setRemainingSearchAttempts;
        this.setPlayerHasKit = setPlayerHasKit;
        this.setPlayerNumberProtections = setPlayerNumberProtections;
        this.setPlayerNumberWeapons = setPlayerNumberWeapons;
        this.addZombieFunction = addZombieFunction;
        this.setActiveZombiesFunction = setActiveZombiesFunction;
        this.playerHasKit = playerHasKit;
        this.remainingSearchAttempts = remainingSearchAttempts;
        this.playerNumberProtections = playerNumberProtections;
        this.playerNumberWeapons = playerNumberWeapons;
        this.roomActiveZombies = roomActiveZombies;
        this.currentRoomNumber = currentRoomNumber;
    }

    public static boolean isAvailable(boolean roomHasActiveZombies, int remainingSearchAttempts) {
        return !roomHasActiveZombies && remainingSearchAttempts != 0;
    }

    @Override
    public List<GameNotification> execute() {
        setRemainingSearchAttempts.accept(remainingSearchAttempts - 1);
        List<GameNotification> results = new ArrayList<>();
        results.add(new DefaultEventInfo(GameNotification.NotificationType.PLAYER_SEARCHED));
        double mainRoll = ThreadLocalRandom.current().nextDouble(0, 100);

        if (mainRoll <= 75) {
            results.add(new DefaultEventInfo(GameNotification.NotificationType.SEARCH_NOISE));
            results.add(noiseResult());
        } else if (mainRoll <= 90) {
            results.add(new DefaultEventInfo(GameNotification.NotificationType.KIT_FOUND));
            if (playerHasKit) {
                results.add(new DefaultEventInfo(GameNotification.NotificationType.KIT_FULL));
            } else
                setPlayerHasKit.accept(true);
        } else if (mainRoll <= 95) {
            setPlayerNumberProtections.accept(playerNumberProtections + 1);
            results.add(new DefaultEventInfo(GameNotification.NotificationType.PROTECTION_FOUND));
        } else {
            setPlayerNumberWeapons.accept(playerNumberWeapons + 1);
            results.add(new DefaultEventInfo(GameNotification.NotificationType.WEAPON_FOUND));
        }
        return results;
    }

    private GameNotification noiseResult() {
        double noiseRoll = ThreadLocalRandom.current().nextDouble(0, 100);
        if (noiseRoll <= 40)
            return new DefaultEventInfo(GameNotification.NotificationType.NOISE_IGNORED);

        // La lógica de añadir zombis debe estar aquí
        if (noiseRoll <= 80) {
            // Añadir un zombie (usando la función inyectada BiConsumer<Integer, Integer>)
            addZombieFunction.accept(1, currentRoomNumber);
            // Actualizar el conteo de zombis activos (usando la función inyectada Consumer<Integer>)
            setActiveZombiesFunction.accept(roomActiveZombies + 1);
            return new ZombieSpawnInfo(1);
        } else {
            // Añadir dos zombies
            addZombieFunction.accept(2, currentRoomNumber);
            setActiveZombiesFunction.accept(roomActiveZombies + 2);
            return new ZombieSpawnInfo(2);
        }
    }
}