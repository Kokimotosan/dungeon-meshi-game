package dungeonmeshigame;

/**
 * Representa um Herói controlado pelo jogador na batalha, extendendo {@link Character}.
 * Heróis contribuem com energia para o grupo (party), permitindo a utilização de cartas.
 */
public class Hero extends Character {
    /** * Modificador de energia gerada pelo herói. Quantidade de energia 
     * que este herói contribui para a "pool" (reservatório) total do grupo por turno.
     */
    public int energy_mod;

    /**
     * Construtor do herói.
     * * @param name O nome do herói.
     * @param health Vida atual inicial.
     * @param max_health Vida máxima.
     * @param start_shield Escudo inicial do combate.
     * @param energy_mod A energia que ele adiciona ao grupo.
     */
    public Hero(String name, int health, int max_health, int start_shield, int energy_mod){
        super(name, health, max_health, start_shield);
        this.energy_mod = energy_mod;
    }
}