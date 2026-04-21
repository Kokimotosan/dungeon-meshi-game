package dungeonmeshigame;

import java.util.ArrayDeque;

public class MapNode {
    private MapNode parent;
    private MapNode first_child;
    private MapNode second_child;
    private MapNode shortcut;
    private int index;
    private int depth;
    private Room room;

    public MapNode(int index){
        this.parent = null;
        this.first_child = null;
        this.second_child = null;
        this.shortcut = null;
        this.depth = 0;
        this.index = index;
        this.room = null;
    }

    public MapNode(int index, int depth, MapNode parent){
        this.parent = parent;
        this.first_child = null;
        this.second_child = null;
        this.shortcut = null;
        this.depth = depth;
        this.index = index;
        this.room = null;
    }

    public MapNode(int index, int depth, MapNode parent, MapNode child1, MapNode child2){
        this.parent = parent;
        this.first_child = child1;
        this.second_child = child2;
        this.shortcut = null;
        this.depth = depth;
        this.index = index;
        this.room = null;
    }

    public void printTree(){
        recprintTree(this, 0);
    }

    private static void recprintTree(MapNode current_node, int current_depth){
        String s = "";
        for(int i = 0; i < current_depth; i++){
            s += "| ";
        }
        System.out.println(s + "->" + current_node.getString());

        if(current_node.getFirst_child() != null){
            recprintTree(current_node.getFirst_child(), current_depth + 1);
        }
        if(current_node.getSecond_child() != null){
            recprintTree(current_node.getSecond_child(), current_depth + 1);
        }
    }

    public MapNode getParent() {
        return parent;
    }

    public int getIndex(){
        return index;
    }

    public int getDepth(){
        return depth;
    }

    public MapNode getFirst_child() {
        return first_child;
    }

    public MapNode getSecond_child() {
        return second_child;
    }

    public MapNode getShortcut() {
        return shortcut;
    }

    public Room getRoom(){
        return room;
    }

    public void setParent(MapNode parent) {
        this.parent = parent;
    }

    public void setFirst_child(MapNode first_child) {
        this.first_child = first_child;
    }

    public void setSecond_child(MapNode second_child) {
        this.second_child = second_child;
    }

    public void setShortcut(MapNode shortcut) {
        this.shortcut = shortcut;
    }

        public void setRoom(Room room) {
        this.room = room;
    }

    public String getString(){
        String s = "Sala " + this.index + ": " + this.room.getNameString();
        if(this.getShortcut() != null){
            s += " (->" + this.getShortcut().getString() + ")";
        }
        return s;
    }
    
}
