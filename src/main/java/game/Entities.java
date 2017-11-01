package game;

import game.entities.Agent;
import game.entities.Enemy;
import game.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class Entities extends ArrayList<Entity> {
    public List<Enemy> getEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (Entity entity : this) {
            if (entity instanceof Enemy) {
                enemies.add((Enemy) entity);
            }
        }

        return enemies;
    }

    public List<Agent> getAgents(){
        List<Agent> agents = new ArrayList<>();
        for (Entity entity : this) {
            if (entity instanceof Agent) {
                agents.add((Agent) entity);
            }
        }

        return agents;
    }
}
