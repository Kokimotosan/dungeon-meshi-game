package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Representa o estado atual de um combate no jogo.
 * <p>
 * Esta classe atua como o "tabuleiro" da batalha, controlando quem está lutando,
 * a ordem de iniciativa (turnos e rodadas), as cartas na mão do jogador e o deck.
 * Ela também verifica as condições de fim de jogo (se os heróis ou inimigos foram derrotados).
 * </p>
 */
public class BattleState{
    Party party;
    ArrayList<Enemy> enemies;
    ArrayList<Character> initiative;
    Deck deck;
    ArrayList<Card> hand;
    int round;
    int turn;
    Publisher publisher;

    /**
     * Constrói um novo estado de batalha inicializando os participantes e as cartas.
     * <p>
     * O construtor define a rodada como 1, o turno como 0, constrói a lista de iniciativa
     * juntando os heróis e os inimigos, e prepara o publicador de eventos.
     * </p>
     * * @param party O grupo de heróis ({@link Party}) que vai lutar.
     * @param enemies A lista de inimigos ({@link Enemy}) que enfrentarão o grupo.
     * @param deck O baralho ({@link Deck}) de onde as cartas serão compradas.
     */
    public BattleState(Party party, ArrayList<Enemy> enemies, Deck deck){
        this.party = party;
        this.enemies = enemies;
        this.round = 1;
        this.turn = 0;
        this.initiative = new ArrayList<Character>();
        this.initiative.addAll(party.members);
        this.initiative.addAll(enemies);
        this.deck = deck;
        this.hand = new ArrayList<Card>();
        this.publisher = new Publisher();
    }

    /**
     * Verifica se a batalha chegou ao fim.
     * <p>
     * A batalha termina se todos os heróis do grupo estiverem mortos, 
     * ou se todos os inimigos da lista estiverem mortos.
     * </p>
     * * @return true se a batalha acabou (vitória ou derrota), false se o combate deve continuar.
     */
    public boolean isOver(){
        boolean one_hero_alive = false;
        boolean one_enemy_alive = false;
        for(Character chara:this.party.members){
            if(chara.isAlive()){
                one_hero_alive = true;
                break;
            }
        }
        if (!one_hero_alive)
            return true;

        for(Character chara:this.enemies){
            if(chara.isAlive()){
                one_enemy_alive = true;
                break;
            }
        }
        
        if(one_enemy_alive)
            return false;
        else
            return true;
    }

    /**
     * Imprime no console a lista de inimigos disponíveis na batalha com seus respectivos
     * índices, para que o jogador possa escolher um alvo.
     * * @param enemies A lista de inimigos (nota: o método utiliza a lista interna da classe para a exibição).
     */
    public void selectEnemies(ArrayList<Enemy> enemies){
        for(int i = 0; i < this.enemies.size(); i++)
            System.out.println("("+ (i + 1) + ")" + " " + this.enemies.get(i).name + " " + this.enemies.get(i).healthString());
    }

    /**
     * Determina qual personagem (herói ou inimigo) deve agir no turno atual.
     * * @return O objeto {@link Character} correspondente ao personagem que tem o turno atual.
     */
    public Character getTurnCharacter(){
        int counter = 0;
        Character current;
        current = this.party.members.get(0);
        while(counter < this.initiative.size() && counter <= turn){
            current = this.initiative.get(counter);
            counter++;
        }
        return current;
    }

    /**
     * Calcula o número total de turnos necessários para completar uma rodada inteira.
     * * @return A soma da quantidade de heróis e inimigos na batalha.
     */
    public int getTurnLoop(){
        return this.party.members.size() + this.enemies.size();
    }

    /**
     * Descarta todas as cartas atualmente na mão do jogador.
     * <p>
     * As cartas são movidas da lista {@code hand} para a pilha de descarte do {@code deck},
     * e a mão é limpa em seguida.
     * </p>
     */
    public void discardHand(){
        for(int i = 0; i < hand.size(); i++){
            this.deck.discard_pile.add(this.hand.get(i));
        }
        this.hand.clear();
    }

    /**
     * Imprime no console o cabeçalho e a situação atual da batalha.
     * <p>
     * Exibe a rodada atual e a barra de vida/status de todos os heróis e inimigos 
     * que ainda estão vivos.
     * </p>
     */
    public void printBattleState(){
        System.out.println("===== Batalha =====");
        System.out.println("Rodada " + round);
        for(int i = 0; i < this.party.members.size(); i++)
            if (this.party.members.get(i).isAlive())
                System.out.println(this.party.members.get(i).name + " " + this.party.members.get(i).healthString());
        System.out.println("- vs -");
        for(int i = 0; i < this.enemies.size(); i++)
            if (this.enemies.get(i).isAlive())
                System.out.println(this.enemies.get(i).name + " " + this.enemies.get(i).healthString());
        System.out.println("===== --------- =====");
        System.out.println();
    }

    /**
     * Imprime no console as cartas que estão atualmente na mão do jogador.
     * Aciona o método de impressão individual de cada carta para detalhar seus efeitos.
     */
    public void printHand(){
        System.out.println("===== Sua mão =====");
        for(int i = 0; i < this.hand.size(); i++){
            this.hand.get(i).printCard();
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Encerra o turno do personagem atual e passa a vez para o próximo na iniciativa.
     * <p>
     * Incrementa o contador de turnos de forma modular. Se o contador retornar a zero,
     * significa que todos agiram e a rodada ({@code round}) é incrementada.
     * </p>
     */
    public void passTurn(){
        this.turn = (this.turn + 1) % this.getTurnLoop();
        if(this.turn == 0){
            this.round += 1;
        }
    }
}