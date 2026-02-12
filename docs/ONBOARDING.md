# Onboarding técnico — ZombieMansion

## ¿Qué es este proyecto?
ZombieMansion es un juego de supervivencia por turnos hecho con **Java + Swing**. La app está organizada en capas simples: interfaz (`ui`), lógica del juego (`game` + `actions`), eventos (`events`) y persistencia (`persistence`).

## Estructura general

- `src/Main.java`: punto de entrada. Arranca Swing y muestra el menú principal.
- `src/ui/`: ventanas y controlador principal de UI.
  - `MenuView`, `GameView`, `CombatView`, `HistoryView`: pantallas.
  - `ViewController`: coordinación entre UI y lógica de juego.
  - `*Delegate`: contratos para desacoplar vistas del controlador.
- `src/game/`: entidades y estado principal del dominio.
  - `Game`: orquestador de partida.
  - `Player`, `Zombie`, `Room`: modelo del juego.
  - `Difficulty`, `GameState`: enums de configuración/estado global.
- `src/actions/`: comandos de turno y reglas de disponibilidad.
  - `Action`: enum de acciones disponibles.
  - `ActionFactory`: mapea `Action` → estrategia concreta.
  - `FightAction`, `SearchAction`, `HealAction`, `AdvanceAction`, `EscapeAction`.
- `src/state/GameStatusDTO.java`: snapshot del estado para pintar UI y guardar historial.
- `src/events/`: notificaciones tipadas producidas por acciones y consumidas por UI.
- `src/persistence/GameSaver.java`: guardado/carga de partida actual e historial final.
- `src/resources/images/`: fondos/imágenes de interfaz.

## Flujo importante (de extremo a extremo)

1. `Main` crea `MenuView` y `ViewController`.
2. Al iniciar partida, `ViewController` crea `GameView` y `Game`.
3. Cuando el jugador elige una acción:
   - `ViewController` llama `game.performAction(...)`.
   - `Game` usa `ActionFactory` para crear la estrategia de acción.
   - La acción ejecuta reglas y devuelve una lista de `GameNotification`.
4. `ViewController` consume notificaciones y actualiza vistas.
5. `GameStatusDTO` se regenera para refrescar estado visible y acciones disponibles.
6. Si hay victoria/derrota, `GameSaver` guarda snapshot en historial.

## ¿Está bien diseñada?

**Sí, para su contexto (proyecto académico/prototipo de juego):**
- Tiene separación razonable entre UI, dominio y persistencia.
- Usa comandos (`actions`) para encapsular reglas.
- Usa notificaciones (`events`) para reducir acoplamiento directo entre acciones y vistas.

**Pero no está “cerrada” como arquitectura de largo plazo:**
- Varias reglas del dominio están repartidas entre `Game`, `GameStatusDTO`, `ViewController` y clases de acción.
- Hay dependencias cruzadas que hacen difícil testear o extender sin tocar varias capas.

## Fallos o riesgos principales

1. **`ViewController` demasiado grande (God Object de coordinación)**
   - Hace navegación, render lógico de eventos, persistencia, control de combate y cierre de partida.
2. **Reglas duplicadas/dispersas de disponibilidad de acciones**
   - `GameStatusDTO` decide acciones disponibles, mientras `Game`/`ViewController` también condicionan flujo.
3. **Dominio acoplado a presentación vía DTO central**
   - `GameStatusDTO` mezcla estado de jugador/sala con decisiones de UX (acciones habilitadas).
4. **Persistencia frágil por serialización binaria de objetos completos**
   - Cambios de clases pueden romper compatibilidad de saves.
5. **Lógica de historial parcialmente codificada “a mano”**
   - La dificultad en histórico no representa todos los valores posibles (por ejemplo, difícil).
6. **Difícil de testear por aleatoriedad embebida**
   - `ThreadLocalRandom` está en acciones y entidad `Zombie`, sin abstracción inyectable.

## Cómo simplificar la arquitectura sin perder separación de responsabilidades

### Fase 1 (bajo riesgo, alta ganancia)
- **Extraer un `GameService`** (o `TurnService`) que concentre:
  - ejecutar acción,
  - devolver resultado de turno,
  - calcular estado de fin de juego.
- **Reducir `ViewController`** a orquestación UI pura:
  - abrir/cerrar vistas,
  - enviar acciones al servicio,
  - renderizar resultado.
- **Mover cálculo de acciones disponibles** a una clase dedicada (`ActionAvailabilityPolicy`).

### Fase 2 (mejora de mantenibilidad)
- **Introducir un `RandomProvider` inyectable** para testear reglas con seeds o dobles.
- **Cambiar persistencia a snapshot explícito** (JSON o DTO persistente) en vez de serializar `Game` completo.
- **Unificar mapeo de eventos → mensajes UI** en una tabla/mapper para achicar switches.

### Fase 3 (si el proyecto crece)
- Separar en módulos lógicos:
  - `domain` (entidades + reglas),
  - `application` (casos de uso/servicios),
  - `infrastructure` (persistencia),
  - `ui` (Swing).

## Recomendaciones de aprendizaje (orden sugerido)

1. Leer `Game`, `ActionFactory` y `GameStatusDTO` para entender el loop principal.
2. Seguir una acción completa (`SearchAction` y `FightAction`) para ver reglas + notificaciones.
3. Revisar `ViewController` para comprender cómo se traduce cada notificación en feedback visual.
4. Revisar `GameSaver` para entender límites de serialización y compatibilidad.
5. Como siguiente mejora, introducir pruebas unitarias para disponibilidad/efectos de acciones.

## Primera contribución recomendada

Implementar tests de reglas para:
- disponibilidad de `FIGHT/SEARCH/ADVANCE/ESCAPE` según estado de sala,
- resultados de `FightAction` cuando muere zombie o jugador,
- consistencia del `GameStatusDTO` tras cada acción.

Esto da seguridad antes de tocar balance, dificultad o UI.
