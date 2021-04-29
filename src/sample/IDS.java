package sample;

import java.util.ArrayList;
import java.util.List;

public class IDS {
    private Integer[][] initialState ;
    private Node root;
    private Node currentNode;
    private Integer[][] goalState ;
    private Stack fringe ;
    private List<String> exploredNodes ;
    private int depth;

    public IDS(Integer[][] initialState, Integer[][] goalState, int depth){
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
        this.root.setDepth(0);
        this.depth = depth;
    }

    public boolean solve(){
        boolean solved = false;
        for (int i = 0; i < this.depth; i++) {
            fringe = new Stack();
            exploredNodes = new ArrayList<>();
            boolean found = depth_limited_search(i);
            if (found) {
                solved = true;
                return solved;
            }
        }

        return solved;
    }

    private boolean depth_limited_search(int depth) {
        boolean solved = false;
        fringe.push(root);
        int d = 0;
        while (!fringe.isEmpty()){
            d++;
            currentNode = fringe.pop();
            exploredNodes.add(currentNode.toString());
            if(gloalReached())
            {
                solved = true;
                System.out.println("solved!!!");
                return solved;
            }
            if (currentNode.getDepth() < depth) {
                for(Node neighbor : currentNode.getNeighbors())
                {
                    if(exploredNodes.indexOf(neighbor.toString()) == -1 && fringe.containsSameNode(neighbor.toString()) == false)
                    {
                        neighbor.setParent(currentNode);
                        neighbor.setDepth(currentNode.getDepth() + 1);
                        fringe.push(neighbor);

                    }
                }
            }

            currentNode.setNeighbors(null);
        }
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

    public Stack getFringe() {
        return fringe;
    }

    public void setFringe(Stack fringe) {
        this.fringe = fringe;
    }

    public List<String> getExploredNodes() {
        return exploredNodes;
    }

    public void setExploredNodes(List<String> exploredNodes) {
        this.exploredNodes = exploredNodes;
    }
}
