package sample;

import java.util.ArrayList;
import java.util.List;

public class IDS extends Search{
    private Stack fringe ;
    private int depth;

    public IDS(Integer[][] initialState, Integer[][] goalState, int depth){
        super(initialState, goalState);
        this.root.setDepth(0);
        this.depth = depth;
    }

    @Override
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

    public Stack getFringe() {
        return fringe;
    }

    public void setFringe(Stack fringe) {
        this.fringe = fringe;
    }

}
