package dungeonmeshigame;

public class GameState {

    private static GameState instance;
    private MapNode mapRoot;
    private Party party;
    private Deck deck;

    public MapNode getMapRoot() {
        return instance.mapRoot;
    }

    public void setMapRoot(MapNode mapRoot) {
        instance.mapRoot = mapRoot;
    }

    private GameState(Party party, Deck deck, MapNode root){
        this.party = party;
        this.deck = deck;
        this.mapRoot = root;
    }

    public static void createInitialInstance(Party party, Deck deck, MapNode root){
        instance = new GameState(party, deck, root);
    }

    public static GameState getInstance(){
        if(instance == null){
            createInitialInstance(null, null, null);
        }

        return instance;
    }

    public Party getParty() {
        return instance.party;
    }

    public void setParty(Party party) {
        instance.party = party;
    }

    public Deck getDeck() {
        return instance.deck;
    }

    public void setDeck(Deck deck) {
        instance.deck = deck;
    }
}
