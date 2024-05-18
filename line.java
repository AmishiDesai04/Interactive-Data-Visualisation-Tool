package com.example.javaproject;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class line extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Enter data for the line chart:");
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        TextField valueInput = new TextField();
        valueInput.setStyle("-fx-font-size: 14px;");
        Button addButton = new Button("Add Data");
        addButton.setStyle("-fx-font-size: 14px; -fx-background-color: #140D4F; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());

        TableView<ItemData> tableView = new TableView<>();

        TableColumn<ItemData, Number> xValueCol = new TableColumn<>("Sr. No.");
        xValueCol.setCellValueFactory(cellData -> cellData.getValue().xValueProperty());

        TableColumn<ItemData, Number> valueCol = new TableColumn<>("Value");
        valueCol.setCellValueFactory(cellData -> cellData.getValue().yValueProperty());

        tableView.getColumns().clear();
        tableView.getColumns().addAll(xValueCol, valueCol);

        ObservableList<ItemData> itemDataList = FXCollections.observableArrayList();

        addButton.setOnAction(event -> {
            try {
                double value = Double.parseDouble(valueInput.getText());

                int xValue = itemDataList.size() + 1;

                ItemData itemData = new ItemData(xValue, value);
                itemDataList.add(itemData);

                tableView.setItems(itemDataList);


                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(xValue, value));


                for (int i = 0; i < itemDataList.size() - 1; i++)
                {
                    ItemData currentItem = itemDataList.get(i);
                    ItemData nextItem = itemDataList.get(i + 1);
                    XYChart.Data<Number, Number> currentPoint = new XYChart.Data<>(currentItem.getXValue(), currentItem.getYValue());
                    XYChart.Data<Number, Number> nextPoint = new XYChart.Data<>(nextItem.getXValue(), nextItem.getYValue());
                    series.getData().addAll(currentPoint, nextPoint);
                }

                lineChart.getData().add(series);

                valueInput.clear();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the value.");
            }
        });


        VBox root = new VBox(10, label, new HBox(10, new Label("Value:"), valueInput), addButton, tableView, lineChart);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f4;-fx-font-family: Cambria");


        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("LINE CHART GENERATOR");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static class ItemData {
        private final SimpleDoubleProperty xValue;
        private final SimpleDoubleProperty yValue;

        public ItemData(double xValue, double yValue) {
            this.xValue = new SimpleDoubleProperty(xValue);
            this.yValue = new SimpleDoubleProperty(yValue);
        }

        public double getXValue() {
            return xValue.get();
        }

        public SimpleDoubleProperty xValueProperty() {
            return xValue;
        }

        public double getYValue() {
            return yValue.get();
        }

        public SimpleDoubleProperty yValueProperty() {
            return yValue;
        }
    }
}
