package algorithms;

public class Node implements Comparable<Node> {
    private int x;
    private int y;

    private int g; // cost of the path from start to n (current node)
    private Double h; // estimate cost of the cheapest path from n (current node) to the goal
    private Double f; // sum of g + h

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setGScore(int gScore) {
        this.g = gScore;
    }

    public void setFScore(Double fScore) {
        this.f = fScore;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Distance between the points.
     *
     * Heuristic cost.
     *
     * @param source
     * @param target
     * @return
     */
    public static Double h(Node source, Node target) {
        return Math.sqrt(Math.pow(target.getX() - source.getX(), 2) + Math.pow(target.getY() - source.getY(), 2));
    }

    public boolean equals(Node node) {
        return this.getX() == node.getX() && this.getY() == node.getY();
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.g, o.g);
    }
}