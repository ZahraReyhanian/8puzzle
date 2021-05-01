package sample;


public class BFS extends Search{
    private Queue fringe = new Queue();


    public BFS(Integer[][] initialState, Integer[][] goalState){
        super(initialState, goalState);
    }

    @Override
    public boolean solve(){
        boolean solved = false;
        fringe.enQueue(root);
        int k = 0;
        while (k < 1000 && ! fringe.isEmpty()){
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

            for(Node neighbor : currentNode.getNeighbors())
            {
                if(exploredNodes.indexOf(neighbor.toString()) == -1 && fringe.containsSameNode(neighbor.toString()) == null)
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


    public Queue getFringe() {
        return fringe;
    }

    public void setFringe(Queue fringe) {
        this.fringe = fringe;
    }

}
