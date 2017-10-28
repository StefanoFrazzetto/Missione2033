package algorithms;

public class Node implements Comparable<Node> {
    private int x;
    private int y;

    private double g = Double.POSITIVE_INFINITY; // cost of the path from start to n (current node)
    private double h; // estimate cost of the cheapest path from n (current node) to the goal
    private double f = Double.POSITIVE_INFINITY; // sum of g + h

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getGScore() {
        return g;
    }

    public double getFScore() {
        return f;
    }

    public void setGScore(double gScore) {
        this.g = gScore;
    }

    public void setFScore(double fScore) {
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
    public static double distance(Node source, Node target) {
        return Math.sqrt(Math.pow(target.getX() - source.getX(), 2) + Math.pow(target.getY() - source.getY(), 2));
    }

    public static double h(Node source, Node target) {
        return distance(source, target);
    }

    public boolean equals(Node node) {
//        return this.getX() == node.getX() && this.getY() == node.getY();
        return distance(this, node) <= 0.001;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.g, o.g);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", x, y);
    }
}