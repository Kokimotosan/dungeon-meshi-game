package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Representa o efeito de estado negativo "Veneno" (Poison).
 * <p>
 * Este efeito causa dano direto à vida do personagem (ignorando escudos) 
 * no final de cada turno. O veneno tem a capacidade de acumular (stack): 
 * se um alvo já estiver envenenado e receber mais veneno, as durações e 
 * o dano são somados num único efeito mais forte.
 * </p>
 */
public class PoisonEffect extends Effect{
    /**
     * Construtor do efeito de Veneno.
     * * @param holder O personagem (Herói ou Inimigo) que sofrerá o efeito do veneno.
     * @param stacks A duração em turnos do veneno (também usada como base para a potência/dano).
     * @param damage A quantidade de dano inicial que o veneno causará por turno.
     */
    public PoisonEffect(Character holder, int stacks, int damage){
        super("Veneno", holder, stacks, damage);
    }

    /**
     * Combina vários efeitos de veneno aplicados no mesmo alvo num só.
     * <p>
     * Se o personagem já tiver efeitos de Veneno ativos, este método soma
     * as durações (stacks), remove os efeitos antigos da lista do personagem
     * e cria um novo efeito unificado com a nova potência e duração somadas.
     * </p>
     * * @return O novo efeito de Veneno consolidado após a junção.
     */
    public Effect mergeEffects(){
        int stacks = 0;
        ArrayList<PoisonEffect> merged = new ArrayList<PoisonEffect>();
        for(int i = 0; i < this.getHolder().getEffects().size(); i++){
            if(this.getHolder().effects.get(i) instanceof PoisonEffect currentPoisonEffect){
                stacks += currentPoisonEffect.getDuration();
                merged.add(currentPoisonEffect);
            }
        }

        for(int i = 0; i < merged.size(); i++){
            merged.get(i).unnapply();
        }

        PoisonEffect neweffect = new PoisonEffect(getHolder(), stacks, stacks);
        
        getHolder().effects.add(neweffect);
        return neweffect;
    }

    /**
     * Recebe notificações do sistema de eventos do jogo.
     * <p>
     * O veneno é ativado (causando dano) especificamente no evento de 
     * fim de turno do herói ({@link Event#END_HERO_TURN}).
     * </p>
     * * @param event O evento atual emitido pelo publicador do jogo.
     */
    public void beNotified(Event event){
        if(event == Event.END_HERO_TURN){
            this.apply();
        }
    }

    /**
     * Aplica o efeito negativo diretamente ao alvo.
     * <p>
     * Subtrai o valor da potência (dano) diretamente dos pontos de vida (health) 
     * do detentor do efeito. A cada vez que é aplicado, a potência e a duração 
     * diminuem em 1. Se a duração chegar a 0, o efeito é dissipado.
     * </p>
     */
    public void apply(){
        getHolder().health -= getPower();
        this.setPower(this.getPower() - 1);
        this.setDuration(getDuration() - 1);
        if (this.getDuration() == 0)
            unnapply();
    }    

    /**
     * Remove este efeito de veneno do personagem e limpa as subscrições.
     * <p>
     * O efeito é retirado da lista de efeitos ativos do personagem e 
     * cancela a sua inscrição em todos os publicadores de eventos, para que 
     * deixe de receber notificações de passagem de turno.
     * </p>
     */
    public void unnapply(){
        getHolder().effects.remove(this);
        for(int i = 0; i < this.pubs.size(); i++){
            this.pubs.get(i).unsubscribe(this);
        }
    }
}