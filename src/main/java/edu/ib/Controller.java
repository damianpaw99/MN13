package edu.ib;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Klasa sterująca GUI i programem
 */
public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtu;

    @FXML
    private TextField etxtt;

    @FXML
    private TextField etxtx0;

    @FXML
    private TextField etxtv0;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private LineChart<?, ?> graph;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    ObservableList<String> list = FXCollections.observableArrayList();
    Integration integration=new Integration();

    /**
     * Metoda rysująca wykres
     * @param event Informacje o zdarzeniu
     */
    @FXML
    void draw(ActionEvent event) {
        String choice=choiceBox.getValue();
        plot(choice);
    }
    private void plot(String choice){
        String u=etxtu.getText();
        String tx=etxtt.getText();
        String x0=etxtx0.getText();
        String v0=etxtv0.getText();

        if(!u.isEmpty() && !tx.isEmpty() && !x0.isEmpty() && !v0.isEmpty()){
            try {
                Steps stepHandler=new Steps();
                integration.rk4(new double[]{Double.parseDouble(x0),Double.parseDouble(v0)},0.001,Double.parseDouble(tx),0,Double.parseDouble(u),stepHandler);
                XYChart.Series points = new XYChart.Series();
                switch (choice){
                    case "x(t)":
                        for(int i=0; i<stepHandler.getT().size();i+=100) {
                            points.getData().add(new XYChart.Data(stepHandler.getT().get(i), stepHandler.getX().get(i)));
                        }
                        break;
                    case "v(t)":
                        for(int i=0; i<stepHandler.getT().size();i+=100) {
                            points.getData().add(new XYChart.Data(stepHandler.getT().get(i), stepHandler.getV().get(i)));
                        }
                        break;
                    case "v(x)":
                        for(int i=0; i<stepHandler.getX().size();i+=100) {
                            points.getData().add(new XYChart.Data(stepHandler.getX().get(i), stepHandler.getV().get(i)));
                        }
                }
                graph.getData().clear();
                graph.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
                graph.getData().add(points);
            } catch(Exception e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * Metoda ustawiająca wartości przycisku wyboru
     */
    private void setMethodChoiceButton() {
        String a = "x(t)";
        String b = "v(t)";
        String c = "v(x)";
        list.addAll(a, b, c);
        choiceBox.getItems().addAll(list);
        choiceBox.setValue(a);
    }

    /**
     * Metoda inicjalizująca
     */
    @FXML
    void initialize() {
        assert etxtu != null : "fx:id=\"etxtu\" was not injected: check your FXML file 'integrator.fxml'.";
        assert etxtt != null : "fx:id=\"txtt\" was not injected: check your FXML file 'integrator.fxml'.";
        assert etxtx0 != null : "fx:id=\"txtx0\" was not injected: check your FXML file 'integrator.fxml'.";
        assert etxtv0 != null : "fx:id=\"txtvo\" was not injected: check your FXML file 'integrator.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'integrator.fxml'.";
        assert graph != null : "fx:id=\"graph\" was not injected: check your FXML file 'integrator.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'integrator.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'integrator.fxml'.";

        setMethodChoiceButton();
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->{
            plot(choiceBox.getItems().get(newValue.intValue()));
        });
    }
}
