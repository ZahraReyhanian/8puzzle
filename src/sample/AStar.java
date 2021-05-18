package sample;

import java.util.ArrayList;

public class AStar extends Search {
    private Node goal;
    private ArrayList<Node> fringe ;

    public  AStar(Integer[][] initialState, Integer[][] goalState){
        super(initialState, goalState);
        int k = 0, l = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goalState[i][j] == 0){
                    k = i;
                    l = j;
                    break;
                }
            }
        }
        this.goal = new Node(k, l, goalState);
        this.root.setDepth(0);
        this.fringe = new ArrayList<Node>();
    }

    @Override
    public boolean solve() {
        boolean solved = false;
        fringe.add(root);
        while (!fringe.isEmpty()){
            currentNode = this.fringe.remove(getBestNodeIndex());
            exploredNodes.add(currentNode.toString());
            if(gloalReached())
            {
                solved = true;
                System.out.println("solved!!!");
                return solved;
            }
            for(Node neighbor : currentNode.getNeighbors())
            {
                if(exploredNodes.indexOf(neighbor.toString()) == -1 && containsSameNode(neighbor.toString()) == false)
                {
                    neighbor.setParent(currentNode);
                    neighbor.setDepth(currentNode.getDepth() + 1);
                    fringe.add(neighbor);

                }
            }

            currentNode.setNeighbors(null);
        }
        return solved;
    }

    public int h(Node node){
        int s = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (! node.getStates()[i][j].equals(goal.getStates()[i][j]) ) s++;
            }
        }
        return s;
    }

    private int getBestNodeIndex() {
        int min = 1000000;
        int minIndex = 0;
        for (int i = 0; i < this.fringe.size(); i++) {
            Node np = this.fringe.get(i);
            int h = h(np);
            if (np.getDepth() + h < min){
                min = np.getDepth() + h;
                minIndex = i;
            }
        }
        return minIndex;
    }

    public boolean containsSameNode(String string) {
        for (Node child:this.fringe) {
            if (child.toString().equals(string)) return true;
        }
        return false;
    }
}
