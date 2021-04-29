package sample;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node next;
    private Integer[][] states;
    private List<Node> neighbors ;
    private int cost = 0;
    private int i; //row number of space
    private int j; //column number of space
    private Node parent = null;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    enum Direction {
        TOP,
        Bottom,
        Right,
        Left
    }

    private Direction direction;

    public  Node(int i, int j, Integer[][] states){
        this.states = new Integer[3][3];
        this.i = i;
        this.j = j;
        this.states = states;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Integer[][] getStates() {
        return states;
    }

    public void setStates(Integer[][] states) {
        this.states = states;
    }

    public List<Node> getNeighbors() {
        neighbors = new ArrayList<>();
        getLeftNeighbor();
        getRightNeighbor();
        getTopNeighbor();
        getBottomNeighbor();

        return neighbors;
    }

    private void getRightNeighbor() {
        if (j + 1 < 3){
            Integer[][] temp = new Integer[3][3];
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    temp[k][l] = this.states[k][l];
                }
            }
            temp[i][j] = states[i][j+1];
            temp[i][j+1] = states[i][j];
            Node node = new Node(i, j + 1, temp);
            node.direction = Direction.Right;
            neighbors.add(node);
        }
    }

    private void getLeftNeighbor() {
        if (j - 1 >= 0){
            Integer[][] temp = new Integer[3][3];
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    temp[k][l] = this.states[k][l];
                }
            }
            temp[i][j] = states[i][j-1];
            temp[i][j-1] = states[i][j];
            Node node = new Node(i, j - 1, temp);
            node.direction = Direction.Left;
            neighbors.add(node);
        }
    }

    private void getBottomNeighbor() {
        if (i + 1 < 3){
            Integer[][] temp = new Integer[3][3];
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    temp[k][l] = this.states[k][l];
                }
            }
            temp[i][j] = states[i + 1][j];
            temp[i + 1][j] = states[i][j];
            Node node = new Node(i + 1, j, temp);
            node.direction = Direction.Bottom;
            neighbors.add(node);
        }
    }

    private void getTopNeighbor() {
        if (i - 1 >= 0){
            Integer[][] temp = new Integer[3][3];
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    temp[k][l] = this.states[k][l];
                }
            }
            temp[i][j] = states[i-1][j];
            temp[i-1][j] = states[i][j];
            Node node = new Node(i - 1, j, temp);
            node.direction = Direction.TOP;
            neighbors.add(node);
        }
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void printState(){
        System.out.println("Node state");
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                System.out.print(states[k][l]);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                str += this.states[k][l];
            }
        }
        return str;
    }
}
