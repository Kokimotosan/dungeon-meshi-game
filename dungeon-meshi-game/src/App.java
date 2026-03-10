import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

public class App {
    private static final Scanner input = new Scanner(System.in);

    public static int receiveInput(){
        return input.nextInt();
    }

    public static void displayBattleState(int round, Hero hero1, Enemy enemy1, Enemy enemy2){
        System.out.println("===== Batalha =====");
        System.out.println("Rodada " + round);
        System.out.println(hero1.name + "(" + hero1.health + "/" + hero1.max_health + ")");
        System.out.println("vs");
        System.out.println(enemy1.name + "(" + enemy1.health + "/" + enemy1.max_health + ")");
        System.out.println(enemy2.name + "(" + enemy2.health + "/" + enemy2.max_health + ")");
        System.out.println();
        System.out.println("===== --------- =====");
    }
    
    public static void main(String[] args) throws Exception {
        // Objetos iniciais
        Hero Laios = new Hero("Laios", 20, 0, 2);
        Enemy mushroom1 = new Enemy("Cogumelo Andante", 8, 0);
        Enemy mushroom2 = new Enemy("Cogumelo Andante", 8, 0);
        DamageCard swordCard = new DamageCard("Espada", 3, 1);
        ShieldCard shieldCard = new ShieldCard("Escudo Pequeno", 3, 1);


        // Variaveis
        int round = 1;
        int turn = 0;
        int energy_max = 1 + Laios.energy_mod;
        int energy = energy_max;
        
        // Loop do combate
        
        while (Laios.isAlive() || (mushroom1.isAlive() && mushroom2.isAlive())){
            // Printando a telinha da batalha
            displayBattleState(round, Laios, mushroom1, mushroom2);

            turn = turn % 3;

            if (turn == 0){ // Turno do Herói
                System.out.println("======= Seu Turno ======");
                System.out.println("Energia: " + energy + "/" + energy_max);
                System.out.println("Suas Cartas:");
                System.out.println(swordCard.name + ": Causa " + swordCard.damage + " de Dano, Custa " + swordCard.cost + " de Energia");
                System.out.println(shieldCard.name + ": Concede " + shieldCard.shield + " de Escudo, Custa " + shieldCard.cost + " de Energia");
                
                System.out.println("Escolha uma ação: ");
                System.out.println("(1) Usar " + swordCard.name);
                System.out.println("(2) Usar " + shieldCard.name);
                System.out.println("(3) Encerrar turno");
                
                switch(receiveInput()){
                    case 1:
                        System.out.println("===== Escolha um alvo =====");
                        System.out.println("(1) Cogumelo Andante" + mushroom1.health + "/" + mushroom1.max_health);
                        System.out.println("(2) Cogumelo Andante" + mushroom2.health + "/" + mushroom2.max_health); 
                        switch (receiveInput()) {
                            case 1:
                                
                                break;
                        
                            default:
                                break;
                        }
                }
            }
        }
    }
}
