import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

public class App {
    // Funções de uso auxiliar
    private static final Scanner input = new Scanner(System.in);

    public static int receiveInput(){
        return input.nextInt();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Função que mostra o estado do combate
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
        Hero Laios = new Hero("Laios", 20, 0, 1);
        Enemy mushroom1 = new Enemy("Cogumelo Andarilho", 8, 0);
        Enemy mushroom2 = new Enemy("Cogumelo Andarilho", 8, 0);
        DamageCard swordCard = new DamageCard("Espada", 4, 1);
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
            
            boolean endTurn = false;
            turn = turn % 3;

            if (turn == 0){ // Turno do Herói
                System.out.println("======= Seu Turno ======");
                System.out.println("Energia: " + energy + "/" + energy_max);
                System.out.println("Suas Cartas:");
                System.out.println(swordCard.name + ": Causa " + swordCard.damage + " de Dano, Custa " + swordCard.cost + " de Energia");
                System.out.println(shieldCard.name + ": Concede " + shieldCard.shield + " de Escudo, Custa " + shieldCard.cost + " de Energia");
                
                energy = energy_max;

                // Loop de escolha de ação
                do {
                    System.out.println("===== Escolha uma ação ===== ");
                    System.err.println("Energia: " + energy + "/" + energy_max);
                    System.out.println("(1) Usar " + swordCard.name + " | Custo: " + swordCard.cost);
                    System.out.println("(2) Usar " + shieldCard.name + " | Custo: " + shieldCard.cost);
                    System.out.println("(3) Encerrar turno");
                    boolean target = false;
                    switch(receiveInput()){
                        case 1:
                            do {
                                System.out.println("===== Escolha um alvo =====");
                                System.out.println("(1) Cogumelo Andarilho " + mushroom1.health + "/" + mushroom1.max_health);
                                System.out.println("(2) Cogumelo Andarilho " + mushroom2.health + "/" + mushroom2.max_health);
                                System.out.println("(3) Retornar ao menu de ação");
                                switch (receiveInput()){
                                    case 1:
                                        mushroom1.takeDamage(swordCard.damage);
                                        System.out.println("Cogumelo Andarilho recebeu " + swordCard.damage + "de dano");
                                        energy -= swordCard.cost;
                                        target = true;
                                        break;
                                    case 2:
                                        mushroom2.takeDamage(swordCard.damage);
                                        System.out.println("Cogumelo Andarilho recebeu " + swordCard.damage + "de dano");
                                        energy -= swordCard.cost;
                                        target = true;
                                        break;
                                    case 3:
                                        target = true;
                                        break;
                                    default:
                                        clearScreen();
                                        System.out.println("Opção invalida!");
                                        break;
                                }
                            } while (!target);
                            break;
                        case 2:
                            do {
                                System.out.println("===== Escolha um alvo =====");
                                System.out.println("(1) Conceder " + shieldCard.shield + " para Laios");
                                System.out.println("(2) Retornar ao menu de ação");
                                switch (receiveInput()){
                                    case 1:
                                        Laios.gainShield(shieldCard.shield);
                                        System.out.println("Laios recebeu" + shieldCard.shield + "de escudo");
                                        energy -= shieldCard.cost;
                                        target = true;
                                        break;
                                    case 2:
                                        target = true;
                                        break;
                                    default:
                                        clearScreen();
                                        System.out.println("Opção invalida!");
                                        break;
                                }
                            } while (target);
                            break;
                        case 3:
                            endTurn = true;
                            break;
                        default:
                            System.out.println("Opção invalida!");
                            break;
                    }
                    clearScreen();
                } while (energy == 0 || endTurn);
            } else {
                mushroom1.attack(Laios, 3);
                System.out.println("Laios recebeu uma cabeçada de um Cogumelo Andarilho");
                clearScreen();
            }   
        }
    }
 }

