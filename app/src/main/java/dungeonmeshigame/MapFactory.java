package dungeonmeshigame;

import java.util.ArrayDeque;
import java.util.Random;

public class MapFactory {
    public int current_index;
    
    public MapFactory(){
        this.current_index = 0;
    }

    public MapNode floorOneMap(int depth){
        MapNode root = new MapNode(0);
        
        this.current_index = 0;
        generateChildren(root, 0, depth);

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
            MapNode child1 = new MapNode(this.current_index, current_node);
            current_node.setFirst_child(child1);

            generateChildren(child1, current_depth + 1, target_depth);

        } 
        else{
            this.current_index += 1;
            MapNode child1 = new MapNode(this.current_index, current_node);
            current_node.setFirst_child(child1);
            this.current_index += 1;
            MapNode child2 = new MapNode(this.current_index, current_node);
            current_node.setSecond_child(child2);

            generateChildren(child1, current_depth + 1, target_depth);
            generateChildren(child2, current_depth + 1, target_depth);
        }
    }

}
