package sample;

import com.sun.deploy.panel.JavaPanel;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

public class GameController extends GridPane {


    public GridPane startPane;
    public GridPane endPane;
    public GridPane currentPane;
    public Button whiteLbl;
    private int currentNum;
    private Integer[][] start ;
    private Integer[][] end ;

    @FXML
    public void initialize(){
        this.currentNum = 1;
        this.currentPane = startPane;
        start = new Integer[3][3];
        end = new Integer[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start[i][j] = 0;
                end[i][j] = 0;
            }
        }
    }

    private void myFunc(javafx.event.ActionEvent actionEvent) {

    }


    public void leftEvent() {
        Button temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);

        Button tt = (Button) getNodeByRowColumnIndex(row , column - 1);
        System.out.println("lefffft");
        if (tt != null){
            System.out.println("left done");
            startPane.getChildren().remove(temp);
            startPane.getChildren().remove(tt);
            startPane.add(temp, column - 1, row);
            startPane.add(tt, column, row);
        }


    }

    public void upEvent() {
        Button temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);
        System.out.println("up!");

        Button tt = (Button) getNodeByRowColumnIndex(row - 1, column );
        if (tt != null){
            startPane.getChildren().remove(temp);
            startPane.getChildren().remove(tt);
            startPane.add(temp, column, row - 1);
            startPane.add(tt, column, row);
        }
    }

    public void downEvent() {
        Button temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);

        Button tt = (Button) getNodeByRowColumnIndex(row + 1 , column);
        if (tt != null){
            startPane.getChildren().remove(temp);
            startPane.getChildren().remove(tt);
            startPane.add(temp, column, row + 1);
            startPane.add(tt, column, row);
        }
    }

    public void rightEvent() {
        Button temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);

        Button tt = (Button) getNodeByRowColumnIndex(row , column + 1);
        System.out.println("right");
        if (tt != null){
            System.out.println("right done");
            startPane.getChildren().remove(temp);
            startPane.getChildren().remove(tt);
            startPane.add(temp, column + 1, row);
            startPane.add(tt, column, row);
        }
    }


    public Node getNodeByRowColumnIndex (final int row, final int column) {
        Node result = null;
        ObservableList<Node> children = startPane.getChildren();

        for (Node node : children) {
            if(startPane.getRowIndex(node) == row && startPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    public void setNumber(ActionEvent actionEvent) {
        boolean found = false;
        if(currentNum < 9){
            String num = currentNum + "";
            ObservableList<Node> children = currentPane.getChildren();
            for (Node node : children) {
                if(node == actionEvent.getSource()) {
                    Button btn = (Button) node;
                    btn.setText(num);
                    found = true;
                    if(currentPane == startPane){
                        start[startPane.getRowIndex(btn)][startPane.getColumnIndex(btn)] = currentNum;
                    }
                    else {
                        end[endPane.getRowIndex(btn)][endPane.getColumnIndex(btn)] = currentNum;
                    }
                    break;
                }
            }
        }

        if(found || currentNum == 9){
            currentNum ++;
            if(currentNum == 10 && currentPane == startPane){
                currentNum = 1;
                currentPane = endPane;
            }
        }


    }

    public void startBFS(ActionEvent actionEvent) throws InterruptedException {
        BFS bfs = new BFS(start, end);
        if(!bfs.solve()){
            JOptionPane.showMessageDialog(null, "Not Found !");
            return;
        }
        List<sample.Node> nodes = new LinkedList();
        sample.Node child = bfs.getCurrentNode();

        while (child.getParent() != null){
            nodes.add(child);
            child = child.getParent();
        }

        System.out.println(nodes.size());
        new Thread(()->{ //use another thread so long process does not block gui
            while (nodes.size() != 0){
                int k = nodes.size() - 1;
                System.out.println("k : "+k);
                sample.Node currentNode = nodes.get(k);
                whiteLbl = (Button) this.getNodeByRowColumnIndex(currentNode.getI(), currentNode.getJ());
                if(currentNode.getDirection() == sample.Node.Direction.Right){
                    Platform.runLater(() -> this.leftEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Left){
                    Platform.runLater(() -> this.rightEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.TOP){
                    Platform.runLater(() -> this.downEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Bottom){
                    Platform.runLater(() -> this.upEvent());
                }
                nodes.remove(currentNode);
                try {Thread.sleep(1000);} catch (InterruptedException ex) { ex.printStackTrace();}
            }

        }).start();

        System.out.println("exit!");

    }

    public void reset(ActionEvent actionEvent) {
        this.currentNum = 1;
        this.currentPane = startPane;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start[i][j] = 0;
                end[i][j] = 0;
            }
        }
        ObservableList<Node> children = startPane.getChildren();
        for (Node node : children) {
            Button btn = (Button) node;
            btn.setText("");
        }
        children = endPane.getChildren();
        for (Node node : children) {
            Button btn = (Button) node;
            btn.setText("");
        }

    }

    public void startIDS(ActionEvent actionEvent) {
        IDS ids = new IDS(start, end, 20);
        if(!ids.solve()){
            JOptionPane.showMessageDialog(null, "Not Found !");
            return;
        }
        List<sample.Node> nodes = new LinkedList();
        sample.Node child = ids.getCurrentNode();

        while (child.getParent() != null){
            System.out.println(child.toString());
            System.out.println(child.getDirection());
            nodes.add(child);
            child = child.getParent();
        }

        System.out.println(nodes.size());
        new Thread(()->{ //use another thread so long process does not block gui
            while (nodes.size() != 0){
                int k = nodes.size() - 1;
                System.out.println("k : "+k);
                sample.Node currentNode = nodes.get(k);
                System.out.println(currentNode.getDirection());
                whiteLbl = (Button) this.getNodeByRowColumnIndex(currentNode.getI(), currentNode.getJ());
                if(currentNode.getDirection() == sample.Node.Direction.Right){
                    Platform.runLater(() -> this.leftEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Left){
                    Platform.runLater(() -> this.rightEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.TOP){
                    Platform.runLater(() -> this.downEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Bottom){
                    Platform.runLater(() -> this.upEvent());
                }
                nodes.remove(currentNode);
                try {Thread.sleep(1000);} catch (InterruptedException ex) { ex.printStackTrace();}
            }

        }).start();

        System.out.println("exit!");
    }

    public void startBiDir(ActionEvent actionEvent) {
        BDSearch bd = new BDSearch(start, end);
        if(!bd.solve()){
            JOptionPane.showMessageDialog(null, "Not Found !");
            return;
        }
        List<sample.Node> nodes = new LinkedList();
        sample.Node child = bd.getCurrentNode();

        while (child.getParent() != null){
            System.out.println(child.toString());
            System.out.println(child.getDirection());
            nodes.add(child);
            child = child.getParent();
        }
        new Thread(()->{ //use another thread so long process does not block gui
            while (nodes.size() != 0){
                int k = nodes.size() - 1;
                System.out.println("k : "+k);
                sample.Node currentNode = nodes.get(k);
                System.out.println(currentNode.getDirection());
                whiteLbl = (Button) this.getNodeByRowColumnIndex(currentNode.getI(), currentNode.getJ());
                if(currentNode.getDirection() == sample.Node.Direction.Right){
                    Platform.runLater(() -> this.leftEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Left){
                    Platform.runLater(() -> this.rightEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.TOP){
                    Platform.runLater(() -> this.downEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Bottom){
                    Platform.runLater(() -> this.upEvent());
                }
                nodes.remove(currentNode);
                try {Thread.sleep(1000);} catch (InterruptedException ex) { ex.printStackTrace();}
            }

        }).start();
    }

    public void startAStar(ActionEvent actionEvent) {
        AStar aStar = new AStar(start, end);
        if(!aStar.solve()){
            JOptionPane.showMessageDialog(null, "Not Found !");
            return;
        }
        List<sample.Node> nodes = new LinkedList();
        sample.Node child = aStar.getCurrentNode();

        while (child.getParent() != null){
            nodes.add(child);
            child = child.getParent();
        }

        System.out.println(nodes.size());
        new Thread(()->{ //use another thread so long process does not block gui
            while (nodes.size() != 0){
                int k = nodes.size() - 1;
                System.out.println("k : "+k);
                sample.Node currentNode = nodes.get(k);
                whiteLbl = (Button) this.getNodeByRowColumnIndex(currentNode.getI(), currentNode.getJ());
                if(currentNode.getDirection() == sample.Node.Direction.Right){
                    Platform.runLater(() -> this.leftEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Left){
                    Platform.runLater(() -> this.rightEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.TOP){
                    Platform.runLater(() -> this.downEvent());
                }
                else if(currentNode.getDirection() == sample.Node.Direction.Bottom){
                    Platform.runLater(() -> this.upEvent());
                }
                nodes.remove(currentNode);
                try {Thread.sleep(1000);} catch (InterruptedException ex) { ex.printStackTrace();}
            }

        }).start();

        System.out.println("exit!");
    }
}
