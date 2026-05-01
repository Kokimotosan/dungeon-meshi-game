package dungeonmeshigame;

import java.util.ArrayList;

public abstract class Room {
    private String name;

    public Room(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }  

    public String getNameString(){
        return this.name;
    }

    /*
    Método que executa o evento associado a sala.
    É o "iniciar" pedido no enunciado.
     */
    public abstract void processRoom(MapNode current_node);

    public static MapNode pickNextRoom(MapNode root, MapNode current_node){
        App.clearScreen();
        root.printTree();
        System.out.println();
        System.out.println("Você está na Sala " + current_node.getIndex() + ". Escolha aonde deseja ir:");
        
        ArrayList<MapNode> aux = new ArrayList<MapNode>();

        if(current_node.getFirst_child() != null){
            aux.add(current_node.getFirst_child());
        }
        if(current_node.getSecond_child() != null){
            aux.add(current_node.getSecond_child());
        }
        if(current_node.getShortcut() != null){
            aux.add(current_node.getShortcut());
        }

        for(int i = 0; i < aux.size(); i++){
            System.out.println("(" + (i+1) + ") " + aux.get(i).getString());
        }

        if(aux.size() == 0){
            return null;
        }

        int choice = App.receiveInput();
        if(choice > 0 && choice <= aux.size()){
            return aux.get(choice-1);
        }
        else{
            return pickNextRoom(root, current_node);
        }
    }
}
