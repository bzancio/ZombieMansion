package persistence;

import game.Game;
import state.GameStatusDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameSaver {
    private static final String SAVE_FILE = "latest_save.brains";
    private static final String HISTORY_FILE = "history.brains";

    public static void saveGame(Game game) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(game);
            System.out.println("Partida guardada exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    public static Game loadGame() {
        File saveFile = new File(SAVE_FILE);
        if (!saveFile.exists()) {
            System.out.println("No se encontró partida guardada.");
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            System.out.println("Partida cargada exitosamente.");
            return (Game) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }

    public static void saveSnapshot(GameStatusDTO finishedGameStatus) {
        List<GameStatusDTO> history = loadHistory();
        history.add(finishedGameStatus);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORY_FILE))) {
            oos.writeObject(history);
            System.out.println("Snapshot de partida finalizada guardado.");
        } catch (IOException e) {
            System.err.println("Error al guardar historial: " + e.getMessage());
        }
    }

    public static List<GameStatusDTO> loadHistory() {
        File historyFile = new File(HISTORY_FILE);
        if (!historyFile.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(historyFile))) {
            return (List<GameStatusDTO>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar historial, creando uno nuevo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
