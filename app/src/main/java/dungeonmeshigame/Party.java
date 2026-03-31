package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Representa o grupo (equipa) de heróis do jogador.
 * A equipa partilha uma reserva de energia (mana/pontos de ação) conjunta
 * para jogar as cartas durante a batalha.
 */
public class Party {
    ArrayList<Hero> members;
    int draw;
    int energy;

    /**
     * Construtor que inicializa a equipa sem membros e com energia a zero.
     */
    public Party(){
        this.members = new ArrayList<Hero>();
        this.energy = 0;
    }

    /**
     * Adiciona um herói à equipa, aumentando a reserva de energia consoante
     * o valor que o herói oferece.
     * * @param new_member O herói a ser integrado no grupo.
     */
    public void addMember(Hero new_member){
        this.members.add(new_member);
        this.energy += new_member.energy_mod;
    }

    /**
     * Exibe a energia atual e a máxima no ecrã (ex: 3/3).
     */
    public void printEnergy(){
        System.out.println("Energia: (" + this.energy + "/" + this.getMaxEnergy() + ")");
    }

    /**
     * Calcula o limite máximo de energia da equipa através da soma 
     * dos modificadores individuais de energia de cada herói.
     * * @return A energia máxima total do grupo.
     */
    int getMaxEnergy(){
        int total = 0;
        for(int i = 0; i < this.members.size(); i++)
            total += this.members.get(i).energy_mod;
        return total;
    }
}