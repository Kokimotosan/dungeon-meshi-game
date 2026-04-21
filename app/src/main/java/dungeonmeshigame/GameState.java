package dungeonmeshigame;

public class GameState {
    private MapNode mapRoot;
    public MapNode getMapRoot() {
        return mapRoot;
    }

    public void setMapRoot(MapNode mapRoot) {
        this.mapRoot = mapRoot;
    }

    private Party party;
    private int money;
    private Deck deck;

    public GameState(Party party, int money, Deck deck, MapNode root){
        this.party = party;
        this.money = money;
        this.deck = deck;
        this.mapRoot = root;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
