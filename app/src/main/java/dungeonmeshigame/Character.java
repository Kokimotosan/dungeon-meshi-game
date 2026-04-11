package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Classe abstrata que representa uma entidade no jogo (Herói ou Inimigo).
 * <p>
 * Define os atributos base partilhados por todos os personagens, como a vida,
 * o escudo e os efeitos de estado que os afetam durante a batalha.
 * </p>
 */
public abstract class Character {

    String name;
    int health;
    int max_health;
    int shield;
    ArrayList<Effect> effects;

    /**
     * Construtor base para instanciar um personagem.
     * * @param name O nome do personagem.
     * @param health A vida atual inicial (normalmente igual à max_health).
     * @param max_health A vida máxima do personagem.
     * @param start_shield O escudo inicial com que o personagem começa o combate.
     */
    public Character(String name, int health, int max_health, int start_shield){
        this.name = name;
        this.max_health = max_health;
        this.health = max_health;
        this.shield = start_shield;
        this.effects = new ArrayList<Effect>();
    }

    /**
     * Causa dano ao personagem. O dano é subtraído primeiro ao escudo e, 
     * se houver dano excedente, este é subtraído à vida.
     * * @param dmg A quantidade de dano a aplicar.
     */
    public void takeDamage(int dmg){
        int remainder = dmg;
        if (this.shield >= dmg){
            this.shield -= dmg;
        } else {
            remainder -= this.shield;
            this.shield = 0;
            this.health -= remainder;
        }
    }

    /**
     * Adiciona pontos de escudo ao personagem.
     * * @param shield A quantidade de escudo a adicionar.
     */
    public void gainShield(int shield){
        this.shield += shield;
    }
        
    /**
     * Verifica se o personagem ainda está vivo.
     * * @return true se a vida for maior que 0, false caso contrário.
     */
    public boolean isAlive(){
        if(this.health > 0){
            return true;
        }
        return false;
    }

    /**
     * Adiciona um novo efeito de estado ao personagem e regista-o no sistema de eventos.
     * * @param effect_publisher O publicador responsável pela emissão de eventos da batalha.
     * @param effect O efeito a ser aplicado (ex: Veneno, Força).
     */
    public void addEffect(Publisher effect_publisher, Effect effect){
        this.effects.add(effect);
        Effect new_effect = effect.mergeEffects();
        effect_publisher.subscribe(new_effect);    
    }

    /**
     * Cria uma representação em texto do estado atual do personagem (Vida, Escudo e Efeitos).
     * * @return Uma string formatada contendo a informação de combate do personagem.
     */
    public String healthString(){
        String r = ("(" + this.health + "/" + this.max_health + ") ");
        if (this.shield > 0)
            r += ("(" + this.shield + " de Escudo)");
        if(this.effects.size() > 0)
            r += "[";
        for(int i = 0; i < this.effects.size(); i++){
            r += this.effects.get(i).getString();
            if((i+1) < this.effects.size()){
                r += " / ";
            }
        }
        if(this.effects.size() > 0){
            r += "]";
        }
        return r;
    }

    /**
     * Obtém a lista de efeitos aplicados no personagem.
     * @return A lista de efeitos.
     */
    public ArrayList<Effect> getEffects() {
        return effects;
    }

    /**
     * Define a lista de efeitos do personagem.
     * @param effects A nova lista de efeitos.
     */
    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }
}