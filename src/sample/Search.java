package sample;

import java.util.ArrayList;
import java.util.List;

public abstract class Search {
    protected Integer[][] initialState ;
    protected Node root;
    protected Node currentNode;
    protected Integer[][] goalState ;
    protected List<String> exploredNodes = new ArrayList<>();

    public Search(Integer[][] initialState, Integer[][] goalState){
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

    public abstract boolean solve();

    public boolean gloalReached() {
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

    public List<String> getExploredNodes() {
        return exploredNodes;
    }

    public void setExploredNodes(List<String> exploredNodes) {
        this.exploredNodes = exploredNodes;
    }
}
