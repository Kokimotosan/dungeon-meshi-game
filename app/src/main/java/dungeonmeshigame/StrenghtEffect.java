package dungeonmeshigame;

/**
 * Representa o efeito de estado positivo "Força" (Strength).
 * <p>
 * Este efeito aumenta o dano causado pelas cartas de ataque do personagem 
 * que o possui. A Força permanece ativa durante um determinado número de turnos, 
 * diminuindo a sua duração no final de cada turno do herói até se dissipar.
 * </p>
 */
public class StrenghtEffect extends Effect {
    int duration;

    /**
     * Construtor do efeito de Força.
     * * @param holder O personagem (aliado) que recebe o bónus de força.
     * @param power O valor do bónus de dano concedido por este efeito.
     * @param duration O número de turnos que o bónus permanecerá ativo.
     */
    public StrenghtEffect(Character holder, int power, int duration){
        super("Força", holder, duration, power);
        this.duration = duration;
    }

    /**
     * Devolve uma representação em texto do estado atual deste efeito.
     * <p>
     * Exemplo de formatação: "Força (3) por 2 turno(s)".
     * </p>
     * * @return Uma string descrevendo o nome, a potência e a duração restante.
     */
    public String getString(){
        return (this.getName() + " (" + this.getPower() + ") por " + this.duration + " turno(s)");
    }

    /**
     * Trata da junção de efeitos iguais.
     * <p>
     * Ao contrário do Veneno, o efeito de Força atual não funde os seus valores 
     * com outros efeitos de Força já existentes; ele simplesmente devolve a sua 
     * própria instância para ser adicionada à lista de forma independente.
     * </p>
     * * @return A própria instância deste efeito.
     */
    public Effect mergeEffects(){
        return this;
    }

    /**
     * Recebe notificações do sistema de eventos do jogo.
     * <p>
     * O efeito de Força reage especificamente ao evento de fim de turno do herói 
     * ({@link Event#END_HERO_TURN}), altura em que consome um turno da sua duração.
     * </p>
     * * @param event O evento atual emitido pelo publicador da batalha.
     */
    public void beNotified(Event event){
        if(event == Event.END_HERO_TURN){
            this.apply();
        }
    }

    /**
     * Aplica a mecânica de passagem de tempo no efeito.
     * <p>
     * Reduz a duração do efeito em 1 turno. Se a duração chegar a zero ou menos, 
     * o efeito invoca o método {@code unnapply()} para se remover do personagem.
     * </p>
     */
    public void apply(){
        this.duration -= 1;
        if(this.duration <= 0){
            this.unnapply();
        }
    }    

    /**
     * Remove o efeito de Força do personagem e limpa as suas subscrições.
     * <p>
     * Retira este efeito da lista de efeitos ativos do detentor e cancela 
     * a inscrição em todos os publicadores, deixando assim de escutar a passagem dos turnos.
     * </p>
     */
    public void unnapply(){
        getHolder().effects.remove(this);
        for(int i = 0; i < this.pubs.size(); i++){
            this.pubs.get(i).unsubscribe(this);
        }
    }
}