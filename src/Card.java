public abstract class Card {
    private String name;
    private int cost;

    public Card(String name, int cost){
        this.name = name;
        this.cost = cost;
    }

    public abstract boolean useCard(Character target, Party party, Deck deck);

    public abstract void printCard();

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
