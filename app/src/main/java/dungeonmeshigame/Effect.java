package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Classe abstrata que serve de base para os efeitos (buffs e debuffs).
 * <p>
 * Extende {@link Subscriber} para poder reagir a eventos (ex: início do turno),
 * aplicando os seus efeitos num personagem (holder).
 * </p>
 */
public abstract class Effect extends Subscriber{
    private String name;
    private Character holder;
    private int duration;
    private int power;

    /**
     * Construtor do efeito base.
     * * @param name O nome do efeito (ex: "Veneno").
     * @param holder O personagem que está sob a influência do efeito.
     * @param duration A duração em turnos do efeito.
     * @param power A potência/força do efeito.
     */
    public Effect(String name, Character holder, int duration, int power){
        this.name = name;
        this.holder = holder;
        this.duration = duration;
        this.power = power;
        this.pubs = new ArrayList<Publisher>();
    }

    /**
     * Método invocado pelo sistema de notificação quando um evento ocorre.
     * Aciona a aplicação prática do efeito.
     */
    public void beNotified(){
        this.apply();
    }

    /**
     * Aplica a mecânica específica do efeito no personagem (a ser implementado nas subclasses).
     */
    public abstract void apply();

    /**
     * Tenta juntar o efeito com um efeito igual já existente no alvo, 
     * normalmente somando a duração e/ou a potência (a ser implementado nas subclasses).
     * * @return A instância do efeito resultante após a junção.
     */
    public abstract Effect mergeEffects();

    /**
     * Devolve a representação textual do efeito e a sua duração.
     * * @return String formatada (ex: "Veneno (3)").
     */
    public String getString(){
        return (this.name + " (" + this.duration + ")");
    }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getPower(){ return power; }
    public void setPower(int power){ this.power = power; }   

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Character getHolder() { return holder; }
    public void setHolder(Character holder) { this.holder = holder; }
}