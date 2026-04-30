package dungeonmeshigame;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    int mxCapacity;
    int capacity;
    Map<Item, Integer> items;

    public Inventory(int capacity, int mxCapacity, Map<Item, Integer> items){
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
        for (Item item : this.items){
            item.displaytItem();
        }
    }

    public void removeItem(){
        
    }

    public void editInventory(){

    }

    void addItem(Item nItem){
        
    }

}

