package sample;

import java.util.LinkedList;

public class Queue {
    private LinkedList<Node> array;
    private int size;
    private Node front = null;
    private Node rear = null;
    private int number = 0;

    public Queue(){
        this.array = new LinkedList<>();
    }

    public boolean isEmpty(){
        return number == 0 ;
    }

    public void enQueue(Node a){
        number++;
        a.setNext(null);
        this.array.add(a);
        if (front == null){
            front = a;
            front.setNext(null);
            rear = a;
        }
        else {
            rear.setNext(a);
            rear = a;
        }
    }

    public LinkedList<Node> getArray() {
        return array;
    }

    public void setArray(LinkedList<Node> array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getFront() {
        return front;
    }

    public void setFront(Node front) {
        this.front = front;
    }

    public Node getRear() {
        return rear;
    }

    public void setRear(Node rear) {
        this.rear = rear;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Node deQueue(){
        if (! isEmpty()){
            Node x = front;
            front = front.getNext();
            number --;
            this.array.remove(x);
            return x;
        }
        else return null;
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
}
