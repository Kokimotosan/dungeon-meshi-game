package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Classe abstrata que representa o lado do Subscritor (Observer) no padrão de projeto Observer.
 * <p>
 * Qualquer classe que precise de reagir passivamente a eventos do jogo (como a passagem de turnos
 * ou a utilização de cartas) deve estender esta classe e implementar a sua própria lógica de reação
 * no método {@code beNotified}.
 * </p>
 */
public abstract class Subscriber {
    ArrayList<Publisher> pubs;
    
    /**
     * Método invocado pelo publicador sempre que um evento ocorre na batalha.
     * <p>
     * As classes filhas (como os efeitos de estado) devem implementar este método para definir 
     * o que acontece exatamente quando recebem um evento específico (por exemplo, um efeito de veneno 
     * verificar se o evento é o fim do turno para poder causar dano).
     * </p>
     *
     * @param event O evento ({@link Event}) acabado de emitir pelo publicador.
     */
    public abstract void beNotified(Event event);
}