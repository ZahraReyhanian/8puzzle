package sample;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.EventListener;

public class GameController extends GridPane {


    public GridPane startPane;
    public GridPane endPane;
    public GridPane currentPane;
    public Label whiteLbl;
    private int currentNum;

    @FXML
    public void initialize(){
        this.currentNum = 1;
        this.currentPane = startPane;
    }

    private void myFunc(javafx.event.ActionEvent actionEvent) {

    }


    public void leftEvent(javafx.event.ActionEvent actionEvent) {
        Label temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);

        Label tt = (Label) getNodeByRowColumnIndex(row , column - 1);
        if (tt != null){
            startPane.getChildren().remove(temp);
            startPane.getChildren().remove(tt);
            startPane.add(temp, column - 1, row);
            startPane.add(tt, column, row);
        }


    }

    public void upEvent(ActionEvent actionEvent) {
        Label temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);

        Label tt = (Label) getNodeByRowColumnIndex(row - 1, column );
        if (tt != null){
            startPane.getChildren().remove(temp);
            startPane.getChildren().remove(tt);
            startPane.add(temp, column, row - 1);
            startPane.add(tt, column, row);
        }
    }

    public void downEvent(ActionEvent actionEvent) {
        Label temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);

        Label tt = (Label) getNodeByRowColumnIndex(row + 1 , column);
        if (tt != null){
            startPane.getChildren().remove(temp);
            startPane.getChildren().remove(tt);
            startPane.add(temp, column, row + 1);
            startPane.add(tt, column, row);
        }
    }

    public void rightEvent(ActionEvent actionEvent) {
        Label temp = whiteLbl;
        int column = getColumnIndex(temp);
        int row = getRowIndex(temp);

        Label tt = (Label) getNodeByRowColumnIndex(row , column + 1);
        if (tt != null){
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
}
