package dungeonmeshigame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class MapFactory {
    private int current_index;
    
    public MapFactory(){
        this.current_index = 0;
    }

    public MapNode floorOneMap(int depth, int shortcutChance){
        MapNode root = new MapNode(0);
        MapNode left_child = new MapNode(1, 1, root);
        MapNode right_child = new MapNode(2, 1, root);

        root.setFirst_child(left_child);
        root.setSecond_child(right_child);
        
        this.current_index = 2;
        generateChildren(left_child, 1, depth);
        generateChildren(right_child, 1, depth);
        setShortcutsForAllChildren(root, shortcutChance);
        root.setRoom(new InitialRoom("Entrada"));
        setFloorOneRooms(left_child);
        setFloorOneRooms(right_child);

        return root;
    }

    private void generateChildren(MapNode current_node, int current_depth, int target_depth){
        if(current_depth == target_depth){
            return;
        }

        Random rng = new Random();
        int child_count = rng.nextInt(2) + 1;

        if(child_count == 1){
            this.current_index += 1;
            MapNode child1 = new MapNode(this.current_index, current_node.getDepth() + 1, current_node);
            current_node.setFirst_child(child1);

            generateChildren(child1, current_depth + 1, target_depth);

        } 
        else{
            this.current_index += 1;
            MapNode child1 = new MapNode(this.current_index, current_node.getDepth() + 1, current_node);
            current_node.setFirst_child(child1);
            this.current_index += 1;
            MapNode child2 = new MapNode(this.current_index, current_node.getDepth() + 1, current_node);
            current_node.setSecond_child(child2);

            generateChildren(child1, current_depth + 1, target_depth);
            generateChildren(child2, current_depth + 1, target_depth);
        }
    }

    private void setShortcutsForAllChildren(MapNode root, int shortcutChance){
        ArrayDeque<MapNode> control = new ArrayDeque<MapNode>();
        Random rng = new Random();

        control.add(root);

        while(control.size() > 0){
            MapNode currentNode = control.removeFirst();

            if(currentNode.getParent() == null){
                control.add(currentNode.getFirst_child());
                control.add(currentNode.getSecond_child());
            } 
            else if(currentNode.getFirst_child() != null && currentNode.getSecond_child() == null){ // Caso de filho único
                if(rng.nextInt(100) < shortcutChance){
                    setShortcut(root, currentNode);
                }
                
                control.add(currentNode.getFirst_child());

            }
            else if(currentNode.getSecond_child() != null){ // Caso de dois filhos
                if(rng.nextInt(100) < shortcutChance){
                    setShortcut(root, currentNode);
                }
                control.add(currentNode.getFirst_child());
                control.add(currentNode.getSecond_child());
            }
            // Se não tiver nenhum filho, nada acontece.
        }
    }

    private void setShortcut(MapNode rootNode, MapNode targetNode){
        ArrayList<MapNode> lower_nodes = findAllNodesOfDepth(rootNode, targetNode.getDepth() + 1);
        ArrayList<MapNode> non_children = new ArrayList<MapNode>();
        Random rng = new Random();

        for(int i = 0; i < lower_nodes.size(); i++){
            if(lower_nodes.get(i).getParent() != targetNode){
                non_children.add(lower_nodes.get(i));
            }
        }

        targetNode.setShortcut(non_children.get(rng.nextInt(non_children.size())));
    }

    private ArrayList<MapNode> findAllNodesOfDepth(MapNode rootNode, int target_depth){
        ArrayList<MapNode> result = new ArrayList<MapNode>();

        recFindNodesOfTargetDepth(rootNode, 0, target_depth, result);

        return result;
    }

    private void recFindNodesOfTargetDepth(MapNode currentNode, int current_depth, int target_depth, ArrayList<MapNode> node_list){
        if(current_depth == target_depth){
            node_list.add(currentNode);
            return;
        }

        if(currentNode.getFirst_child() != null){
            recFindNodesOfTargetDepth(currentNode.getFirst_child(), current_depth + 1, target_depth, node_list);
        }
        if(currentNode.getSecond_child() != null){
            recFindNodesOfTargetDepth(currentNode.getSecond_child(), current_depth + 1, target_depth, node_list);
        }
    }

    private void setFloorOneRooms(MapNode currentNode){
        currentNode.setRoom(randomFloorOneRoom());
        if(currentNode.getFirst_child() != null){
            setFloorOneRooms(currentNode.getFirst_child());
        }
        if(currentNode.getSecond_child() != null){
            setFloorOneRooms(currentNode.getSecond_child());
        }
    }

    private Room randomFloorOneRoom(){
        RoomFactory roomMaker = new RoomFactory();
        Random rng = new Random();

        if(rng.nextInt(100) < 75){
            return roomMaker.randomFloorOneBattleRoom(16);
        }
        else
        {
            return new CardRoom("Tesouro da Masmorra");
        }

    }

}
