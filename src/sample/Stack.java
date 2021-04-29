package sample;

import java.util.LinkedList;

public class Stack {
    private LinkedList<Node> array;
    private Node rear = null;
    private int number = 0;

    public Stack(){
        this.array = new LinkedList<>();
    }

    public void push(Node node){
        number ++;
        this.array.add(node);
        rear = node;
    }

    public Node pop(){
        number --;
        rear = this.array.get(number);
        return this.array.pop();
    }

    public boolean contains(Node node) {
        return this.array.contains(node);
    }

    public boolean containsSameNode(String string) {
        for (Node child:this.array) {
            if (child.toString().equals(string)) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return number == 0;
    }
}
