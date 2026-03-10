import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Objetos iniciais
        Hero Laios = new Hero("Laios", 20, 0);
        Enemy mushroom1 = new Enemy("Cogumelho Andante", 8, 0);
        Enemy mushroom2 = new Enemy("Cogumelo Andante", 8, 0);
        DamageCard swordCard = new DamageCard("Espada", 3, 1);
        ShieldCard shieldCard = new ShieldCard("Escudo Pequeno", 3, 1);


        // Variaveis
        int round = 1;
        int turn = 0;
        int choice;

        // Loop do combate
        while (Laios.isAlive() || (mushroom1.isAlive() && mushroom2.isAlive())){
            // Printando a telinha da batalha
            displayBattleState(round, Laios, mushroom1, mushroom2, swordCard, shieldCard);

            turn = turn % 3;

            if (turn == 0){ // Turno do Herói
                Scanner input = new Scanner(System.in);

                System.out.println("Escolha uma ação: ");
                choice = input.nextInt();

                switch(choice){
                    case 1:
                        
                }
            }
        }
    }

    public static void displayBattleState(int round, Hero hero1, Enemy enemy1, Enemy enemy2, DamageCard dmgcard1, ShieldCard shieldcard){
        System.out.println("=====( Batalha )=====");
        System.out.println("Rodada " + round);
        System.out.println(hero1.name + "(" + hero1.health + "/" + hero1.max_health + ")");
        System.out.println("vs");
        System.out.println(enemy1.name + "(" + enemy1.health + "/" + enemy1.max_health + ")");
        System.out.println(enemy2.name + "(" + enemy2.health + "/" + enemy2.max_health + ")");
        System.out.println();
        System.out.println("Suas Cartas:");
        System.out.println("(1) " + dmgcard1.name + ": " + dmgcard1.damage + " de Dano, Custa " + dmgcard1.cost + " de Energia");
        System.out.println("(2) " + shieldcard.name + ": Gera " + shieldcard.shield + " de Escudo, Custa " + shieldcard.cost + " de Energia");
        System.out.println("(3) Encerrar turno");
        System.out.println("=====(---------)=====");

    }
}
