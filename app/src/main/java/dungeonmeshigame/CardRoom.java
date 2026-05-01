package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CardRoom extends Room {
    private Card loot;

    public CardRoom(String name){
        super(name);
        Random rng = new Random();
        this.loot = CardCatalog.catalog.get(rng.nextInt(CardCatalog.catalog.size()));
    }

    public void processRoom(MapNode current_node) {
        Random rng = new Random();
        Scanner scan = new Scanner(System.in);

        App.clearScreen();
        
        System.out.println("Você encontra um tesoura da masmorra: [" + loot.getName() + "]");
        loot.printCard();

        System.out.println("Adicionar ao seu deck?");
        System.out.println("(1) Sim");
        System.out.println("(2) Não");
        int choice = scan.nextInt();
        scan.nextLine();
        if(choice == 1){
            System.out.println("[" + loot.getName() + "] Adicionado ao deck!");
            System.out.println("Aperte enter para continuar.");
            scan.nextLine();
            MapNode next_room = pickNextRoom(GameState.getInstance().getMapRoot(), current_node);
            if (next_room == null){
                System.out.println("Você chegou ao final da masmorra!");
                return;
            }
            next_room.getRoom().processRoom(next_room);
        }
        else if(choice == 2){
            System.out.println("Você deixa o tesouro encontrado para tras.");
            System.out.println("Aperte enter para continuar");
            scan.nextLine();
            MapNode next_room = pickNextRoom(GameState.getInstance().getMapRoot(), current_node);
            if (next_room == null){
                System.out.println("Você chegou ao final da masmorra!");
                return;
            }
            next_room.getRoom().processRoom(next_room);
        }
        else{
            processRoom(current_node);
        }
    }
}
