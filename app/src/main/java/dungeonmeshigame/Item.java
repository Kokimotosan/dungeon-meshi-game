package dungeonmeshigame;

public class Item {
    String name;
    int weight;
    
    public Item(String name, int weight){
        this.name = name;
        this.weight = weight;        
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public void displaytItem(){
        System.out.println(this.name + ": Pesa " + this.weight);
    }
    
}