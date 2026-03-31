package dungeonmeshigame;

/**
 * Enumeração dos possíveis eventos ou "Triggers" que ocorrem durante o fluxo de uma batalha.
 * <p>
 * O sistema de subscrição utiliza estes eventos para acionar os devidos efeitos, 
 * por exemplo: retirar vida através de veneno no final de um turno.
 * </p>
 */
public enum Event {
    START_HERO_TURN,
    END_HERO_TURN,
    START_ENEMY_TURN,
    END_ENEMY_TURN,
    USE_CARD,
    ENEMY_ATACK
}