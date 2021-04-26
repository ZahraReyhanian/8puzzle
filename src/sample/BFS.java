package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS {
    private Integer[][] initialState ;
    private Node root;
    private Node currentNode;
    private Integer[][] goalState ;
    private Queue fringe = new Queue();
    private List<String> exploredNodes = new ArrayList<>();

    public BFS(Integer[][] initialState, Integer[][] goalState){
        this.initialState = initialState;
        this.goalState = goalState;
        int k = 0, l = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (initialState[i][j] == 0){
                    k = i;
                    l = j;
                    break;
                }
            }
        }
        this.root = new Node(k, l, initialState);
    }

    public boolean solve(){
        boolean solved = false;
        fringe.enQueue(root);
        int k = 0;
        while (k < 100 && ! fringe.isEmpty()){
            k++;
            System.out.println("///////////////");
            currentNode = fringe.deQueue();
            exploredNodes.add(currentNode.toString());
            if(gloalReached())
            {
                solved = true;
                System.out.println("solved!!!");
                return solved;
            }

            currentNode.printState();
            System.out.println(fringe.getArray());

            System.out.println(currentNode.getNeighbors());
            for(Node neighbor : currentNode.getNeighbors())
            {
                if(exploredNodes.indexOf(neighbor.toString()) == -1 && fringe.containsSameNode(neighbor.toString()) == false)
                {
                    neighbor.setParent(currentNode);
                    fringe.enQueue(neighbor);

                }
            }
            System.out.println(fringe.getArray());
            currentNode.setNeighbors(null);
        }

        System.out.println("unsolve!!!");
        return solved;

    }

    private boolean gloalReached() {
        boolean success = true;

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(!currentNode.getStates()[i][j].equals(goalState[i][j]))
                {
                    success = false;
                    break;
                }

            }
            if(success == false)
            {
                break;
            }

        }

        return success;
    }

    public Integer[][] getInitialState() {
        return initialState;
    }

    public void setInitialState(Integer[][] initialState) {
        this.initialState = initialState;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Integer[][] getGoalState() {
        return goalState;
    }

    public void setGoalState(Integer[][] goalState) {
        this.goalState = goalState;
    }

    public Queue getFringe() {
        return fringe;
    }

    public void setFringe(Queue fringe) {
        this.fringe = fringe;
    }

}
