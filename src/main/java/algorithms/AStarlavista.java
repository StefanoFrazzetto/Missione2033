package algorithms;

import gameobjects.GameEngine;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * AStarlavista
 *
 * @author stefano
 * @version 1.0.0
 */
public class AStarlavista {
    private Set<Node> closedSet;

    private Map<Node, Node> cameFrom;

    private PriorityQueue<Node> openSet;

    private int gScore;

    private Double fScore;

    public Set<Node> AStarlavista(GameEngine gameEngine, Node source, Node goal) {
        gScore = Integer.MAX_VALUE;
        fScore = Double.POSITIVE_INFINITY;

        Node current = source;
        openSet.add(current);

        current.setGScore(0D);
        current.setFScore(Node.h(source, goal));

        while (!openSet.isEmpty()) {
            current = openSet.poll();
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            Set<Node> neighbours = gameEngine.freeAdjacentNodes(current);
            for (Node neighbour : neighbours) {
                if (closedSet.contains(neighbour))
                    continue; // node already evaluated


                if (!openSet.contains(neighbour))
                    openSet.add(neighbour); // new node

                double tentativeGScore = current.getGScore() + Node.distance(current, neighbour);
                if (tentativeGScore >= neighbour.getGScore())
                    continue; // not a better path

                cameFrom.put(neighbour, current);
                neighbour.setGScore(tentativeGScore);
                neighbour.setFScore(neighbour.getGScore() + Node.h(neighbour, goal));
            }
        }

        return null; // falliu, minchia!
    }

    public Set<Node> reconstructPath(Map<Node, Node> path, Node current){
        Set<Node> totalPath = new LinkedHashSet<>();
        totalPath.add(current);

        Set<Node> keys = path.keySet();
        for (Node nodeInPath : keys) {
            nodeInPath = path.get(nodeInPath);
            totalPath.add(nodeInPath); // boooh
        }

        return totalPath;
    }
}
