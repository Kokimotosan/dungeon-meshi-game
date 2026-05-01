package dungeonmeshigame;

import java.util.HashMap;
import java.util.Scanner;

public class Inventory {
    int mxCapacity;
    int capacity;
    HashMap<Item, Integer> items;

    public Inventory(int capacity, int mxCapacity, HashMap<Item, Integer> items){
        this.mxCapacity = mxCapacity;
        this.capacity = capacity;
        this.items = items;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getName() {
        return this.capacity;
    }

    public void displayInventory(){
        System.out.println("-------- Seu Inventario --------");
        System.out.println("-------- Capacidade " + this.mxCapacity + "/" + this.capacity + " --------");
        this.items.forEach((item, quantity) -> {
            System.out.println(item.name + " (x" + quantity + ") Pesa: " + item.weight);
        });
    }

    public Item searchItem(String name){
        Item searched = null;
        boolean found = false;
        while (!found) {
            for (Item item : this.items.keySet()){
                if (item.getName().equals(name)){
                    searched = item;
                    found = true;
                    break;
                }
            }
            if (!found)
                System.out.println("Esten Item não existe no inventario, escolha outro");
        }
        return searched;
    }

    public void removeItem(){
        Scanner scan = new Scanner(System.in);
        displayInventory();
        System.out.println("Digite o Nome do Item que deseja remover");
        String name = scan.nextLine();
        Item removed = searchItem(name);
        if (this.items.getOrDefault(removed, 0) > 0)
            this.items.computeIfPresent(removed, (key, quantity) -> quantity - 1);
        else
            this.items.remove(removed);    
    }

    public void EditInventory(){
        Scanner scan = new Scanner(System.in);
        System.out.println("(0) Remova um item");
        System.out.println("(1) Adicionar item");
        int choice = scan.nextInt();
        if (choice == 0)
            this.removeItem();
        else if (choice == 1)
            return;
        else 
            System.out.println("Escolha outra opção");           
    }

    void addItem(Item nItem){
        this.items.put(nItem, this.items.getOrDefault(nItem, 0) + 1);
    }

}

