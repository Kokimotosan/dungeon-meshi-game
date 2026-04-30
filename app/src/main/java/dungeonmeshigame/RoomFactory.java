package dungeonmeshigame;

import java.util.Random;
import java.util.ArrayList;

public class RoomFactory{


    public RoomFactory(){
        return;
    }

    public BattleRoom randomFloorOneBattleRoom(int diff_cap){
        Random rng = new Random();
        BattleRoom battle_room = new BattleRoom("Batalha", 0);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        int diff = 0;
        while((rng.nextInt(diff_cap) + 1) > diff){
            int next_enemy = rng.nextInt(2);
            if(next_enemy == 0){   
                int index = countEnemiesOfType(enemies, WalkingMushroom.class);
                WalkingMushroom new_mush = new WalkingMushroom(index);

                enemies.add(new_mush);
                diff += 2;
            }
            else if(next_enemy == 1){
                int index = countEnemiesOfType(enemies, HugeScorpion.class);
                HugeScorpion new_scorp = new HugeScorpion(index);

                enemies.add(new_scorp);
                diff += 6;
            }
        }

        battle_room.setEnemies(enemies);
        battle_room.setDifficulty(diff);
        return battle_room;
    }

    private int countEnemiesOfType(ArrayList<Enemy> enemies, Class<? extends Enemy> type){
        int index = 1;
        for(int i = 0; i < enemies.size(); i++){
            if(type.isInstance(enemies.get(i))){
                index += 1;
            }
        }
        return index;
    }
}