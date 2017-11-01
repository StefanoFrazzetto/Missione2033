package game;

import algorithms.*;
import game.entities.Agent;
import game.entities.Character;
import game.entities.Enemy;
import game.entities.Entity;
import game.gridobjects.Door;
import game.gridobjects.Exit;
import game.gridobjects.GridObject;

import java.io.Serializable;
import java.util.*;

/**
 * GameEngine
 *
 * @author stefano
 * @version 1.0.0
 */
public class GameEngine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The agent
     */
    private Agent agent;

    /**
     * The current level
     */
    private Level level;

    /**
     * The game status
     * <p>
     * TODO: migrate to enum
     */
    private GameStatus gameStatus;

    public GameEngine() {
        initializeGame();
    }

    public void initializeGame() {
        loadLevel("MansionLevel");
        setGameStatus(GameStatus.RUNNING);
    }

    private void checkGameStatus() {
        for (Entity entity : this.level.getEntities()) {
            if (entity instanceof Enemy) {
                if (entity.getDistance(agent) < 1) {
                    setGameStatus(GameStatus.GAME_OVER);
                }
            }
        }

        if (level.getGameGrid().getBelow(agent) instanceof Exit) {
            setGameStatus(GameStatus.VICTORY);
        }
    }

    public Level getLevel() {
        return level;
    }

    /**
     * @return the game status
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    private void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * Load the level from a txt file.
     *
     * @param levelName the file name
     */
    public void loadLevel(String levelName) {
        Objects.requireNonNull(levelName);

        level = LevelLoader.loadLevel(levelName);

        try {
            agent = level.getEntities().getAgents().get(0);
        } catch (Exception e) {
            throw new RuntimeException("No agent found!");
        }
    }

    /**
     * @return the game grid
     */
    public Grid<GridObject> getGameGrid() {
        return level.getGameGrid();
    }

    /**
     * @return the list containing the entities
     */
    public Entities getEntityList() {
        return level.getEntities();
    }

    public void handleMovement(Direction direction) {
        double x = agent.getX();
        double y = agent.getY();

        switch (direction) {
            case NORTH:
                y -= 1;
                break;
            case EAST:
                x += 1;
                break;
            case SOUTH:
                y += 1;
                break;
            case WEST:
                x -= 1;
                break;
        }

        if (isNodeFree(x, y)) {
            agent.setPosition(x, y);
            moveEnemies();
        }

        // Check the game status
        checkGameStatus();
    }

    public boolean isNodeFree(double x, double y) {
        return !this.level.getGameGrid().isBlocked((int) x, (int) y);
    }

    public boolean isNodeFree(int x, int y) {
        return !this.level.getGameGrid().isBlocked(x, y);
    }

    /**
     * 100% money back guarantee
     */
    public Set<Node> freeAdjacentNodes(Node node) {
        Set<Node> nodes = new HashSet<>();


        if (isNodeFree(node.getX() - 1, node.getY()))
            nodes.add(new Node(node.getX() - 1, node.getY()));


        if (isNodeFree(node.getX() + 1, node.getY()))
            nodes.add(new Node(node.getX() + 1, node.getY()));

        if (isNodeFree(node.getX(), node.getY() + 1))
            nodes.add(new Node(node.getX(), node.getY() + 1));

        if (isNodeFree(node.getX(), node.getY() - 1))
            nodes.add(new Node(node.getX(), node.getY() - 1));

        return nodes;
    }

    private void moveEnemies() {
        List<Enemy> enemies = getEntityList().getEnemies();

        for (Enemy enemy : enemies) {
            AStarPathFinder aStarPathFinder = new AStarPathFinder(new GameMap(this), 500, false);
            Path path = aStarPathFinder.findPath(
                    new UnitMover(),
                    (int) enemy.getX(),
                    (int) enemy.getY(),
                    (int) agent.getX(),
                    (int) agent.getY()
            );

            if (path != null) {
                Path.Step step = path.getStep(1);
                enemy.setPosition(step.getX(), step.getY());
            }
        }
    }

    public void attack(Character attacker, Direction direction) {
        Character victim = (Character) level.rayCast((int) attacker.getX(), (int) attacker.getY(), direction);
        System.out.println(victim);
        if (victim != null) {
            attacker.attack(victim);

            if (victim.getHealth() <= 0 && victim instanceof Enemy) {
                System.out.println("YOU KILLED AN ENEMY!");
                level.getEntities().remove(victim);
            }
        }

        if (victim instanceof Agent && victim.getHealth() <= 0) {
            System.out.println("GAME OVER!");
            setGameStatus(GameStatus.GAME_OVER);
        }

        moveEnemies();
    }

    /**
     * @return the agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Initialize the agent
     */
    private void initAgent() {
        this.agent = new Agent();
    }

    /**
     * Open all the doors of the same type while closing the others.
     * <p>
     * We can only have one type of door open at a time.
     *
     * @param doorType the door type
     */
    public void openDoors(char doorType) {
        getDoors().forEach(door -> {
            if (door.getDoorType() == doorType)
                door.open();
            else
                door.close();
        });
    }

    public void closeDoors(char doorType) {
        getDoors().forEach(door -> {
            if (door.getDoorType() == doorType)
                door.close();
        });
    }

    private List<Door> getDoors() {
        List<Door> doors = new ArrayList<>();

        level.getGameGrid().forEach((gridObject, x, y) -> {
            if (gridObject != null && gridObject instanceof Door) {
                doors.add((Door) gridObject);
            }
        });

        return doors;
    }
}
