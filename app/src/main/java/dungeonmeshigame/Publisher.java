package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Implementa o lado do Publicador (Publisher) no padrão de projeto Observer.
 * <p>
 * Esta classe é responsável por gerir uma lista de subscritores (efeitos, turnos, etc.)
 * e notificá-los sempre que um evento importante ocorre na batalha (como o início
 * ou o fim de um turno).
 * </p>
 */
public class Publisher{
    ArrayList<Subscriber> subs;
    
    /**
     * Construtor padrão. Inicializa o publicador com uma lista vazia de subscritores.
     */
    public Publisher(){
        this.subs = new ArrayList<Subscriber>();
    }

    /**
     * Regista um novo subscritor para começar a receber notificações de eventos.
     * <p>
     * Este método estabelece uma ligação bidirecional: adiciona o subscritor à
     * lista deste publicador e adiciona este publicador à lista de publicadores do subscritor.
     * </p>
     * * @param sub O subscritor ({@link Subscriber}) a ser registado.
     * @return O próprio subscritor que acabou de ser registado.
     */
    public Subscriber subscribe(Subscriber sub){
        this.subs.add(sub);
        sub.pubs.add(this);
        return sub;
    }

    /**
     * Remove um subscritor da lista, fazendo com que deixe de receber notificações deste publicador.
     * * @param sub O subscritor ({@link Subscriber}) a ser removido.
     * @return O próprio subscritor que acabou de ser removido.
     */
    public Subscriber unsubscribe(Subscriber sub){
        this.subs.remove(sub);
        return sub;
    }

    /**
     * Notifica todos os subscritores registados de que um determinado evento ocorreu.
     * <p>
     * Percorre a lista de subscritores e invoca o método {@code beNotified(event)}
     * de cada um deles, permitindo que reajam ao evento (ex: veneno causar dano no fim do turno).
     * </p>
     * * @param event O evento ({@link Event}) que acabou de ocorrer no jogo.
     */
    public void notifySubs(Event event){
        for(int i = 0; i < this.subs.size(); i++){
            this.subs.get(i).beNotified(event);
        }
    }
}