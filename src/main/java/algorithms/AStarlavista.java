package algorithms;

import gameobjects.GameEngine;

import java.util.*;

/**
 * AStarlavista
 *
 * @author stefano
 * @version 1.0.0
 */
abstract public class AStarlavista {

    public static Set<Node> AStarlavista(GameEngine gameEngine, Node source, Node goal) {
        Set<Node> closedSet = new HashSet<>();
        Map<Node, Node> cameFrom = new HashMap<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>();

        Node current = source;
        openSet.add(current);

        current.setGScore(0D);
        current.setFScore(Node.h(source, goal));

        while (!openSet.isEmpty()) {
            current = openSet.poll();
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

//            openSet.remove(current);
            closedSet.add(current);

            Set<Node> neighbours = gameEngine.freeAdjacentNodes(current);
            for (Node neighbour : neighbours) {
                if (closedSet.contains(neighbour))
                    continue; // node already evaluated


                if (!openSet.contains(neighbour))
                    openSet.add(neighbour); // new node

                double tentativeGScore = current.getGScore() + Node.distance(current, neighbour);
                System.out.println("G SCORE: " + neighbour.getGScore());
                if (tentativeGScore >= neighbour.getGScore())
                    continue; // not a better path

                cameFrom.put(neighbour, current);
                neighbour.setGScore(tentativeGScore);
                neighbour.setFScore(neighbour.getGScore() + Node.h(neighbour, goal));
            }
        }

        return null; // falliu, minchia!
    }

    public static Set<Node> reconstructPath(Map<Node, Node> path, Node current){
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
