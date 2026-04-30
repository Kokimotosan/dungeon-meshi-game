package dungeonmeshigame;

import java.util.Scanner;

/**
 * A classe principal responsável por inicializar e executar o jogo Dungeon Meshi.
 * <p>
 * Esta classe atua como o motor do jogo em modo texto, configurando o estado
 * inicial da partida (grupo de heróis, inimigos, deck de cartas) e gerenciando
 * o laço principal de batalha (turnos, ações, verificação de vitória/derrota).
 * </p>
 * * @author [Julio da Silva Telles RA:281275] e [Andre Storti RA:294852]
 * @version 1.4
 */
public class App {
    
    private static final Scanner input = new Scanner(System.in);

    public static int receiveInput(){
        return input.nextInt();
    }

    public static void delayPrint(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }     
    }

    /**
     * Limpa a tela do terminal do jogador para manter a interface organizada.
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch(final Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Ponto de entrada principal do programa.
     * <p>
     * Este método inicializa o herói(s), cria os inimigos,
     * constrói e embaralha o deck inicial de cartas,
     * e inicia a máquina de estados da batalha.
     * </p>
     * * @param args (não utilizado)
     * @throws Exception Caso ocorra algum erro durante a execução dos processos do sistema.
     */
    public static void main(String[] args) throws Exception {
        Party party = new Party();
        Hero Laios = new Hero("Laios", 25, 25, 0, 3);
        party.addMember(Laios);


        Deck deck = new Deck();

        for(int n = 0; n < 2; n++){
            Card strenght_card = new StrenghtCard("Força",  3, 1, 1);
            deck.cards.add(strenght_card);

            Card axe = new SwordCard("Machado do Senshi", 6, 2);
            deck.cards.add(axe);

            Card shield_card = new ShieldCard("Panela inoxídavel do Senshi", 7, 2);
            deck.cards.add(shield_card);

            Card mandragora = new PottedMandragoraCard("Mandrágora no vaso", 4, 3, 1);
            deck.cards.add(mandragora);

            Card poison_sting = new PoisonStingCard("Ferrão de Escorpião", 2, 1, 1);
            deck.cards.add(poison_sting);

            Card poison_flask = new EffectCard("Frasco de veneno", new PoisonEffect("Veneno", null, 3), 1);
            deck.cards.add(poison_flask);
        }

        Card poison_antidote = new EffectCard("Antidoto de escorpião", new PoisonEffect("Veneno", null, -3), 1);
        deck.cards.add(poison_antidote);


        for(int n = 0; n < 4; n++){
            Card sword_card = new SwordCard("Espada", 3, 1);
            deck.cards.add(sword_card);

            Card shield_card = new ShieldCard("Escudo Pequeno", 3, 1);
            deck.cards.add(shield_card);
        }

        deck.shuffleDeck();

        MapFactory mapGenerator = new MapFactory();

        MapNode mapRoot = mapGenerator.floorOneMap(2, 75);
        mapRoot.printTree();

        GameState game = new GameState(party, 0, deck, mapRoot);

        mapRoot.getRoom().processRoom(game, mapRoot);
    }

}