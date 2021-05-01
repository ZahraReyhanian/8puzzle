package sample;

public class BDSearch extends Search{
    private Queue forward = new Queue();
    private Queue backward = new Queue();
    private Node goal;
    protected Node backNode;

    public BDSearch(Integer[][] initialState, Integer[][] goalState){
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
    }

    @Override
    public boolean solve() {
        boolean solved = false;
        forward.enQueue(root);
        backward.enQueue(goal);
        while (!forward.isEmpty() && !backward.isEmpty()){
            System.out.println("//////////");
            if (!forward.isEmpty()){
                this.currentNode = forward.deQueue();
                exploredNodes.add(currentNode.toString());
                Node x = backward.containsSameNode(this.currentNode.toString());
                if (this.currentNode.toString().equals(this.goal.toString()) || x != null){
                    complete(x);
                    System.out.println("found!");
                    System.out.println(currentNode);
                    System.out.println(goal);
                    solved = true;
                    return solved;
                }
                for(Node neighbor : currentNode.getNeighbors())
                {
                    System.out.println("ffff");
                    if(exploredNodes.indexOf(neighbor.toString()) == -1 && forward.containsSameNode(neighbor.toString()) == null)
                    {
                        neighbor.setParent(currentNode);
                        forward.enQueue(neighbor);

                    }
                }

                System.out.println(forward.getArray());

                currentNode.setNeighbors(null);
            }

            if (!backward.isEmpty()){
                this.backNode = backward.deQueue();
                exploredNodes.add(backNode.toString());
                Node x = forward.containsSameNode(this.backNode.toString());
                if(this.backNode.toString().equals(this.root.toString()) || x != null){
                    this.currentNode = x;
                    complete(backNode);
                    System.out.println("found2!");
                    solved = true;
                    return solved;
                }

                for(Node neighbor : backNode.getNeighbors())
                {
                    if(exploredNodes.indexOf(neighbor.toString()) == -1 && backward.containsSameNode(neighbor.toString()) == null)
                    {
                        neighbor.setParent(backNode);
                        backward.enQueue(neighbor);

                    }
                }

                System.out.println(backward.getArray());

                backNode.setNeighbors(null);
            }


        }
        return solved;
    }

    private void complete(Node x) {
        while (x.getParent() != null){
            Node p = x.getParent();
            x.setParent(currentNode);
            currentNode = x;
            x = p;
        }
    }


    public Queue getForward() {
        return forward;
    }

    public void setForward(Queue forward) {
        this.forward = forward;
    }

    public Queue getBackward() {
        return backward;
    }

    public void setBackward(Queue backward) {
        this.backward = backward;
    }

    public Node getGoal() {
        return goal;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public Node getBackNode() {
        return backNode;
    }

    public void setBackNode(Node backNode) {
        this.backNode = backNode;
    }
}
