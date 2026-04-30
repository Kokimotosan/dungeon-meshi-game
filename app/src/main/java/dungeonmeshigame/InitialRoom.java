package dungeonmeshigame;

public class InitialRoom  extends Room{

    public InitialRoom(String name) {
        super(name);
    }
    
    public void processRoom(GameState game, MapNode currentNode){
        MapNode next_node = pickNextRoom(currentNode, currentNode);
        next_node.getRoom().processRoom(game, next_node);
    }
}
